package com.example.android.aptekaapp.Presentation.View.Fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.android.aptekaapp.Presentation.DI.Components.UserComponent;
import com.example.android.aptekaapp.Presentation.Model.DragModel;
import com.example.android.aptekaapp.Presentation.Presenter.DragListFragmentPresenter;
import com.example.android.aptekaapp.Presentation.View.Adapter.DragListAdapter;
import com.example.android.aptekaapp.Presentation.View.Adapter.DragsLayoutManager;
import com.example.android.aptekaapp.Presentation.View.DragListView;
import com.example.android.aptekaapp.databinding.TestDragListFragmentBinding;

import java.util.Collection;

import javax.inject.Inject;


/**Фрагмент,отображающий список лекарств */
public class DragListFragment extends BaseFragment implements DragListView {

    private static final String PARAM_DRAG_TITLE = "param_drag_title";

    TestDragListFragmentBinding binding;

    @Inject
    DragListFragmentPresenter presenter;

    @Inject
    DragListAdapter dragsAdapter;

    /**обьект интерфейса,общего с активити хостом.Через этот интерфейс в активити хост будет идити команда,когда пользователь
     * нажмет на конкретном лекарстве в списке */
    private DragListListener dragListListener;
    /**обьект интерфейса,общего с активити хостом.Через этот интерфейс в активити хост будет идити команда,когда пользователь
     * нажмет на кнопку Повторить */
    private RetryButtonClickListener retryButtonClickListener;

    /**
     * Реализация интерфейса нажатия на конкретном лекарстве
     */
    public interface DragListListener {
        void onDragClicked(final DragModel dragModel);
    }

    /**реализация интерфейса нажатия на кнопку Повторить */
    public interface RetryButtonClickListener {
        void onRetryButtonClick();
    }

    /**настройка общего интерфейса с активити хостом */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof DragListListener&&activity instanceof RetryButtonClickListener) {
            this.dragListListener = (DragListListener) activity;
            this.retryButtonClickListener = (RetryButtonClickListener) activity;
        }
    }

    /**инициализация фрагмента, получение строки поиска
     * @param dragTitle строка поиска
     * @return DragListFragment */
    public static DragListFragment getOfDragTitle(String dragTitle) {
        final DragListFragment dragListFragment = new DragListFragment();
        final Bundle arguments = new Bundle();
        arguments.putString(PARAM_DRAG_TITLE, dragTitle);
        dragListFragment.setArguments(arguments);
        return dragListFragment;
    }

    /**сохранение состояния при поворотах */
    public DragListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(UserComponent.class).inject(this);
    }

    /**возвращает строку поиска из Bundle
     * @return строка поиска */
    private String currentDragTitle() {
        final Bundle arguments = getArguments();
        return arguments.getString(PARAM_DRAG_TITLE);
    }

    /**инициализация биндинга */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = TestDragListFragmentBinding.inflate(inflater,container,false);
        binding.setDragListFragment(this);
        setupRecyclerView();
        return binding.getRoot();
    }

    /**передача экземпляра DragListFragment в презентер для взаимодействия с ним.
     * Если savedInstanceState == null ,то есть есди экран не поворачивался (первая загрузка),
     * вызывается loadDragListByTitle(currentDragTitle()*/
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        this.presenter.setView(this);
        if (savedInstanceState == null) {
            this.loadDragListByTitle(currentDragTitle());
        }
    }

    /**настройка RecyclerView */
    private void setupRecyclerView() {

        dragsAdapter.setOnItemClickListener(onItemClickListener);
        binding.rvUsers.setLayoutManager(new DragsLayoutManager(context()));
        binding.rvUsers.setAdapter(dragsAdapter);
    }

    @Override public void onResume() {
        super.onResume();
        this.presenter.resume();
    }

    @Override public void onPause() {
        super.onPause();
        this.presenter.pause();
    }

    /**обнуляется адаптер,отключается биндер */
    @Override public void onDestroyView() {
        super.onDestroyView();
        binding.rvUsers.setAdapter(null);
        binding.unbind();
    }

    /**отключается презентер */
    @Override public void onDestroy() {
        super.onDestroy();
        this.presenter.destroy();
    }

    /**отключается слушатель нажатий на списке */
    @Override
    public void onDetach() {
        super.onDetach();
        this.dragListListener = null;
    }

    /**если коллекция не нулевая,отдает ее адаптеру для обработки
     * @param  dragModelCollection коллекция обьектов DragModel*/
    @Override
    public void renderDragList(Collection<DragModel> dragModelCollection) {

        if(dragModelCollection!= null){
            this.dragsAdapter.setDragsCollection(dragModelCollection);
        }
    }

    /**получает обьект DragModel,проверяет активный ли лисенер dragListListener,если да отдает
     * ему DragModel,на котором кликнули
     * @param dragModel обьект лекарства на котором кликнули*/
    @Override
    public void viewDrag(DragModel dragModel) {

        if (this.dragListListener != null) {
            this.dragListListener.onDragClicked(dragModel);
        }
    }

    /**
     * Загрузка лекарств,дает команду презентеру запускать загрузку
     */
    private void loadDragListByTitle(String dragTitle) {

        if(this.presenter!=null) {
            Log.d("2810","DragListFragment loadDragListByTitle");
            this.presenter.initialize(dragTitle);
        }
    }

    @Override
    public Context context() {
        return this.getActivity().getApplicationContext();
    }

    @Override
    public void showLoading() {
        binding.rlProgress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {

        binding.rlProgress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void showRetry() {
        binding.rlRetry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {

        binding.rlRetry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {

        this.showToastMessage(message);
    }

    /**нажатие на кнопке повторить */
    public void onButtonRetryClick(View v) {
        Log.d("1111","DragListFragment onButtonRetryClick");
        if(retryButtonClickListener!=null){
            this.retryButtonClickListener.onRetryButtonClick();
        }
    }


    private DragListAdapter.OnItemClickListener onItemClickListener =
            new DragListAdapter.OnItemClickListener() {
                @Override public void onUserItemClicked(DragModel dragModel) {
                    if (DragListFragment.this.presenter != null && dragModel != null) {
                        DragListFragment.this.presenter.onDragClicked(dragModel);
                    }
                }
            };
}
