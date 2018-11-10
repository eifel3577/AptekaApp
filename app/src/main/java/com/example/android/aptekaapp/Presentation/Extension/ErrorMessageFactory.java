package com.example.android.aptekaapp.Presentation.Extension;

import android.content.Context;

import com.example.android.aptekaapp.Data.Exception.NetworkConnectionException;
import com.example.android.aptekaapp.Data.Exception.UserNotFoundException;
import com.example.android.aptekaapp.R;


/**
 * Фабрика для создания сообщений об ошибках при получении стандартных исключений
 */
public class ErrorMessageFactory {

    private ErrorMessageFactory() {
        //empty
    }

    /**
     * Creates a String representing an error message.
     * Создание строкового представления сообщения об ошибке
     * @param context контекст необходим для получения строкового ресурса
     *
     * @param exception расширение используется как условие для получения корректного сообщения
     *                об ошибке
     * @return {@link String} сообщение об ошибке в виде String
     */
    public static String create(Context context, Exception exception) {
        String message = context.getString(R.string.exception_message_generic);

        if (exception instanceof NetworkConnectionException) {
            message = context.getString(R.string.exception_message_no_connection);
        } else if (exception instanceof UserNotFoundException) {
            message = context.getString(R.string.exception_message_user_not_found);
        }

        return message;
    }
}