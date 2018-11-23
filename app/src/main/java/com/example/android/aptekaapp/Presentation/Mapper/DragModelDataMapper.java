package com.example.android.aptekaapp.Presentation.Mapper;

/**
 * Маппер используется для трансформации Drag (из Domain слоя) в DragModel из Presentation слоя
 */



import com.example.android.aptekaapp.Domain.Drag;
import com.example.android.aptekaapp.Presentation.DI.PerActivity;
import com.example.android.aptekaapp.Presentation.Model.DragModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

/**метка указывает на скоуп уровня активити */
@PerActivity
public class DragModelDataMapper {

    /**класс предоставляется другим классам как зависимость */
    @Inject
    public DragModelDataMapper() {}

    /**
     * Трансформирует {@link Drag} в {@link DragModel}.
     * @param drag обьект типа Drag
     * @return {@link DragModel}. обьект типа UserModel
     */
    public DragModel transform(Drag drag) {
        if (drag == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final DragModel dragModel = new DragModel();
        dragModel.setDragName(drag.getDragName());
        dragModel.setDragPrice(drag.getDragPrice());
        dragModel.setDragPhoto(drag.getDragPhoto());
        dragModel.setDragUrl(drag.getDragUrl());
        return dragModel;
    }

    /**
     * Трансформирует коллекцию Drag-ов {@link Drag}  в коллекцию
     * DragModel-ей {@link DragModel}
     * @param dragsCollection коллекция Drag-ов
     * @return List of {@link DragModel}. коллекция DragModel-ей
     */
    public Collection<DragModel> transform(Collection<Drag> dragsCollection) {
        Collection<DragModel> dragModelsCollection;

        if (dragsCollection != null && !dragsCollection.isEmpty()) {
            dragModelsCollection = new ArrayList<>();
            for (Drag drag : dragsCollection) {
                dragModelsCollection.add(transform(drag));
            }
        } else {
            dragModelsCollection = Collections.emptyList();
        }

        return dragModelsCollection;
    }
}