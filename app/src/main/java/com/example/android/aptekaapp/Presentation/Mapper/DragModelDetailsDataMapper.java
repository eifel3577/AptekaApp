package com.example.android.aptekaapp.Presentation.Mapper;


import com.example.android.aptekaapp.Domain.DragDetails;
import com.example.android.aptekaapp.Presentation.DI.PerActivity;
import com.example.android.aptekaapp.Presentation.Model.DragModelDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class DragModelDetailsDataMapper {

    @Inject
    public DragModelDetailsDataMapper() {
    }

    public DragModelDetails transformDragDetailsToDragModelDetails(DragDetails dragDetails){
        DragModelDetails dragModelDetails = null;
        if(dragDetails!=null){
            dragModelDetails.setGeneralInformation(dragDetails.getGeneralInformation());
            dragModelDetails.setInstruction(dragDetails.getInstruction());
            //dragModelDetails.setProperties(dragDetails.getProperties());
        }
        return dragModelDetails;
    }

    public List<DragModelDetails> transformListDragDetailsToListDragModelDetails(Collection<DragDetails> dragDetailses){
        List<DragModelDetails>modelDetailses = new ArrayList<>();
        for(DragDetails dragDetails:dragDetailses){
            final DragModelDetails dragModelDetails = transformDragDetailsToDragModelDetails(dragDetails);
            modelDetailses.add(dragModelDetails);
        }
        return modelDetailses;
    }
}
