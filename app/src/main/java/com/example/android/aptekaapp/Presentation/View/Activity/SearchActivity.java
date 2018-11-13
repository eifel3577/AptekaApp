package com.example.android.aptekaapp.Presentation.View.Activity;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.android.aptekaapp.R;
import com.example.android.aptekaapp.databinding.ActivitySearchBinding;


public class SearchActivity extends BaseActivity {

    private ActivitySearchBinding binding;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, SearchActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        binding.setSearchActivity(this);
    }

    public void clickOnSearchButton(View v){

        if(!TextUtils.isEmpty(binding.searchEditText.getText().toString())) {
            String text = binding.searchEditText.getText().toString();
            this.navigator.navigateToDragList(this, text);
        }
        else Toast.makeText(this,getResources().getString(R.string.please_enter_drag_title),Toast.LENGTH_SHORT).show();

    }
}
