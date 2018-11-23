package com.example.android.aptekaapp.Data.Repository.DataSource;

import android.util.Log;


import com.example.android.aptekaapp.Data.Cashe.DragCashe;
import com.example.android.aptekaapp.Data.Entity.DragEntity;
import com.example.android.aptekaapp.Data.Entity.DragEntityDetails;
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
        this.jsoupGetData = jsoupGetData;
        this.dragCache = dragCache;
    }

    @Override
    public Observable<List<DragEntity>> dragEntityList(final String dragTitle) {

        return this.jsoupGetData.dragEntityList(dragTitle).doOnNext(new Consumer<List<DragEntity>>() {

            @Override
            public void accept(@NonNull List<DragEntity> dragEntityList) throws Exception {
                ParsingDragDataStore.this.dragCache.put(dragEntityList);
            }
        });
    }

    @Override
    public Observable<List<DragEntityDetails>> dragEntityDetailsList(String dragTitle) {
        return this.jsoupGetData.dragEntityDetailsList(dragTitle).doOnNext(new Consumer<List<DragEntityDetails>>() {
            @Override
            public void accept(@NonNull List<DragEntityDetails> dragEntityDetailses) throws Exception {
                ParsingDragDataStore.this.dragCache.put(dragEntityDetailses);
            }
        });
    }
}
