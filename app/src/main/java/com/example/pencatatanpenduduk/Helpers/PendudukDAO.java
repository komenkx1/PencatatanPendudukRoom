package com.example.pencatatanpenduduk.Helpers;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pencatatanpenduduk.model.Penduduk;

@Dao
public interface PendudukDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertPenduduk(Penduduk penduduk);

    @Update
    int updatePenduduk(Penduduk penduduk);

    @Delete
    int deletePenduduk(Penduduk penduduk);

    @Query("SELECT * FROM tbPenduduk")
    Penduduk[] selectAllPenduduks();

    @Query("SELECT * FROM tbPenduduk WHERE _id = :id LIMIT 1")
    Penduduk selectPendudukDetail(int id);
}
