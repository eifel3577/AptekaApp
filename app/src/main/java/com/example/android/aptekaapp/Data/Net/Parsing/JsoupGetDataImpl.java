package com.example.android.aptekaapp.Data.Net.Parsing;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


import com.example.android.aptekaapp.Data.Entity.DragEntity;
import com.example.android.aptekaapp.Data.Entity.DragEntityDetails;
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
        return Observable.create(new ObservableOnSubscribe<List<DragEntity>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<DragEntity>> em) throws Exception {
                if (isThereInternetConnection()) {
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
                    em.onError(new NetworkConnectionException());
                }
            }
        });
    }


    private List<DragEntity> getDragEntitiesFromJsoup(String search) throws MalformedURLException {
        return JsoupConnection.createGET(search,"base_search").connectToJsoup();
    }

    private List<DragEntityDetails> getDragEntityDetailsFromJsoup(String searchDetails) throws MalformedURLException{
        return JsoupConnection.createGET(searchDetails,"details_search").connectToJsoup();
    }

    @Override
    public Observable<List<DragEntityDetails>> dragEntityDetailsList(final String searchDetails)  {
        return Observable.create(new ObservableOnSubscribe<List<DragEntityDetails>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<DragEntityDetails>> em) throws Exception {
                if (isThereInternetConnection()) {
                    try {
                        List<DragEntityDetails>dragEntityList = getDragEntitiesFromJsoup(searchDetails);
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
                    em.onError(new NetworkConnectionException());
                }
            }
        });
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