package com.example.android.aptekaapp.Data.Cashe.db.Database;



import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.android.aptekaapp.Data.Cashe.DragCashe;
import com.example.android.aptekaapp.Data.Entity.DragEntity;
import com.example.android.aptekaapp.Presentation.AndroidApplication;

import org.reactivestreams.Publisher;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class DatabaseCashe implements DragCashe {


    MyDatabase dragstoreServiceDatabase;
    private final DragDao dragDao;
    private Boolean v;

    @Inject
    public DatabaseCashe() {
        //TODO заменить на получение инстанса MyDatabase из даггера
        dragstoreServiceDatabase = AndroidApplication.getInstance().getDatabase();
        dragDao = dragstoreServiceDatabase.dragDao();
    }

    @Override
    public void evictAll() {
        dragDao.clearTable();
    }

    @Override
    public Observable<List<DragEntity>> get(String groupName) {
        return dragDao.getListDrags(groupName).
                subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).
                flatMapObservable(new Function<List<DragEntity>, ObservableSource<List<DragEntity>>>() {
                    @Override
                    public ObservableSource<List<DragEntity>> apply(@NonNull List<DragEntity> dragEntities) throws Exception {
                        Log.d("1111","dragEntityList size = "+String.valueOf(dragEntities.size()));
                        return Observable.fromArray(dragEntities);
                    }
                });
    }

    @Override
    public void put(final List<DragEntity> list) {
        Log.d("1111","put is called");
        dragDao.insertDragList(list);
    }

    @Override
    public boolean isCached(final String groupName) {
        checkTable(groupName);
        return getV();
    }


    private void checkTable(final String groupName){
        dragDao.getListDrags(groupName).
                subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Consumer<List<DragEntity>>() {
            @Override
            public void accept(@NonNull List<DragEntity> dragEntities) throws Exception {
                if(dragEntities.size()>0) setV(true);
                else setV(false);
            }
        });
    }

    public Boolean getV() {
        return v;
    }

    public void setV(Boolean v) {
        this.v = v;
    }

    @Override
    public boolean isExpired() {
        return false;
    }





}
