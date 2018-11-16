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

import com.example.android.aptekaapp.Presentation.DI.Components.UserComponent;
import com.example.android.aptekaapp.Presentation.Presenter.SearchActivityPresenter;
import com.example.android.aptekaapp.Presentation.View.DragListView;
import com.example.android.aptekaapp.R;
import com.example.android.aptekaapp.databinding.ActivitySearchBinding;

import javax.inject.Inject;


public class SearchActivity extends BaseActivity {

    private ActivitySearchBinding binding;


    @Inject
    SearchActivityPresenter presenter;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, SearchActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        binding.setSearchActivity(this);
        this.presenter.setView(this);
        binding.searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void clickOnSearchButton(View v){

        if(!TextUtils.isEmpty(binding.searchEditText.getText().toString())) {
            String text = binding.searchEditText.getText().toString();
            this.navigator.navigateToDragList(this, text);
        }
        else Toast.makeText(this,getResources().getString(R.string.please_enter_drag_title),Toast.LENGTH_SHORT).show();

    }


}
