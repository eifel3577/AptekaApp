package com.example.android.aptekaapp.Presentation.View.Activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;


import com.example.android.aptekaapp.Presentation.AndroidApplication;
import com.example.android.aptekaapp.Presentation.DI.Components.ApplicationComponent;
import com.example.android.aptekaapp.Presentation.DI.Modules.ActivityModule;
import com.example.android.aptekaapp.Presentation.Navigator.Navigator;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity {

    /**получает объект класса Navigator через Dagger */
    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }


    /**Добавляет фрагмент в макет этого активити
     * @param containerViewId контейнер куда добавляется фрагмент
     * @param fragment фрагмент который должен быть добавлен
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(containerViewId, fragment)
                .commit();
    }


    /**Дает Main Application component для внедрения зависимости
     *@return возвращает ApplicationComponent
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Дает Activity module для внедрения зависимости
     *@return возвращает  ActivityModule
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}