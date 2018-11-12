package com.example.android.aptekaapp.Data.Cashe.db;



import android.util.Log;

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
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class DatabaseCashe implements DragCashe {


    MyDatabase dragstoreServiceDatabase;
    private final DragDao dragDao;
    private boolean isDataInDatabase;

    @Inject
    public DatabaseCashe() {
        //TODO заменить на получение инстанса MyDatabase из даггера
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
    public Observable<List<DragEntity>> get(String groupName) {
        return dragDao.getListDrags().flatMapObservable(new Function<List<DragEntity>, ObservableSource<List<DragEntity>>>() {
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
        new Runnable(){
            @Override
            public void run() {
                List<Long>longs = dragDao.insertDragList(list);
                for(Long s : longs) Log.d("1111","inserted to db "+String.valueOf(s));
            }
        };

    }

    @Override
    public boolean isCached(String groupName) {
        dragDao.isListEmpty(groupName).subscribe(new MaybeObserver<DragEntity>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {}

            @Override
            public void onSuccess(@NonNull DragEntity dragEntity) {
                isDataInDatabase = true;

            }

            @Override
            public void onError(@NonNull Throwable e) {
                isDataInDatabase = false;
            }

            @Override
            public void onComplete() {}
        });
        Log.d("1111","isDataInDatabase = "+String.valueOf(isDataInDatabase));
        return isDataInDatabase;
    }

    @Override
    public boolean isExpired() {
        return false;
    }


}
