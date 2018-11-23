package com.example.android.aptekaapp.Data.Cashe.db.Database;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.android.aptekaapp.Data.Entity.DragEntityDetails;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface DragDetailsDao {

    @Query("SELECT * FROM details WHERE groupName =:groupName")
    Single<List<DragEntityDetails>> getListDragDetails(String groupName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDragDetailList(List<DragEntityDetails> dragEntityDetailses);

    @Query("DELETE FROM details")
    void clearDetailsTable();

    @Query("SELECT * FROM details WHERE groupName =:groupName")
    Maybe<List<DragEntityDetails>> isTableEmpty(String groupName);

}
