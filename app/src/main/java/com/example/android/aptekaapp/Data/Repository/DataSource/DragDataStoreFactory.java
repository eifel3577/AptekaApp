package com.example.android.aptekaapp.Data.Repository.DataSource;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;


import com.example.android.aptekaapp.Data.Cashe.DragCashe;
import com.example.android.aptekaapp.Data.Net.Parsing.JsoupGetData;
import com.example.android.aptekaapp.Data.Net.Parsing.JsoupGetDataImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Фабрика которая создает различные вариации источников данных {@link DragDataStore}.
 */
@Singleton
public class DragDataStoreFactory {

    private final Context context;
    private final DragCashe dragCache;

    @Inject
    DragDataStoreFactory(@NonNull Context context, @NonNull DragCashe dragCache) {
        this.context = context.getApplicationContext();
        this.dragCache = dragCache;
    }

    /**
     * Создание источника данных {@link DragDataStore}
     * @param dragTitle название лекарства
     */
    public DragDataStore create(String dragTitle) {
        Log.d("1111","dragTitle = "+dragTitle);
        DragDataStore dragDataStore;

        //проверяет если база непустая то источник данных будет база (DatabaseDragDataStore)
        //if (!this.dragCache.isExpired() && this.dragCache.isCached(dragTitle)) {
        if(this.dragCache.isCached(dragTitle)){
            Log.d("1111","грузим из кеша");
            dragDataStore = new DatabaseDragDataStore(this.dragCache);
        }
        else {
            dragDataStore = createParsingDataStore();
        }

        return dragDataStore;
    }

    /**
     * Создание {@link DragDataStore} для получения данных через парсинг сети
     */
    public DragDataStore createParsingDataStore() {
        final JsoupGetData jsoupGetData = new JsoupGetDataImpl(this.context);
        return new ParsingDragDataStore(jsoupGetData, this.dragCache);
    }
}