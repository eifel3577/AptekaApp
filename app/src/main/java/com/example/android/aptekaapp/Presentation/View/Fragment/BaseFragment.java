package com.example.android.aptekaapp.Presentation.View.Fragment;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.android.aptekaapp.Presentation.DI.HasComponent;


/**
 * Основной класс родитель для всех фрагментов в приложении
 */
public abstract class BaseFragment extends Fragment {
    /**
     * Показывает {@link android.widget.Toast} сообщение
     *
     * @param message строка,которая будет показана в сообщении
     */
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Дает компонент для DI по данному типу
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        //принимает класс компонент
        //кастует его к нужному классу
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}