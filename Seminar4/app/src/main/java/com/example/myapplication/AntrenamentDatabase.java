package com.example.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Antrenament.class}, version = 1)
@TypeConverters({ConvertDateToLong.class})
public abstract class AntrenamentDatabase extends RoomDatabase {
    public abstract AntrenamentDAO antrenamentDAO();
}
