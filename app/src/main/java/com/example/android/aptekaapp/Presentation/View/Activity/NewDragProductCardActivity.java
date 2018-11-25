package com.example.android.aptekaapp.Presentation.View.Activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.android.aptekaapp.Presentation.DI.Components.DaggerUserComponent;
import com.example.android.aptekaapp.Presentation.DI.Components.UserComponent;
import com.example.android.aptekaapp.Presentation.DI.HasComponent;
import com.example.android.aptekaapp.Presentation.View.Fragment.DragDetailFragment;
import com.example.android.aptekaapp.Presentation.View.Fragment.DragListFragment;
import com.example.android.aptekaapp.R;

public class NewDragProductCardActivity extends BaseActivity implements
        HasComponent<UserComponent> {

    private static final String INTENT_EXTRA_DRAG_URL = "intent_extra_drag_url";
    private static final String SAVE_VALUE_DRAG_URL = "save_value_drag_url";

    private String dragUrl;
    private UserComponent userComponent;

    public static Intent getCallingIntent(Context context, String dragUrl) {
        Intent callingIntent = new Intent(context, NewDragProductCardActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_DRAG_URL, dragUrl);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container_layout);
        this.initializeActivity(savedInstanceState);
        this.initializeInjector();
    }

    /**сохранение состояния при повороте экрана.Сохраняет строку поиска */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putString(SAVE_VALUE_DRAG_URL, this.dragUrl);
        }
        super.onSaveInstanceState(outState);
    }

    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.dragUrl = getIntent().getStringExtra(INTENT_EXTRA_DRAG_URL);
            addFragment(R.id.fragmentContainer, DragDetailFragment.getOfDragUrl(dragUrl));
        } else {
            this.dragUrl = savedInstanceState.getString(SAVE_VALUE_DRAG_URL);
        }
    }

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
}
