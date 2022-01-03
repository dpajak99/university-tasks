package pl.pwsztar.ppsm_lab_7.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import pl.pwsztar.ppsm_lab_7.data.database.dao.DbCityDAO;
import pl.pwsztar.ppsm_lab_7.data.model.City;

@Database(entities = {City.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
  public abstract DbCityDAO dbCityDAO();
}
