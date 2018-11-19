package com.example.android.aptekaapp.Presentation.View.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.aptekaapp.Presentation.DI.Components.UserComponent;
import com.example.android.aptekaapp.Presentation.Presenter.SearchFragmentPresenter;

import javax.inject.Inject;

public class SearchFragment extends BaseFragment {

    @Inject
    SearchFragmentPresenter presenter;

    public SearchFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.getComponent(UserComponent.class).i
    }

    public static SearchFragment initFragment(){
        return new SearchFragment();
    }


}
