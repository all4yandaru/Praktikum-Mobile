package com.project.mylocaldb.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.project.mylocaldb.model.Penjualan;

@Database(entities = Penjualan.class, version = 1)
public abstract class PenjualanDatabase extends RoomDatabase {
    private static final String DB_NAME = "db_penjualan";
    private static PenjualanDatabase instance;

    public abstract PenjualanDao penjualanDao();

    public static PenjualanDatabase getInstance(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), PenjualanDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
