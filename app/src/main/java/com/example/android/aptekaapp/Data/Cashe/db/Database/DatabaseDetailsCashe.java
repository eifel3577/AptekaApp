package com.example.android.aptekaapp.Data.Cashe.db.Database;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.android.aptekaapp.Data.Cashe.DragCashe;
import com.example.android.aptekaapp.Data.Entity.DragEntity;
import com.example.android.aptekaapp.Data.Entity.DragEntityDetails;
import com.example.android.aptekaapp.Presentation.AndroidApplication;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class DatabaseDetailsCashe implements DragCashe<DragEntityDetails> {

    /**время, которое информация будет хранится в кеше.В данном случае 10 минут */
    private static final long EXPIRATION_TIME = 60 * 10 * 1000;
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE_DETAILS = "last_cache_update_details";
    private static final String PREFERENCE_IDENTIFICATOR_DETAILS = "pref_ident_details";
    private static final String DATA_IN_DATABASE = "data_in_database";
    private static final String NO_DATA_IN_DATABASE = "no_data_in_database";

    private final Context context;
    MyDatabase dragstoreServiceDatabase;
    DragDetailsDao detailsDao;
    private String isDataInDatabase;

    @Inject
    public DatabaseDetailsCashe(Context context) {
        this.context = context;
        //TODO заменить на получение инстанса MyDatabase из даггера
        dragstoreServiceDatabase = AndroidApplication.getInstance().getDatabase();
        detailsDao = dragstoreServiceDatabase.dragDetailsDao();
    }

    @Override
    public void evictAll() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                detailsDao.clearDetailsTable();
            }
        });
        thread.start();
    }

    @Override
    public Observable<List<DragEntityDetails>> get(String groupName) {
        return detailsDao.getListDragDetails(groupName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapObservable(new Function<List<DragEntityDetails>, ObservableSource<List<DragEntityDetails>>>() {
                    @Override
                    public ObservableSource<List<DragEntityDetails>> apply(@NonNull List<DragEntityDetails> dragEntityDetailses) throws Exception {
                        return Observable.fromArray(dragEntityDetailses);
                    }
                });
    }

    @Override
    public void put(List<DragEntityDetails> list) {
        detailsDao.insertDragDetailList(list);
        setLastCacheUpdateTimeMillis();
    }

    @Override
    public boolean isCached(String dragTitle) {

        isDataAvailable(dragTitle);
        if(isDataInDatabase!=null){
            if(isDataInDatabase.equals(DATA_IN_DATABASE)){
                return true;
            }
            return false;
        }
        return false;
    }

    private void isDataAvailable(String dragTitle){
        detailsDao.getListDragDetails(dragTitle)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<DragEntityDetails>>() {
                    @Override
                    public void accept(@NonNull List<DragEntityDetails> dragEntityDetailses) throws Exception {
                        if(dragEntityDetailses.size()>0) {
                            isDataInDatabase = DATA_IN_DATABASE;
                        }
                        else isDataInDatabase = NO_DATA_IN_DATABASE;
                    }
                });
    }

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
        final SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_IDENTIFICATOR_DETAILS,
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(SETTINGS_KEY_LAST_CACHE_UPDATE_DETAILS, currentMillis);
        editor.apply();
    }

    /**
     * Достает из преференса временную метку
     */
    private long getLastCacheUpdateTimeMillis() {
        final SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_IDENTIFICATOR_DETAILS,
                Context.MODE_PRIVATE);
        return sharedPreferences.getLong(SETTINGS_KEY_LAST_CACHE_UPDATE_DETAILS, 0);
    }

}
