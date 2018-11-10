package com.example.android.aptekaapp.Domain.Executor;

import java.util.concurrent.Executor;

/**
 * Имплементация класса Executor может быть основана на использовании различных фреймворков или технологий
 * асинхронного выполнения, но каждая имплементация должна выполнять {@link com.example.cleanarchitecturebycejas.Domain.Interactor.UseCase}
 * НЕ в UI потоке
 */
public interface ThreadExecutor extends Executor {}