package pl.pwsztar.ppsm_lab_7.data.repository;

import pl.pwsztar.ppsm_lab_7.data.model.WeatherDetails;
import retrofit2.http.Body;
import retrofit2.http.GET;
import io.reactivex.Observable;
import retrofit2.http.Query;

public interface RepoService {
  String APPID = "749561a315b14523a8f5f1ef95e45864";
  String units = "metric";
  String HTTPS_API_MPK_URL = "http://api.openweathermap.org/";

  @GET("data/2.5/weather")
  Observable<WeatherDetails> getCityDetails(@Query("q") String city, @Query("units") String units, @Query("APPID") String APPID);
}
