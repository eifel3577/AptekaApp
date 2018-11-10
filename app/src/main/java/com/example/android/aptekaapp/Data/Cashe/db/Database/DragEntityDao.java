package com.example.android.aptekaapp.Data.Cashe.db.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import com.example.android.aptekaapp.Data.Entity.DragEntity;

import java.util.List;

@Dao
public interface DragEntityDao {

    @Insert
    void saveDragEntity(DragEntity dragEntity);

    @Insert
    void saveDragEntityList(List<DragEntity>dragEntityList);

    @Query("SELECT * FROM dragentity")
    List<DragEntity> loadAll();

    //@Query("SELECT * FROM dragentity WHERE dragName = :dragName")
    //DragEntity getByTitle(String dragTitle);

    @Query("DELETE FROM dragentity")
    void clearDatabase();


}