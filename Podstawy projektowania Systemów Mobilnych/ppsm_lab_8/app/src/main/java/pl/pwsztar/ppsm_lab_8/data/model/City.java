package pl.pwsztar.ppsm_lab_8.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "City")
public class City {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    @NonNull
    private String name;
    @ColumnInfo(name = "isAvailable")
    @NonNull
    private boolean isAvailable;

    public City(int id, @NonNull String name, @NonNull boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.isAvailable = isAvailable;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "City{" +
          "id=" + id +
          ", name='" + name + '\'' +
          ", isAvailable=" + isAvailable +
          '}';
    }
}
