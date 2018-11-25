package com.example.android.aptekaapp.Presentation.DI.Components;



import com.example.android.aptekaapp.Presentation.DI.Modules.ActivityModule;
import com.example.android.aptekaapp.Presentation.DI.Modules.UserModule;
import com.example.android.aptekaapp.Presentation.DI.PerActivity;
import com.example.android.aptekaapp.Presentation.View.Fragment.DragDetailFragment;
import com.example.android.aptekaapp.Presentation.View.Fragment.DragListFragment;

import dagger.Component;

/**
 * A scope {@link //com.fernandocejas.android10.sample.presentation.internal.di.PerActivity} component.
 * Injects user specific Fragments.
 * Component скоупа PerActivity.Инжектирует зависимости во фрагменты.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserModule.class})
public interface UserComponent extends ActivityComponent {
    void inject(DragListFragment testDragListFragment);
    void inject(DragDetailFragment dragDetailFragment);

}