package com.example.android.aptekaapp.Presentation.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.android.aptekaapp.Presentation.DI.Components.DaggerUserComponent;
import com.example.android.aptekaapp.Presentation.DI.Components.UserComponent;
import com.example.android.aptekaapp.Presentation.DI.HasComponent;
import com.example.android.aptekaapp.Presentation.Model.DragModel;
import com.example.android.aptekaapp.Presentation.View.Fragment.DragListFragment;
import com.example.android.aptekaapp.R;


public class DragListActivity extends BaseActivity
        implements HasComponent<UserComponent>,
        DragListFragment.SpecifyButtonClickListener,DragListFragment.DragListListener {

    /**константа для строки поиска,которая пришла в интенте */
    private static final String INTENT_EXTRA_PARAM_SEARCH_STRING = "dragstoreserviceapp.INTENT_EXTRA_PARAM_SEARCH_STRING";
    /**константа для сохранения строки поиска при повороте экрана */
    private static final String INSTANCE_STATE_SEARCH_STRING = "dragstoreserviceapp.INSTANCE_STATE_SEARCH_STRING";

    private String searchString;
    private UserComponent userComponent;

    /**обработка интента,с помощью которого было вызвано текущее активити DragListActivity
     * @param context контекст ,необходим для передачи интента
     * @param searchString строка поиска,введеная пользователем в SearchActivity */
    public static Intent getCallingIntent(Context context, String searchString) {
        Log.d("2810","DragListActivity getCallingIntent");
        Intent callingIntent = new Intent(context, DragListActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_SEARCH_STRING, searchString);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("2810","DragListActivity onCreate");
        setContentView(R.layout.fragment_container_layout);
        this.initializeActivity(savedInstanceState);
        this.initializeInjector();
    }

    /**сохранение состояния при повороте экрана.Сохраняет строку поиска */
    @Override protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putString(INSTANCE_STATE_SEARCH_STRING, this.searchString);
        }
        super.onSaveInstanceState(outState);
    }

    /**получает строку поиска,открывает DragListFragment, отдает ему строку поиска
     *  в метод DragListFragment.getOfDragTitle(searchString)
     *  @param savedInstanceState Bundle ,хранит строку поиска при повороте экрана*/
    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.searchString = getIntent().getStringExtra(INTENT_EXTRA_PARAM_SEARCH_STRING);
            Log.d("2810","DragListActivity initializeActivity, searchString = "+searchString);
            addFragment(R.id.fragmentContainer, DragListFragment.getOfDragTitle(searchString));
        } else {
            this.searchString = savedInstanceState.getString(INSTANCE_STATE_SEARCH_STRING);
        }
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

    /**обработка нажатия на кнопку Повторить в DragListFragment.Вызывает SearchActivity
     * чтобы пользователь мог снова ввести запрос */
    @Override
    public void onSpecifyButtonClick() {
        this.navigator.navigateToSearchActivity(this);
    }

    /**обработка нажатия на конкретном лекарстве в списке */
    @Override
    public void onDragClicked(DragModel dragModel) {

    }
}
