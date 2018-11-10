package com.example.android.aptekaapp.Domain.Interactor;

import io.reactivex.observers.DisposableObserver;

/**
 * Default {@link DisposableObserver} base class to be used whenever you want default error handling.
 {@link DisposableObserver} это по умолчанию базовый класс,который используется
 везде,включая с целью обработки ошибок.Класс наследует DisposableObserver.
 DisposableObserver это наблюдатель, который позволяет асинхронную отписку от
 источника после одноразового использования.Как и все другие потребители,
 DisposableObserver может быть подписан только один раз.
 */
public class DefaultObserver<T> extends DisposableObserver<T> {
    @Override public void onNext(T t) {
        // no-op by default.
    }

    @Override public void onComplete() {
        // no-op by default.
    }

    @Override public void onError(Throwable exception) {
        // no-op by default.
    }
}