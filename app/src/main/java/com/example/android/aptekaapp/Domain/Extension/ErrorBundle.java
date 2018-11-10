package com.example.android.aptekaapp.Domain.Extension;

/**
 * Интерфейс представляет обертку вокруг стандартных исключений {@link java.lang.Exception}
 * для управления обработкой ошибок
 */
public interface ErrorBundle {
    Exception getException();

    String getErrorMessage();
}