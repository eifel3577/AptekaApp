package com.example.android.aptekaapp.Data.Net.Parsing;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


import com.example.android.aptekaapp.Data.Entity.DragEntity;
import com.example.android.aptekaapp.Data.Exception.NetworkConnectionException;

import java.net.MalformedURLException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Реализация {@link JsoupGetData} для получения данных через парсер */

public class JsoupGetDataImpl implements JsoupGetData {

    private Context context;

    /**
     * @param context {@link android.content.Context}.
     *
     */
    public JsoupGetDataImpl(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context;

    }

    @Override
    public Observable<List<DragEntity>> dragEntityList(final String search) {
        Log.d("2810","JsoupGetDataImpl dragEntityList");
        return Observable.create(new ObservableOnSubscribe<List<DragEntity>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<DragEntity>> em) throws Exception {
                if (isThereInternetConnection()) {
                    Log.d("2810","JsoupGetDataImpl dragEntityList yes connection");
                    try {
                        List<DragEntity>dragEntityList = getDragEntitiesFromJsoup(search);
                        if (dragEntityList != null) {
                            em.onNext(dragEntityList);
                            em.onComplete();
                        } else {
                            em.onError(new NetworkConnectionException());
                        }
                    } catch (Exception e) {
                        em.onError(new NetworkConnectionException(e.getCause()));
                    }
                } else {
                    Log.d("2810","JsoupGetDataImpl dragEntityList no connection ");
                    em.onError(new NetworkConnectionException());
                }
            }
        });
    }


    private List<DragEntity> getDragEntitiesFromJsoup(String search) throws MalformedURLException {
        Log.d("2810","JsoupGetDataImpl getDragEntitiesFromJsoup");
        return JsoupConnection.createGET(search).connectToJsoup();
    }


    /**
     * Проверка подключения к интернету
     *
     * @return true если устройство подключено к интернету,иначе false
     */
    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}