package com.example.android.aptekaapp.Presentation.Presenter;


import android.support.annotation.NonNull;

import com.example.android.aptekaapp.Domain.Extension.ErrorBundle;
import com.example.android.aptekaapp.Domain.Interactor.GetDragDetails;
import com.example.android.aptekaapp.Presentation.Extension.ErrorMessageFactory;
import com.example.android.aptekaapp.Presentation.Mapper.DragModelDetailsDataMapper;
import com.example.android.aptekaapp.Presentation.View.Activity.DragProductCardActivity;
import com.example.android.aptekaapp.Presentation.View.ApplicationView;

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
        this.loadDragDetails(dragTitle);
    }

    private void loadDragDetails(String dragTitle) {
        this.showViewLoading();
        this.getUserList(dragTitle);
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

    }

    @Override
    public void pause() {

    }
}
