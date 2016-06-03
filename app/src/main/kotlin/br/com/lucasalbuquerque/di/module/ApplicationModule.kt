package br.com.lucasalbuquerque.di.module

import android.app.Application
import android.content.Context
import br.com.lucasalbuquerque.presenter.CharactersPresenter
import br.com.lucasalbuquerque.presenter.CharactersPresenterImpl
import br.com.lucasalbuquerque.presenter.MainPresenter
import br.com.lucasalbuquerque.presenter.MainPresenterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * A module for Android-specific dependencies which require a [android.content.Context] or [ ] to create.
 */
@Module
class ApplicationModule(private val application: Application) {

    /**
     * Allow the application context to be injected but require that it be annotated with [ ][ForApplication] to explicitly differentiate it from an activity context.
     */
    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideMainPresenter(mainPresenter: MainPresenterImpl): MainPresenter {
        return mainPresenter
    }

    @Provides
    @Singleton
    fun provideCharactersPresenter(charactersPresenter: CharactersPresenterImpl): CharactersPresenter {
        return charactersPresenter
    }
}
