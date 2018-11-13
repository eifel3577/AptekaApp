package com.example.android.aptekaapp.Domain.Interactor;

import android.util.Log;


import com.example.android.aptekaapp.Domain.Drag;
import com.example.android.aptekaapp.Domain.Repository.DragRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Drag}.
 *
 * Этот класс-интерактор является имплементацией {@link UseCase} .Данная имплементация это Use Case для
 * получения коллекции {@link Drag}.
 */
public class GetDragList extends UseCase<List<Drag>, GetDragList.Params> {

    /**обьект репозитория */
    private final DragRepository userRepository;

    /**получение зависимости DragRepository  */
    @Inject
    GetDragList(DragRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**реализация метода родителя.Создает Observable */
    @Override
    Observable<List<Drag>> buildUseCaseObservable(Params params) {
        return this.userRepository.drags(params.dragSearch);
    }

    public static final class Params {

        private final String dragSearch;

        private Params(String dragSearch) {
            this.dragSearch = dragSearch;
        }

        public static Params setDragSearch(String dragSearch) {
            return new Params(dragSearch);
        }
    }
}