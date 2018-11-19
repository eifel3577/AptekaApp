package com.example.android.aptekaapp.Presentation.Presenter;


import android.util.Log;

import com.example.android.aptekaapp.Domain.Drag;
import com.example.android.aptekaapp.Domain.Extension.DefaultErrorBundle;
import com.example.android.aptekaapp.Domain.Interactor.DefaultObserver;
import com.example.android.aptekaapp.Domain.Interactor.GetDragList;
import com.example.android.aptekaapp.Presentation.DI.PerActivity;
import com.example.android.aptekaapp.Presentation.Mapper.DragModelDataMapper;
import com.example.android.aptekaapp.Presentation.View.Activity.SearchActivity;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;

@PerActivity
public class SearchFragmentPresenter implements Presenter {

    private SearchActivity activity;
    private final GetDragList getDragListUseCase;
    private final DragModelDataMapper dragModelDataMapper;
    private List<Drag>resultList;

    PublishSubject<String> subject = PublishSubject.create();

    @Inject
    public SearchFragmentPresenter(GetDragList getUserListUserCase,
                                   DragModelDataMapper dragModelDataMapper) {
        this.getDragListUseCase = getUserListUserCase;
        this.dragModelDataMapper = dragModelDataMapper;
    }



    public void initialPublishSubject(){
        subject.debounce(1000, TimeUnit.MILLISECONDS)
                .switchMap(new Function<String, ObservableSource<List<Drag>>>() {
                    @Override
                    public ObservableSource<List<Drag>> apply(String query) throws Exception {
                        return Observable.fromArray(resultList);
                        //return repository.searchUsers(query);
                    }
                })
                .subscribe(new Consumer<List<Drag>>() {
                    @Override
                    public void accept(List<Drag> drags) throws Exception {
                        //TODO it's test implementation, rewrite after testing
                        //activity.showUsers(drags);
                    }
                });
    }

    public void setView(SearchActivity activity){
        this.activity = activity;
    }

    /**вызывает метод обьекта слоя Domain GetDragList execute,передает ему
     *  в параметр наблюдатель DragListObserver*/
    public void getUserList(String dragTitle) {
        this.getDragListUseCase.execute(new DragListObserver(),GetDragList.Params.setDragSearch(dragTitle) );
    }

    @Override
    public void destroy() {
        this.activity = null;
    }

    @Override
    public void resume() {}

    @Override
    public void pause() {}

    /**наблюдатель наследует интерфейс DefaultObserver.Обрабатывает результаты трансляции,которая приходит от Observable */
    private final class DragListObserver extends DefaultObserver<List<Drag>> {

        /**источник успешно закончил трансляцию*/
        @Override
        public void onComplete() {}

        /**ошибка получения данных от источника */
        @Override
        public void onError(Throwable e) {}

        /**источник прислал данные (список юзеров) */
        @Override
        public void onNext(List<Drag> drag) {
            resultList = drag;
            //DragListFragmentPresenter.this.showDragsCollectionInView(drag);
        }
    }


}
