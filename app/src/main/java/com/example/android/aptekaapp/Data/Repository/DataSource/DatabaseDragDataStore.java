package com.example.android.aptekaapp.Data.Repository.DataSource;



import com.example.android.aptekaapp.Data.Cashe.DragCashe;
import com.example.android.aptekaapp.Data.Entity.DragEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 *  Источник данных {@link DragDataStore}, основанная на на хранении данных
 *  в базе данных
 */

public class DatabaseDragDataStore implements DragDataStore {

    private final DragCashe dragCache;
    private String dragTitle;

    /**
     * Этот {@link DragDataStore} будет работать с базой данных
     *
     * @param dragCache  {@link DragCashe} кэш базы данных
     */
    DatabaseDragDataStore(DragCashe dragCache) {
        this.dragCache = dragCache;
    }

    @Override
    public Observable<List<DragEntity>> dragEntityList(String dragTitle) {
        return this.dragCache.get(dragTitle);
    }
}