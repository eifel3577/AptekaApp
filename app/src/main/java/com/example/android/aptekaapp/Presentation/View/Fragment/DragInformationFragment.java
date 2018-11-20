package com.example.android.aptekaapp.Presentation.View.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.aptekaapp.R;

public class DragInformationFragment extends Fragment {


    public static DragInformationFragment newInstance(){
        return new DragInformationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.drag_information,container,false);
    }
}
