package com.example.android.aptekaapp.Presentation.View.Activity;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.android.aptekaapp.Domain.Drag;
import com.example.android.aptekaapp.Presentation.DI.Components.DaggerUserComponent;
import com.example.android.aptekaapp.Presentation.DI.Components.UserComponent;
import com.example.android.aptekaapp.Presentation.DI.HasComponent;
import com.example.android.aptekaapp.Presentation.Presenter.SearchActivityPresenter;
import com.example.android.aptekaapp.Presentation.View.DragListView;
import com.example.android.aptekaapp.R;
import com.example.android.aptekaapp.databinding.ActivitySearchBinding;

import java.util.List;

import javax.inject.Inject;


public class SearchActivity extends BaseActivity implements HasComponent<UserComponent> {

    @Inject
    SearchActivityPresenter presenter;

    private ActivitySearchBinding binding;
    private UserComponent userComponent;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, SearchActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initializeInjector();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        binding.setSearchActivity(this);
        this.presenter.setView(this);
        this.presenter.initialPublishSubject();
        binding.searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                presenter.getUserList(s.toString());
            }
        });
    }

    /**строит компонент userComponent.ApplicationComponent и ActivityModule получает из BaseActivity */
    private void initializeInjector() {
        this.userComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public UserComponent getComponent() {
        return userComponent;
    }

    //TODO remove after testing
    public void showUsers(List<Drag> list){
        for(Drag drag:list) {
            Log.d("1711", drag.getDragName());
        }
    }

    public void clickOnSearchButton(View v){

        if(!TextUtils.isEmpty(binding.searchEditText.getText().toString())) {
            String text = binding.searchEditText.getText().toString();
            this.navigator.navigateToDragList(this, text);
        }
        else Toast.makeText(this,getResources().getString(R.string.please_enter_drag_title),Toast.LENGTH_SHORT).show();

    }


}
