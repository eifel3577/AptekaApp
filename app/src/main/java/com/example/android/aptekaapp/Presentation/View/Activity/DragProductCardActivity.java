package com.example.android.aptekaapp.Presentation.View.Activity;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.example.android.aptekaapp.Presentation.Adapter.TabsAdapter;

import com.example.android.aptekaapp.Presentation.DI.Components.DaggerUserComponent;
import com.example.android.aptekaapp.Presentation.DI.Components.UserComponent;
import com.example.android.aptekaapp.Presentation.DI.HasComponent;
import com.example.android.aptekaapp.Presentation.Presenter.DragProductCardPresenter;
import com.example.android.aptekaapp.Presentation.View.ApplicationView;
import com.example.android.aptekaapp.Presentation.View.Fragment.DragListFragment;

import com.example.android.aptekaapp.R;
import com.example.android.aptekaapp.databinding.ActivityProductCardBinding;

import javax.inject.Inject;


public class DragProductCardActivity extends BaseActivity
        implements ApplicationView, HasComponent<UserComponent> {

    private static final String INTENT_EXTRA_DRAG_NAME = "intent_extra_frag_name";

    @Inject
    DragProductCardPresenter dragProductCardPresenter;

    private UserComponent userComponent;
    private String dragUrl;
    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private ActivityProductCardBinding productCardBinding;

    public static Intent getCallingIntent(Context context, String dragName) {
        Intent callingIntent = new Intent(context, DragProductCardActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_DRAG_NAME, dragName);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.dragUrl = getIntent().getStringExtra(INTENT_EXTRA_DRAG_NAME);
        this.initializeActivity();
        this.initializeInjector();
        this.initView();
        this.initPresenter(this.dragUrl);
    }

    public void initPresenter(String dragUrl){
        if(dragProductCardPresenter!=null){
            this.dragProductCardPresenter.initialize(dragUrl);
        }
    }

    public void showLoading(){
        this.productCardBinding.rlProgress.setVisibility(View.VISIBLE);
        this.setProgressBarIndeterminateVisibility(true);
    }

    public void hideLoading(){
        this.productCardBinding.rlProgress.setVisibility(View.GONE);
        this.setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public UserComponent getComponent() {
        return null;
    }

    private void initializeActivity() {
        productCardBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_card);
        productCardBinding.setActivityProductCard(this);
    }

    /**строит компонент userComponent.ApplicationComponent и ActivityModule получает из BaseActivity */
    private void initializeInjector() {
        this.userComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    public Context getContext(){
        return this.getApplicationContext();
    }

    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    private void initView(){
        productCardBinding.pager.setAdapter(new TabsAdapter(getSupportFragmentManager(),this));
        productCardBinding.pager.setCurrentItem(0);
        productCardBinding.tabs.setViewPager(productCardBinding.pager);
    }
}
