package com.example.android.aptekaapp.Presentation.Presenter;


import android.support.annotation.NonNull;

import com.example.android.aptekaapp.Domain.DragDetails;
import com.example.android.aptekaapp.Domain.Extension.DefaultErrorBundle;
import com.example.android.aptekaapp.Domain.Extension.ErrorBundle;
import com.example.android.aptekaapp.Domain.Interactor.DefaultObserver;
import com.example.android.aptekaapp.Domain.Interactor.GetDragDetails;
import com.example.android.aptekaapp.Presentation.Extension.ErrorMessageFactory;
import com.example.android.aptekaapp.Presentation.Mapper.DragModelDetailsDataMapper;
import com.example.android.aptekaapp.Presentation.View.Activity.DragProductCardActivity;
import com.example.android.aptekaapp.Presentation.View.ApplicationView;

import java.util.List;

import javax.inject.Inject;

/**презентер для получения детальной информации о лекарстве.Полученное инфо кладется в базу */
public class DragProductCardPresenter implements Presenter {

    private boolean showDownloadProgressBar = false;
    private GetDragDetails dragDetails;
    private DragProductCardActivity view;
    private DragModelDetailsDataMapper detailsDataMapper;

    @Inject
    public DragProductCardPresenter(GetDragDetails dragDetails,
                                    DragModelDetailsDataMapper detailsDataMapper) {
        this.dragDetails = dragDetails;
        this.detailsDataMapper = detailsDataMapper;
    }

    public void setView(@NonNull ApplicationView view){
        this.view = (DragProductCardActivity)view;
    }

    public void initialize(String dragTitle) {
        this.showViewLoading();
        this.getDetails(dragTitle);
    }

    private void getDetails(String dragTitle){
        this.dragDetails.execute(new DragDetailsObserver(),GetDragDetails.Params.setDragSearch(dragTitle));
    }


    /**дает View команду показывать View с прогрессбаром индикатором загрузки */
    private void showViewLoading() {
        showDownloadProgressBar = true;
        this.view.showLoading();
    }

    private void hideViewLoading(){
        showDownloadProgressBar = false;
        this.view.hideLoading();
    }

    /**принимает на вход кастомное исключение,с помощью фабрики конвертирует его
     * в удобочитаемое сообщение,дает команду View показать это сообщение
     * пользователю*/
    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.view.getContext(),
                errorBundle.getException());
        this.view.showError(errorMessage);
    }

    @Override
    public void destroy() {
        this.dragDetails.dispose();
        this.view = null;
    }

    @Override
    public void resume() {
        if(showDownloadProgressBar){
            this.showViewLoading();
        }
    }

    @Override
    public void pause() {}

    private final class DragDetailsObserver extends DefaultObserver<List<DragDetails>> {
        @Override
        public void onComplete() {
            DragProductCardPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable exception) {
            DragProductCardPresenter.this.hideViewLoading();
            DragProductCardPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) exception));
        }

        @Override
        public void onNext(List<DragDetails> dragDetailses) {
            //TODO положить в базу
        }
    }
}
