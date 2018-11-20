package com.example.android.aptekaapp.Presentation.View.Activity;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.astuetz.PagerSlidingTabStrip;
import com.example.android.aptekaapp.Presentation.Adapter.TabsAdapter;
import com.example.android.aptekaapp.Presentation.DI.Components.UserComponent;
import com.example.android.aptekaapp.Presentation.DI.HasComponent;
import com.example.android.aptekaapp.Presentation.View.Fragment.DragListFragment;
import com.example.android.aptekaapp.R;
import com.example.android.aptekaapp.databinding.ActivityProductCardBinding;

public class DragProductCardActivity extends BaseActivity
        implements HasComponent<UserComponent> {

    private static final String INTENT_EXTRA_DRAG_NAME = "intent_extra_frag_name";

    private UserComponent userComponent;
    private String dragTitle;
    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    ActivityProductCardBinding productCardBinding;

    public static Intent getCallingIntent(Context context, String dragName) {
        Intent callingIntent = new Intent(context, DragProductCardActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_DRAG_NAME, dragName);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.dragTitle = getIntent().getStringExtra(INTENT_EXTRA_DRAG_NAME);
        this.initializeActivity();
        this.initToolbar();
        this.initView();
    }

    @Override
    public UserComponent getComponent() {
        return null;
    }

    private void initializeActivity() {
        productCardBinding = DataBindingUtil.setContentView(this,R.layout.activity_product_card);
        productCardBinding.setActivityProductCard(this);
    }

    private void initToolbar(){
        setSupportActionBar(productCardBinding.toolbar);
    }

    private void initView(){
        productCardBinding.pager.setAdapter(new TabsAdapter(getSupportFragmentManager(),this));
        productCardBinding.pager.setCurrentItem(0);
        productCardBinding.tabs.setViewPager(productCardBinding.pager);
    }
}
