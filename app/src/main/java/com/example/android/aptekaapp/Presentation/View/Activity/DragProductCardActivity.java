package com.example.android.aptekaapp.Presentation.View.Activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.android.aptekaapp.Presentation.DI.Components.UserComponent;
import com.example.android.aptekaapp.Presentation.DI.HasComponent;
import com.example.android.aptekaapp.Presentation.View.Fragment.DragListFragment;
import com.example.android.aptekaapp.R;

public class DragProductCardActivity extends BaseActivity
        implements HasComponent<UserComponent> {

    private static final String INTENT_EXTRA_DRAG_NAME = "intent_extra_frag_name";

    private UserComponent userComponent;
    private String dragTitle;

    public static Intent getCallingIntent(Context context, String dragName) {
        Intent callingIntent = new Intent(context, DragProductCardActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_DRAG_NAME, dragName);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dragTitle = getIntent().getStringExtra(INTENT_EXTRA_DRAG_NAME);
    }

    @Override
    public UserComponent getComponent() {
        return null;
    }

    private void initializeActivity(Bundle savedInstanceState) {
        addFragment(R.id.fragmentContainer, DragListFragment.getOfDragTitle(dragTitle));
    }
}
