package com.example.android.aptekaapp.Presentation.DI.Components;

import android.app.Activity;


import com.example.android.aptekaapp.Presentation.DI.Modules.ActivityModule;
import com.example.android.aptekaapp.Presentation.DI.PerActivity;

import dagger.Component;

/**
 * Базовый компонент с помощью которого фрагменты получают зависимости.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
interface ActivityComponent {
    //Exposed to sub-graphs.
    Activity activity();
}