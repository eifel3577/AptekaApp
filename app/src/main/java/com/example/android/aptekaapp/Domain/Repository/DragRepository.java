package com.example.android.aptekaapp.Domain.Repository;



import com.example.android.aptekaapp.Domain.Drag;

import java.util.List;

import io.reactivex.Observable;

/**
 * Интерфейс который представляет собой Репозиторий для получения {@link Drag}
 * Этот интерфейс является мостом между слоем Domain и слоем Data.Обьект слоя Data {@link DragDataRepository}
 * реализовывает этот интерфейс,таким образом организовывается взаимодействие между слоями
 */
public interface DragRepository {
    /**
     * @return  {@link Observable} который транслирует список {@link Drag}.
     * @param dragSearch строка поиска,по которой будет осуществлятся построение списка лекарств
     */
    Observable<List<Drag>> drags(String dragSearch);

    /**
     * @return  {@link Observable} который будет транслировать конкретного {@link Drag}.
     * @param dragId ID юзера который будет транслироваться
     */
    //TODO получение конкретного лекарства по названию реализовать позже
    //Observable<Drag> drag(final int dragId);
}