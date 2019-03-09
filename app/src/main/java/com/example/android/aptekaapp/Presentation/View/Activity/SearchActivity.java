package com.example.android.aptekaapp.Presentation.View.Activity;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.android.aptekaapp.R;
import com.example.android.aptekaapp.databinding.ActivitySearchBinding;

/** лаунчер */
public class SearchActivity extends BaseActivity {

    //биндинг для работы с макетом
    private ActivitySearchBinding binding;

    //возвращает интент на самого себя
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, SearchActivity.class);
    }

    //настройка биндинга
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        //привязка активити к биндингу
        binding.setSearchActivity(this);
    }

    //обработчик нажатия на кнопку "поиск".Вызывается из биндинга.Получает данные из EditText,
    //вызывает обьект навигатора и передает ему текст поиска
    public void clickOnSearchButton(View v){
        //если строка поиска не пуста
        if(!TextUtils.isEmpty(binding.searchEditText.getText().toString())) {
            //получение текста из строки поиска и передача ее навигатору
            String text = binding.searchEditText.getText().toString();
            this.navigator.navigateToDragList(this, text);
        }
        //если трока поиска пуста,выводится сообщение пользователю
        else Toast.makeText(this,getResources().getString(R.string.please_enter_drag_title),Toast.LENGTH_SHORT).show();

    }
}
