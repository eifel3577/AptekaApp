package com.example.android.aptekaapp.Presentation.Presenter;


import com.example.android.aptekaapp.Domain.Drag;
import com.example.android.aptekaapp.Domain.Interactor.GetDragList;
import com.example.android.aptekaapp.Presentation.DI.PerActivity;
import com.example.android.aptekaapp.Presentation.View.Activity.SearchActivity;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;

@PerActivity
public class SearchActivityPresenter implements Presenter {

    SearchActivity activity;
    PublishSubject<String> subject = PublishSubject.create();

    @Inject
    public SearchActivityPresenter() {
        initialPublishSubject();
    }

    private void initialPublishSubject(){
        subject.debounce(1000, TimeUnit.MILLISECONDS)
                .switchMap(new Function<String, ObservableSource<List<Drag>>>() {
                    @Override
                    public ObservableSource<List<Drag>> apply(String query) throws Exception {
                        return repository.searchUsers(query);
                    }
                })
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        showUsers(users);
                    }
                });
    }

    public void setView(SearchActivity activity){
        this.activity = activity;
    }

    public void performSearch(String search){
        //get info from net
    }

    @Override
    public void destroy() {
        this.activity = null;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }


}
