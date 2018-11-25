package com.example.android.aptekaapp.Presentation.View.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.example.android.aptekaapp.Presentation.DI.Components.UserComponent;
import com.example.android.aptekaapp.Presentation.Presenter.DragProductCardPresenter;
import com.example.android.aptekaapp.Presentation.View.DragListView;
import com.example.android.aptekaapp.Presentation.View.DragView;
import com.example.android.aptekaapp.databinding.TestDragListFragmentBinding;

import javax.inject.Inject;

public class DragDetailFragment extends BaseFragment implements DragView {

    private static final String DRAG_URL = "drag_url";

    @Inject
    DragProductCardPresenter dragProductCardPresenter;

    private PagerSlidingTabStrip tabs;
    private ViewPager pager;

    public static DragDetailFragment getOfDragUrl(String dragUrl) {
        final DragDetailFragment dragDetailFragment = new DragDetailFragment();
        final Bundle arguments = new Bundle();
        arguments.putString(DRAG_URL, dragUrl);
        dragDetailFragment.setArguments(arguments);
        return dragDetailFragment;
    }

    /**сохранение состояния при поворотах */
    public DragDetailFragment() {
        setRetainInstance(true);
    }

    /**инициализация обьекта даггера */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(UserComponent.class).inject(this);
    }

    /**инициализация биндинга */
    //@Override
    //public View onCreateView(LayoutInflater inflater, ViewGroup container,
    //                         Bundle savedInstanceState) {


    //}


    @Override
    public Context context() {
        return null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }
}
