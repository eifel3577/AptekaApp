package com.example.android.aptekaapp.Presentation.DI.Components;

import android.content.Context;


import com.example.android.aptekaapp.Data.Cashe.db.Database.MyDatabase;
import com.example.android.aptekaapp.Data.Cashe.db.DatabaseCashe;
import com.example.android.aptekaapp.Domain.Repository.DragRepository;
import com.example.android.aptekaapp.Presentation.DI.Modules.ApplicationModule;
import com.example.android.aptekaapp.Presentation.View.Activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * компонент время жизни которого - это время жизни приложения
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);
    void inject(DatabaseCashe databaseCashe);
    //Exposed to sub-graphs.
    Context context();
    DragRepository dragRepository();
    //MyDatabase myDatabase();
}