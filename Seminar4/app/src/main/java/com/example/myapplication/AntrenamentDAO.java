package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AntrenamentDAO {
    @Insert
    public void insertAntrenament(Antrenament antrenament);

    @Query("Select * from antrenamente")
    public List<Antrenament> getAntrenamente();
    @Delete
    void deleteAntrenament(Antrenament antrenament);

}
