package pl.pwsztar.ppsm_lab_8.ui.viewmodel;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pl.pwsztar.ppsm_lab_8.data.model.WeatherDetails;
import pl.pwsztar.ppsm_lab_8.data.repository.JsonRepository;
import pl.pwsztar.ppsm_lab_8.data.repository.RepoService;


public class WeatherActivityViewModel extends Observable {
  private Context context;
  private WeatherDetails weatherDetails;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();;
  private String cityName;

  public WeatherActivityViewModel(@NonNull Context context, String cityName) {
    this.context = context;
    this.cityName = cityName;

    update();
  }

  private void getCityFromRepo(String city) {
    Disposable disposable = JsonRepository.getInstance().getRepoService().getCityDetails(city, RepoService.units, RepoService.APPID )
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(this::changeCityDataSet, throwable -> {
        Log.i("TEST", "failed");
        Log.i("TEST", throwable.toString());
      });

    compositeDisposable.add(disposable);
  }

  public WeatherDetails getWeatherDetails() {
    return weatherDetails;
  }

  public void update() {
    getCityFromRepo(cityName);
  }

  private void changeCityDataSet(WeatherDetails weatherDetails) {
    Log.i("TEST", weatherDetails.getMain().toString());
    this.weatherDetails = weatherDetails;
    setChanged();
    notifyObservers();
  }

  public void reset() {
    compositeDisposable.dispose();
    compositeDisposable = null;
    context = null;
  }
}
