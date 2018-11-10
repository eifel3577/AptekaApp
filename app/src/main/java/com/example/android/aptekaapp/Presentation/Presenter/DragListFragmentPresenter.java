package com.example.android.aptekaapp.Presentation.Presenter;

import android.util.Log;

import com.android.annotations.NonNull;
import com.example.android.aptekaapp.Domain.Drag;
import com.example.android.aptekaapp.Domain.Extension.DefaultErrorBundle;
import com.example.android.aptekaapp.Domain.Extension.ErrorBundle;
import com.example.android.aptekaapp.Domain.Interactor.DefaultObserver;
import com.example.android.aptekaapp.Domain.Interactor.GetDragList;
import com.example.android.aptekaapp.Presentation.DI.PerActivity;
import com.example.android.aptekaapp.Presentation.Extension.ErrorMessageFactory;
import com.example.android.aptekaapp.Presentation.Mapper.DragModelDataMapper;
import com.example.android.aptekaapp.Presentation.Model.DragModel;
import com.example.android.aptekaapp.Presentation.View.DragListView;


import java.util.Collection;
import java.util.List;

import javax.inject.Inject;



/**
 * Презентер {@link DragListFragmentPresenter} работает с view {@link TestDragListFragment}
 */
@PerActivity
public class DragListFragmentPresenter implements Presenter {

    /**обьект DragListView,с которым работает презентер */
    private DragListView viewListView;

    /**обьект GetUserList.Он находится в другом слое,в Domain */
    private final GetDragList getDragListUseCase;
    /**обьект GetUserList.Он находится в другом слое,в Domain */
    private final DragModelDataMapper dragModelDataMapper;

    /**получение зависимостей {@link GetDragList} и {@link DragModelDataMapper} */
    @Inject
    public DragListFragmentPresenter(GetDragList getUserListUserCase,
                                     DragModelDataMapper dragModelDataMapper) {
        this.getDragListUseCase = getUserListUserCase;
        this.dragModelDataMapper = dragModelDataMapper;
    }

    /**принимает View, с которым взаимодействует */
    public void setView(@NonNull DragListView view) {
        this.viewListView = view;
    }

    @Override public void resume() {}

    @Override public void pause() {}

    /**отписывается от Disposable , обнуляет ссылку на прикрепленное вью {@link TestDragListFragment} */
    @Override public void destroy() {
        this.getDragListUseCase.dispose();
        this.viewListView = null;
    }

    /**
     * @param dragTitle строка поиска
     */
    public void initialize(String dragTitle) {
        Log.d("2810","DragListFragmentPresenter initialize");
        this.loadDragList(dragTitle);
    }

    /**
     * получает
     * @param dragTitle строку поиска
     */
    private void loadDragList(String dragTitle) {
        Log.d("2810","DragListFragmentPresenter loadDragList, dragTitle = "+dragTitle);
        this.hideViewRetry();
        this.showViewLoading();
        this.getUserList(dragTitle);
    }

    /**получает лекарство
     * @param dragModel
     * на котором кликнули */
    public void onDragClicked(DragModel dragModel) {
        this.viewListView.viewDrag(dragModel);
    }

    /**дает View команду показывать View с прогрессбаром индикатором загрузки */
    private void showViewLoading() {
        this.viewListView.showLoading();
    }

    /**дает View команду скрыть View с прогрессбаром индикатором загрузки*/
    private void hideViewLoading() {
        this.viewListView.hideLoading();
    }

    /**дает View команду показать View "Повторить" */
    private void showViewRetry() {
        this.viewListView.showRetry();
    }

    /**дает View команду скрыть View "Повторить" */
    private void hideViewRetry() {
        this.viewListView.hideRetry();
    }

    /**принимает на вход кастомное исключение,с помощью фабрики конвертирует его
     * в удобочитаемое сообщение,дает команду View показать это сообщение
     * пользователю*/
    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.viewListView.context(),
                errorBundle.getException());
        this.viewListView.showError(errorMessage);
    }

    /**получает на вход коллекцию Drag-ов,трансформирует ее в коллекцию
     * DragModel-ей,дает команду View отобразить список DragModel-ей пользователю */
    private void showDragsCollectionInView(Collection<Drag> dragsCollection) {
        final Collection<DragModel> dragModelsCollection =
                this.dragModelDataMapper.transform(dragsCollection);
        this.viewListView.renderDragList(dragModelsCollection);
    }

    /**вызывает метод обьекта слоя Domain GetUserList execute,передает ему
     *  в параметр наблюдатель DragListObserver*/
    private void getUserList(String dragTitle) {
        Log.d("2810","DragListFragmentPresenter getUserList");
        this.getDragListUseCase.execute(new DragListObserver(),GetDragList.Params.setDragSearch(dragTitle) );
    }

    /**наблюдатель наследует интерфейс DefaultObserver.Обрабатывает результаты трансляции,которая приходит от Observable */
    private final class DragListObserver extends DefaultObserver<List<Drag>> {

        /**источник успешно закончил трансляцию*/
        @Override
        public void onComplete() {
            DragListFragmentPresenter.this.hideViewLoading();
        }

        /**ошибка получения данных от источника */
        @Override
        public void onError(Throwable e) {
            DragListFragmentPresenter.this.hideViewLoading();
            DragListFragmentPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            DragListFragmentPresenter.this.showViewRetry();
        }

        /**источник прислал данные (список юзеров) */
        @Override
        public void onNext(List<Drag> drag) {
            DragListFragmentPresenter.this.showDragsCollectionInView(drag);
        }
    }
}