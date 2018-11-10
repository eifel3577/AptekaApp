package com.example.android.aptekaapp.Data.Repository.DataSource;

import android.util.Log;


import com.example.android.aptekaapp.Data.Cashe.DragCashe;
import com.example.android.aptekaapp.Data.Entity.DragEntity;
import com.example.android.aptekaapp.Data.Net.Parsing.JsoupGetData;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class ParsingDragDataStore implements DragDataStore {

    private final JsoupGetData jsoupGetData;
    private final DragCashe dragCache;

    /**
     * Конструктор {@link DragDataStore} основан на соединении с api.
     *
     * @param jsoupGetData The {@link JsoupGetData} апи чтобы ходить в сеть и брать данные через парсинг
     * @param dragCache A {@link DragCashe} чтобы кешировать данные полученные через парсинг
     */
    ParsingDragDataStore(JsoupGetData jsoupGetData, DragCashe dragCache) {
        Log.d("2810","ParsingDragDataStore constructor");
        this.jsoupGetData = jsoupGetData;
        this.dragCache = dragCache;
    }

    @Override
    public Observable<List<DragEntity>> dragEntityList(final String dragTitle) {
        Log.d("2810","ParsingDragDataStore dragEntityList");
        //при получении данных они автоматически кладутся в кеш(базу данных)
        return this.jsoupGetData.dragEntityList(dragTitle).doOnNext(new Consumer<List<DragEntity>>() {
            @Override
            public void accept(@NonNull List<DragEntity> dragEntityList) throws Exception {

                Log.d("2810","ParsingDragDataStore accept ,dragEntityList is empty "+String.valueOf(dragEntityList.isEmpty()));
                ParsingDragDataStore.this.dragCache.put(dragEntityList);


                //dragCache.put(dragEntityList,dragTitle);

            }
        });
    }
}
