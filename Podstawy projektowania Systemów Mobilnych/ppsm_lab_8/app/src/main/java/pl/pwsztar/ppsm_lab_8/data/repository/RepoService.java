package pl.pwsztar.ppsm_lab_8.data.repository;

import pl.pwsztar.ppsm_lab_8.data.model.WeatherDetails;
import retrofit2.http.GET;
import io.reactivex.Observable;
import retrofit2.http.Query;

public interface RepoService {
  String APPID = "06dee8162f98ef39e3a0146a1b7db911";
  String units = "metric";
  String HTTPS_API_MPK_URL = "http://api.openweathermap.org/";

  @GET("data/2.5/weather")
  Observable<WeatherDetails> getCityDetails(@Query("q") String city, @Query("units") String units, @Query("APPID") String APPID);
}
