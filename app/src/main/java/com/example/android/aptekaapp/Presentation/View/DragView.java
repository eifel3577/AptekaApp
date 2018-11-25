package com.example.android.aptekaapp.Presentation.View;


import android.content.Context;

public interface DragView {
    /**
     * показывает View с прогрессбаром индикатором загрузки
     */
    void showLoading();

    /**
     * скрывает View с прогрессбаром индикатором загрузки
     */
    void hideLoading();

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
