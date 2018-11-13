package com.example.android.aptekaapp.Data.Cashe.db.Database;



import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.android.aptekaapp.Data.Cashe.DragCashe;
import com.example.android.aptekaapp.Data.Entity.DragEntity;
import com.example.android.aptekaapp.Presentation.AndroidApplication;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
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

    /**время, которое информация будет хранится в кеше.В данном случае 10 минут */
    private static final long EXPIRATION_TIME = 60 * 10 * 1000;
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";
    private static final String PREFERENCE_IDENTIFICATOR = "pref_ident";

    private final Context context;
    MyDatabase dragstoreServiceDatabase;
    private final DragDao dragDao;
    private String isDataInDatabase;


    @Inject
    public DatabaseCashe(Context context) {
        this.context = context;
        //TODO заменить на получение инстанса MyDatabase из даггера
        dragstoreServiceDatabase = AndroidApplication.getInstance().getDatabase();
        dragDao = dragstoreServiceDatabase.dragDao();
    }

    @Override
    public void evictAll() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                dragDao.clearTable();
            }
        });
        thread.start();
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

    /**кладет список DragEntity в базу,устанавливает временную метку */
    @Override
    public void put(final List<DragEntity> list) {
        Log.d("1111","put is called");
        dragDao.insertDragList(list);
        setLastCacheUpdateTimeMillis();
    }


    @Override
    public boolean isCached(String dragTitle) {
        isDataAvailable(dragTitle);
        if(isDataInDatabase!=null){
            if(isDataInDatabase.equals("dataInDatabase")){
                return true;
            }
            return false;
        }
        return false;
    }

    private void isDataAvailable(String dragTitle){
        dragDao.getListDrags(dragTitle).
                subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Consumer<List<DragEntity>>() {
                    @Override
                    public void accept(@NonNull List<DragEntity> dragEntities) throws Exception {
                        if(dragEntities.size()>0) {
                            isDataInDatabase = "dataInDatabase";
                        }
                        else isDataInDatabase = "dataIsNotInDatabase";
                    }
                });
    }



    /**определяет текущее время,затем получает время последнего кеширования
     * если с момента кеширования до настоящего времени прошло больше 10 минут,
     * то данные устарели и они вытираются из базы*/
    @Override
    public boolean isExpired() {
        long currentTime = System.currentTimeMillis();
        long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

        boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME);

        if (expired) {
            this.evictAll();
        }

        return expired;

    }

    /**
     * Кладет в преференс временную метку.Это будет временная метка того времени,
     * когда данные ложились в кеш
     */
    private void setLastCacheUpdateTimeMillis() {
        final long currentMillis = System.currentTimeMillis();
        final SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_IDENTIFICATOR,
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(SETTINGS_KEY_LAST_CACHE_UPDATE, currentMillis);
        editor.apply();
    }

    /**
     * Достает из преференса временную метку
     */
    private long getLastCacheUpdateTimeMillis() {
        final SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_IDENTIFICATOR,
                Context.MODE_PRIVATE);
        return sharedPreferences.getLong(SETTINGS_KEY_LAST_CACHE_UPDATE, 0);
    }

}
