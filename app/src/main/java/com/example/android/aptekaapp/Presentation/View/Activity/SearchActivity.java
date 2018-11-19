package com.example.android.aptekaapp.Presentation.View.Activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.android.aptekaapp.Presentation.DI.Components.DaggerUserComponent;
import com.example.android.aptekaapp.Presentation.DI.Components.UserComponent;
import com.example.android.aptekaapp.Presentation.DI.HasComponent;
import com.example.android.aptekaapp.Presentation.View.Fragment.SearchFragment;
import com.example.android.aptekaapp.R;


public class SearchActivity extends BaseActivity implements HasComponent<UserComponent> {


    private UserComponent userComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container_layout);
        this.initializeActivity();
        this.initializeInjector();
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, SearchActivity.class);
    }

    private void initializeActivity() {
        addFragment(R.id.fragmentContainer, SearchFragment.initFragment());
    }

    private void initializeInjector(){
        this.userComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public UserComponent getComponent() {
        return userComponent;
    }



}
