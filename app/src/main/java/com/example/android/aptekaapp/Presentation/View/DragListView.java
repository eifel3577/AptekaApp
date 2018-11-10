package com.example.android.aptekaapp.Presentation.View;



import com.example.android.aptekaapp.Presentation.Model.DragModel;

import java.util.Collection;

/**
 * Интерфейс представляющий View в модели MVP.Этот интерфейс реализуют все View работающие с UserModel
 */
public interface DragListView extends LoadDataView {
    /**
     * отображение списка лекарств пользователю
     *
     * @param userModelCollection коллекция{@link DragModel} котораябудет отображаться
     */
    void renderDragList(Collection<DragModel> dragModelCollection);

    /**Просмотр детальной информации о {@link DragModel}
     *
     * @param dragModel юзер детальная информация о котором должна отображаться
     */
    void viewDrag(DragModel dragModel);
}