package com.example.android.aptekaapp.Data.Cashe.db;



import com.example.android.aptekaapp.Data.Cashe.DragCashe;
import com.example.android.aptekaapp.Data.Cashe.db.Database.MyDatabase;
import com.example.android.aptekaapp.Data.Entity.DragEntity;
import com.example.android.aptekaapp.Presentation.AndroidApplication;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class DatabaseCashe implements DragCashe {


    MyDatabase dragstoreServiceDatabase;
    private final DragDao dragDao;
    private boolean isDatabaseEmpty;

    @Inject
    public DatabaseCashe() {
        dragstoreServiceDatabase = AndroidApplication.getInstance().getDatabase();
        dragDao = dragstoreServiceDatabase.dragDao();
    }

    @Override
    public void evictAll() {
        new Runnable(){
            @Override
            public void run() {
                dragDao.clearTable();
            }
        };
    }

    @Override
    public Single<List<DragEntity>> get(String groupName) {
        return dragDao.getListDrags(groupName);
    }

    @Override
    public void put(final List<DragEntity> list) {
        new Runnable(){
            @Override
            public void run() {
                dragDao.insertDragList(list);
            }
        };

    }

    @Override
    public boolean isCached(String groupName) {
        dragDao.isListEmpty(groupName).subscribe(new MaybeObserver<DragEntity>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull DragEntity dragEntity) {
                isDatabaseEmpty = false;
            }

            @Override
            public void onError(@NonNull Throwable e) {
                isDatabaseEmpty = true;
            }

            @Override
            public void onComplete() {

            }
        });
        return isDatabaseEmpty;
    }

    @Override
    public boolean isExpired() {
        return false;
    }


}
