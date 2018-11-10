package com.example.android.aptekaapp.Data.Repository.DataSource;



import com.example.android.aptekaapp.Data.Entity.DragEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Интерфейс который представляет хранилище данных, полученных
 * из различных источников
 */
public interface DragDataStore {

    /**
     * Получает {@link Observable} который будет транслировать конкретное
     *  {@link DragEntity} по его dragTitle
     * @param  dragTitle ,название препарата по которому будет получаться {@link DragEntity}
     */
    Observable<List<DragEntity>> dragEntityList(final String dragTitle);
}