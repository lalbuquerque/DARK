package org.loop.example.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import org.loop.example.ForApplication
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
    @ForApplication
    fun provideApplicationContext(): Context {
        return application
    }
}
