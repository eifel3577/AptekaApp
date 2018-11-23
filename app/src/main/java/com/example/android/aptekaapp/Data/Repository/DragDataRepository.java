package com.example.android.aptekaapp.Data.Repository;

import android.os.SystemClock;
import android.util.Log;


import com.example.android.aptekaapp.Data.Entity.DragEntity;
import com.example.android.aptekaapp.Data.Entity.DragEntityDetails;
import com.example.android.aptekaapp.Data.Entity.Mapper.DragEntityDataMapper;
import com.example.android.aptekaapp.Data.Entity.Mapper.DragEntityDetailsMapper;
import com.example.android.aptekaapp.Data.Repository.DataSource.DragDataStore;
import com.example.android.aptekaapp.Data.Repository.DataSource.DragDataStoreFactory;
import com.example.android.aptekaapp.Domain.Drag;
import com.example.android.aptekaapp.Domain.DragDetails;
import com.example.android.aptekaapp.Domain.Repository.DragRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * {@link DragDataRepository} имплементит оепозиторий слоя Domain,таким образом связывая
 * слой Data и слой Domain
 */
@Singleton
public class DragDataRepository implements DragRepository {

    private final DragDataStoreFactory dragDataStoreFactory;
    private final DragEntityDataMapper dragEntityDataMapper;
    private final DragEntityDetailsMapper dragEntityDetailsMapper;

    /**
     * Конструирует {@link DragRepository}.
     *
     * @param dataStoreFactory Фабрика чтобы строить различные вариации data source
     * @param dragEntityDataMapper {@link DragEntityDataMapper}.
     */
    @Inject
    DragDataRepository(DragDataStoreFactory dataStoreFactory,
                       DragEntityDataMapper dragEntityDataMapper,
                       DragEntityDetailsMapper dragEntityDetailsMapper) {
        this.dragDataStoreFactory = dataStoreFactory;
        this.dragEntityDataMapper = dragEntityDataMapper;
        this.dragEntityDetailsMapper = dragEntityDetailsMapper;
    }

    /**создает DragDataStore (источник данных) через фабрику,полученные из источника данные
     * конвертятся из DragEntity в Drag */
    @Override
    public Observable<List<Drag>> drags(String searchString) {

        final DragDataStore dragDataStore = this.dragDataStoreFactory.create(searchString);
        return dragDataStore.dragEntityList(searchString).map(new Function<List<DragEntity>, List<Drag>>() {
            @Override
            public List<Drag> apply(@NonNull List<DragEntity> dragEntities) throws Exception {
                return dragEntityDataMapper.transform(dragEntities);
            }
        });
    }

    @Override
    public Observable<List<DragDetails>> dragDetails(String dragSearch) {
        final DragDataStore dragDataStore = this.dragDataStoreFactory.create(dragSearch);
        return dragDataStore.dragEntityDetailsList(dragSearch).map(new Function<List<DragEntityDetails>, List<DragDetails>>() {
            @Override
            public List<DragDetails> apply(@NonNull List<DragEntityDetails> dragEntityDetailses) throws Exception {
                return dragEntityDetailsMapper.transformListDragEntityDetailsToListDragDetails(dragEntityDetailses);
            }
        });

    }
}