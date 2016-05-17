package org.loop.example.di.component

import dagger.Component
import org.loop.example.MainActivity
import org.loop.example.MyApplication
import org.loop.example.di.module.ApplicationModule
import javax.inject.Singleton

/**
 * Created by loop on 14/12/14.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(application: MyApplication)

    fun inject(mainActivity: MainActivity)
}
