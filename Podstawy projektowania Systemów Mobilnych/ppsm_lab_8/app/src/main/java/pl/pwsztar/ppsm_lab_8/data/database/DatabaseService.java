package pl.pwsztar.ppsm_lab_8.data.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseService {
  private AppDatabase database;

  public DatabaseService(Context context) {
    this.database = Room.databaseBuilder(context,
      AppDatabase.class, "weatherapp.db").allowMainThreadQueries().build();
  }

  public AppDatabase getDatabase() {
    return database;
  }

  public static DatabaseService getInstance(Context context) {
    return new DatabaseService(context);
  }
}
