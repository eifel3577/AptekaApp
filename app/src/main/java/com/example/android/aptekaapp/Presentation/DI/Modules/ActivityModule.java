package com.example.android.aptekaapp.Presentation.DI.Modules;

import android.app.Activity;


import com.example.android.aptekaapp.Presentation.DI.PerActivity;

import dagger.Module;
import dagger.Provides;

/**Модули - это просто классы, куда мы помещаем код создания объектов
 * модуль,возвращающий зависимость "активити"
 *
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    /**
     * Предоставляет activity для зависимостей,которые в нем нуждаются
     */
    @Provides
    @PerActivity
    Activity activity() {
        return this.activity;
    }
}