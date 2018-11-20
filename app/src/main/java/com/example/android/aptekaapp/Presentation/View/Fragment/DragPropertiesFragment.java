package com.example.android.aptekaapp.Presentation.View.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.aptekaapp.R;


public class DragPropertiesFragment extends Fragment {

    public static DragPropertiesFragment newInstance(){
        return new DragPropertiesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.drag_properties,container,false);
    }
}
