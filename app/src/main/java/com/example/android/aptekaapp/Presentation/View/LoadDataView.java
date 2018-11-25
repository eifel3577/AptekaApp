package com.example.android.aptekaapp.Presentation.View;

import android.content.Context;

/**
 * Интерфей представляющий View ,использующие загруженный данные
 */
public interface LoadDataView extends DragView {


    /**
     * Show a retry view in case of an error when retrieving data.
     * показывает View "Повторить" в случае ошибки при получении данных
     */
    void showRetry();

    /**
     * скрывает View "Повторить"
     */
    void hideRetry();

    /**показывает View с экраном Уточнить поиск */
    void showSpecifyScreen();

    /**скрывает View с экраном Уточнить поиск  */
    void hideSpecifyScreen();


}