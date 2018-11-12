package com.example.android.aptekaapp.Presentation.DI.Modules;

import android.content.Context;


import com.example.android.aptekaapp.Data.Cashe.DragCashe;
import com.example.android.aptekaapp.Data.Cashe.db.Database.DatabaseCashe;
import com.example.android.aptekaapp.Data.Repository.DragDataRepository;
import com.example.android.aptekaapp.Domain.Repository.DragRepository;
import com.example.android.aptekaapp.Presentation.AndroidApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 * Даггер модуль который предоставляет обьекты,которые живут все время жизни приложения.
 */
@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    //TODO реализовать,не работает
    //@Provides
    //@Singleton
    //MyDatabase provideDatabase(){
    //    return Room.databaseBuilder(this.application,MyDatabase.class,"database").build();
    //}

    //@Provides @Singleton
    //ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    //    return jobExecutor;
    //}

    @Provides
    @Singleton
    DragCashe provideDragCache(DatabaseCashe dragCache) {
        return dragCache;
    }

    @Provides
    @Singleton
    DragRepository provideDragRepository(DragDataRepository dragDataRepository) {
        return dragDataRepository;
    }
}