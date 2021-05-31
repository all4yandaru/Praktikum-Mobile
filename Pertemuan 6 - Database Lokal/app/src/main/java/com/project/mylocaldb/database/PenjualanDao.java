package com.project.mylocaldb.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.project.mylocaldb.model.Penjualan;

import java.util.List;

@Dao
public interface PenjualanDao {
    @Query("SELECT * FROM penjualan")
    List<Penjualan> getAllData();

    @Insert
    void insertData(Penjualan penjualan);

    @Update
    void updateData(Penjualan penjualan);

    @Delete
    void deleteData(Penjualan penjualan);
}
