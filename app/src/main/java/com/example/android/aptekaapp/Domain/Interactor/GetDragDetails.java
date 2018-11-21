package com.example.android.aptekaapp.Domain.Interactor;


import com.example.android.aptekaapp.Domain.DragDetails;
import com.example.android.aptekaapp.Domain.Repository.DragRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetDragDetails extends UseCase<List<DragDetails>,GetDragDetails.Params> {

    /**обьект репозитория */
    private final DragRepository dragRepository;

    /**получение зависимости DragRepository  */
    @Inject
    GetDragDetails(DragRepository dragRepository) {
        this.dragRepository = dragRepository;
    }

    @Override
    Observable<List<DragDetails>> buildUseCaseObservable(GetDragDetails.Params params) {
        return this.dragRepository.dragDetails(params.dragSearch);
    }

    public static final class Params {

        private final String dragSearch;

        private Params(String dragSearch) {
            this.dragSearch = dragSearch;
        }

        public static GetDragDetails.Params setDragSearch(String dragSearch) {
            return new GetDragDetails.Params(dragSearch);
        }
    }
}
