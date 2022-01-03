package pl.pwsztar.ppsm_lab_8.ui.viewmodel;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import io.reactivex.schedulers.Schedulers;
import pl.pwsztar.ppsm_lab_8.data.database.DatabaseService;
import pl.pwsztar.ppsm_lab_8.data.model.City;
import pl.pwsztar.ppsm_lab_8.data.repository.JsonRepository;
import pl.pwsztar.ppsm_lab_8.data.repository.RepoService;
import pl.pwsztar.ppsm_lab_8.ui.views.SearchActivity;


public class SearchActivityViewModel extends Observable {
  private Context context;
  private SearchActivity activity;
  private List<City> cities;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();;

  public SearchActivityViewModel(@NonNull Context context, SearchActivity activity) {
    this.activity = activity;
    this.context = context;
    this.cities = new ArrayList<>();
    fetchCities();
  }

  private void fetchCities() {
    DatabaseService databaseService = new DatabaseService(context);

    Disposable disposable = databaseService.getDatabase().dbCityDAO().getAll()
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(this::changeCityDataSet, Throwable::printStackTrace);

    compositeDisposable.add(disposable);
  }

  private void getCityFromRepo(String city) {
    Disposable disposable = JsonRepository.getInstance().getRepoService().getCityDetails(city + ",", RepoService.units, RepoService.APPID )
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(weatherDetails -> {
        Log.i("TEST", "success");
        addCityToDatabase(city, true);
      }, throwable -> {
        Log.i("TEST", "failed");
        Log.i("TEST", throwable.toString());
        addCityToDatabase(city, false);
      });

    compositeDisposable.add(disposable);
  }

  private void changeCityDataSet(List<City> cities) {
    this.cities = cities;
    setChanged();
    notifyObservers();
  }


  public List<City> getCitiesList() {
    return cities;
  }


  public void checkCityInRepo(String city ) {
    getCityFromRepo(city);
  }

  public void addCityToDatabase( String name, boolean status ) {
    DatabaseService.getInstance(context).getDatabase().dbCityDAO().insert(new City(0 ,name, status));

    if(status) {
      Intent returnIntent = new Intent();
      returnIntent.putExtra("CITY_NAME", name);
      activity.setResult(Activity.RESULT_OK,returnIntent);
      activity.finish();
    } else {
      Toast.makeText(context, "Nie znaleziono miejscowości", Toast.LENGTH_SHORT).show();
      fetchCities();
    }
  }

  public void reset() {
    compositeDisposable.dispose();
    compositeDisposable = null;
    context = null;
  }
}
