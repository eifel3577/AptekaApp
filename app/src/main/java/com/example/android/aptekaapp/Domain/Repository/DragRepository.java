package com.example.android.aptekaapp.Domain.Repository;



import com.example.android.aptekaapp.Domain.Drag;
import com.example.android.aptekaapp.Domain.DragDetails;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

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

    Observable<List<DragDetails>> dragDetails(String dragSearch);
}