package com.example.android.aptekaapp.Data.Entity.Mapper;


import com.example.android.aptekaapp.Data.Entity.DragEntityDetails;
import com.example.android.aptekaapp.Domain.DragDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DragEntityDetailsMapper {

    @Inject
    public DragEntityDetailsMapper() {
    }

    public DragDetails transformDragEntityDetailsToDragDetails(DragEntityDetails dragEntityDetails){
        DragDetails dragDetails = null;
        if(dragEntityDetails!=null){
            dragDetails = new DragDetails();
            dragDetails.setGeneralInformation(dragEntityDetails.getGeneralInformation());
            dragDetails.setInstruction(dragEntityDetails.getInstruction());
            //dragDetails.setProperties(dragEntityDetails.getProperties());
        }
        return dragDetails;
    }

    public List<DragDetails> transformListDragEntityDetailsToListDragDetails(Collection<DragEntityDetails> entityDetailsList){
        List<DragDetails>resultList = new ArrayList<>();
        for(DragEntityDetails entityDetails:entityDetailsList){
            final DragDetails dragDetails = transformDragEntityDetailsToDragDetails(entityDetails);
            if(dragDetails!=null){
                resultList.add(dragDetails);
            }
        }
        return resultList;
    }
}
