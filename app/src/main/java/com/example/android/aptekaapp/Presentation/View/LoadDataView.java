package com.example.android.aptekaapp.Presentation.View;

import android.content.Context;

/**
 * Интерфей представляющий View ,использующие загруженный данные
 */
public interface LoadDataView {
    /**
     * показывает View с прогрессбаром индикатором загрузки
     */
    void showLoading();

    /**
     * скрывает View с прогрессбаром индикатором загрузки
     */
    void hideLoading();

    /**
     * Show a retry view in case of an error when retrieving data.
     * показывает View "Повторить" в случае ошибки при получении данных
     */
    void showRetry();

    /**
     * скрывает View "Повторить"
     */
    void hideRetry();

    /**
     * Показывает сообщение о ошибке
     *
     * @param message строка которая будет отображаться в сообщении
     */
    void showError(String message);

    /**
     * Получение  {@link android.content.Context}.
     */
    Context context();
}