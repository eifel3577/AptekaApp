package com.example.android.aptekaapp.Data.Entity.Mapper;



import com.example.android.aptekaapp.Data.Entity.DragEntity;
import com.example.android.aptekaapp.Domain.Drag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link DragEntity} (in the data layer) to {@link Drag} in the
 * domain layer.
 */
@Singleton
public class DragEntityDataMapper {

    @Inject
    DragEntityDataMapper() {}

    /**
     * Трансформирует {@link DragEntity} into an {@link Drag}.
     *
     * @param dragEntity обьект который будет трансформироваться
     * @return {@link Drag} если пришедший {@link DragEntity} корректен,
     * иначе null.
     */
    public Drag transform(DragEntity dragEntity) {
        Drag drag = null;
        if (dragEntity != null) {
            drag = new Drag();
            drag.setDragName(dragEntity.getDragName());
            drag.setDragPrice(dragEntity.getDragPrice());
            drag.setDragPhoto(dragEntity.getDragPhoto());
            drag.setDragUrl(dragEntity.getDragUrl());

        }
        return drag;
    }

    /**
     * Трансформирует список  {@link DragEntity} в список {@link Drag}.
     *
     * @param dragEntityCollection список который будет трансформироваться
     * @return {@link Drag} Если пришедший {@link DragEntity} корректен,
     * иначе null.
     */
    public List<Drag> transform(Collection<DragEntity> dragEntityCollection) {
        final List<Drag> userList = new ArrayList<>();
        for (DragEntity dragEntity : dragEntityCollection) {
            final Drag drag = transform(dragEntity);
            if (drag != null) {
                userList.add(drag);
            }
        }
        return userList;
    }
}
