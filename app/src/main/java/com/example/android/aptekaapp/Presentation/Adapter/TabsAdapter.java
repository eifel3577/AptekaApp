package com.example.android.aptekaapp.Presentation.Adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.aptekaapp.Presentation.View.Fragment.DragInformationFragment;
import com.example.android.aptekaapp.Presentation.View.Fragment.DragInstructionFragment;
import com.example.android.aptekaapp.Presentation.View.Fragment.DragPropertiesFragment;

public class TabsAdapter extends FragmentPagerAdapter {

    private String[] titles = { "Информация",
            "Инструкция","Характеристики"  };

    public TabsAdapter(FragmentManager fm, Context c) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:{
                return DragInformationFragment.newInstance();
            }
            case 1:{
                return DragInstructionFragment.newInstance();
            }
            case 2:{
                return DragPropertiesFragment.newInstance();
            }
        }
        return null;
    }


    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
