package com.example.android.aptekaapp.Data.Cashe.db;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


import com.example.android.aptekaapp.Data.Entity.DragEntity;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface DragDao {

    @Query("SELECT * FROM dragentity WHERE groupName = :groupName")
    Single<List<DragEntity>> getListDrags(String groupName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDragList(List<DragEntity> dragEntities);

    @Query("DELETE FROM dragentity")
    void clearTable();

    @Query("SELECT * FROM dragentity WHERE groupName = :groupName")
    Maybe<DragEntity> isListEmpty(String groupName);


}
