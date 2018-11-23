package com.example.android.aptekaapp.Data.Net.Parsing;



import com.example.android.aptekaapp.Data.Entity.DragEntity;
import com.example.android.aptekaapp.Data.Entity.DragEntityDetails;

import java.util.List;

import io.reactivex.Observable;

/**
 * Интерфейс получения данных через Jsoup
 */

public interface JsoupGetData {

    /**
     * Возвращает {@link Observable} который транслирует список {@link DragEntity}.
     */
    Observable<List<DragEntity>> dragEntityList(final String search);

    Observable<List<DragEntityDetails>> dragEntityDetailsList(final String searchDetails);
}
