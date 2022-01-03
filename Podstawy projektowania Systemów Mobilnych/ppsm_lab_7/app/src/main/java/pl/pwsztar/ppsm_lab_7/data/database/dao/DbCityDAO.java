package pl.pwsztar.ppsm_lab_7.data.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Observable;
import pl.pwsztar.ppsm_lab_7.data.model.City;

@Dao
public interface DbCityDAO {
  @Query("SELECT * FROM City")
  Observable<List<City>> getAll();

  @Insert
  void insert(City city);
}
