package com.example.pencatatanpenduduk.Helpers;

import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.pencatatanpenduduk.model.Penduduk;

@Database(entities = {Penduduk.class}, version = 1)
public abstract class DBRoomHelper extends RoomDatabase {

    public abstract PendudukDAO pendudukDAO();

}
