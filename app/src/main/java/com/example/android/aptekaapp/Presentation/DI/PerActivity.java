package com.example.android.aptekaapp.Presentation.DI;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * A scoping annotation to permit objects whose lifetime should
 * conform to the life of the activity to be memorized in the
 * correct component.
 *
 * Scope аннотация.Служит для маркировки обьектов,время жизни которых должно соответствовать жизни активити.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {}