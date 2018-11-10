package com.example.android.aptekaapp.Presentation.Presenter;

/**
        * Интерфейс который реализуют все презентеры
        */
public interface Presenter {
    /**
     * Метод для контроля за жизненным циклом View.Он должен вызываться в методе onResume()
     * активити или фрагмента
     */
    void resume();

    /**
     * Метод для контроля за жизненным циклом View.Он должен вызываться в методе onPause()
     * активити или фрагмента
     */
    void pause();

    /**
     * Метод для контроля за жизненным циклом View.Он должен вызываться в методе onDestroy()
     * активити или фрагменты
     */
    void destroy();
}