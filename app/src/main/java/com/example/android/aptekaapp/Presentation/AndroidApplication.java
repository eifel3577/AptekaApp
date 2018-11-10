package com.example.android.aptekaapp.Presentation;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.android.aptekaapp.Data.Cashe.db.Database.MyDatabase;
import com.example.android.aptekaapp.Presentation.DI.Components.ApplicationComponent;
import com.example.android.aptekaapp.Presentation.DI.Components.DaggerApplicationComponent;
import com.example.android.aptekaapp.Presentation.DI.Modules.ApplicationModule;


public class AndroidApplication extends Application {

    public static AndroidApplication instance;
    private ApplicationComponent applicationComponent;
    private MyDatabase database;

    @Override public void onCreate() {
        super.onCreate();
        instance = this;
        this.initializeInjector();
        database = Room.databaseBuilder(this, MyDatabase.class, "database")
                .build();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static AndroidApplication getInstance() {
        return instance;
    }

    public MyDatabase getDatabase() {
        return database;
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }


}