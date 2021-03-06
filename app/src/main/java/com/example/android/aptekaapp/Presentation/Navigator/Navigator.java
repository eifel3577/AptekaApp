package com.example.android.aptekaapp.Presentation.Navigator;

import android.content.Context;
import android.content.Intent;
import android.util.Log;


import com.example.android.aptekaapp.Presentation.View.Activity.DragSearchActivity;
import com.example.android.aptekaapp.Presentation.View.Activity.SearchActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * Класс обеспечивающий навигацию в приложении
 */
@Singleton
public class Navigator {

    /**класс будет передаваться как зависимость в другие классы */
    @Inject
    public Navigator() { }

    /**
     * направляет пользователя на показ страницы поиска лекарств
     *
     * @param context контекст нужный для открытия соответствующего активити
     */
    public void navigateToDragSearchScreen(Context context) {
        if (context != null) {
            //Intent intentToLaunch = DragSearchActivity.getCallingIntent(context);
            //context.startActivity(intentToLaunch);
        }
    }


    /**
     * направляет пользователя на показ списка лекарств
     *
     * @param context контекст нужный для открытия соответствующего активити
     * @param searchText название лекарства,которое ввел пользователь в строку поиска
     */
    public void navigateToDragList(Context context,String searchText) {

        if (context != null) {
            //создается интент на DragSearchactivity,ему передается строка поиска для инициализации
            Intent intentToLaunch =  DragSearchActivity.getCallingIntent(context,searchText);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToSearchActivity(Context context){
        Intent intentToSearchActivity = SearchActivity.getCallingIntent(context);
        context.startActivity(intentToSearchActivity);
    }

    /**
     * направляет пользователя на показ детельной инфо о лекарстве
     * @param context контекст нужный для открытия соответствующего активити
     */
    public void navigateToDragDetails(Context context, int userId) {
        if (context != null) {
            //Intent intentToLaunch = UserDetailsActivity.getCallingIntent(context, userId);
            //context.startActivity(intentToLaunch);
        }
    }
}