package com.example.android.aptekaapp.Data.Cashe;



import com.example.android.aptekaapp.Data.Entity.DragEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface DragCashe {

    /**
     * Получает  {@link Observable} который будет транслировать {@link DragEntity}.
     *
     */
    Single<List<DragEntity>> get(String groupName);

    /**
     * Кладет список элементов в кэш
     * @param list Список элементов для помещения в кэш
     */
    void put(List<DragEntity> list);

    /**
     * Проверяет находится ли элемент (Drag) в данном кэше
     * @param dragTitle название препарата чтобы искать его в кэше
     * @return true если элемент находится в данном кэше,false в противном случае
     */
    boolean isCached(final String dragTitle);

    /**
     * Проверяет cache is expired
     * @return true, the cache is expired, otherwise false.
     */
    boolean isExpired();

    /**
     * Очищает кэш от всех находящихся в нем элементов
     */
    void evictAll();
}