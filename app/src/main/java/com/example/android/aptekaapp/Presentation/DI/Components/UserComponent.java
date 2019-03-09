package com.example.android.aptekaapp.Presentation.DI.Components;



import com.example.android.aptekaapp.Presentation.DI.Modules.ActivityModule;
import com.example.android.aptekaapp.Presentation.DI.Modules.UserModule;
import com.example.android.aptekaapp.Presentation.DI.PerActivity;
import com.example.android.aptekaapp.Presentation.View.Fragment.DragListFragment;

import dagger.Component;

/**Компонент - это посредник между Activity и модулем. Когда Activity нужен какой-либо объект, она
 *  сообщает об этом компоненту. Компонент знает, какой модуль умеет создавать такой объект, просит
 *  модуль создать объект и передает его в Activity. При этом компонент может использовать другие
 *  модули, чтобы создать всю иерархию объектов, необходимую для создания искомого объекта.
 * A scope {@link //com.fernandocejas.android10.sample.presentation.internal.di.PerActivity} component.
 * Injects user specific Fragments.
 * Component скоупа PerActivity.Инжектирует зависимости во фрагменты.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserModule.class})
public interface UserComponent extends ActivityComponent {
    void inject(DragListFragment testDragListFragment);
}