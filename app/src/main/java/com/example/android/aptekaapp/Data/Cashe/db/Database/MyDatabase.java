package com.example.android.aptekaapp.Data.Cashe.db.Database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.android.aptekaapp.Data.Entity.DragEntity;
import com.example.android.aptekaapp.Data.Entity.DragEntityDetails;


@Database(entities = {DragEntity.class, DragEntityDetails.class}, version = 1,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public abstract DragDao dragDao();
    public abstract DragDetailsDao dragDetailsDao();
}