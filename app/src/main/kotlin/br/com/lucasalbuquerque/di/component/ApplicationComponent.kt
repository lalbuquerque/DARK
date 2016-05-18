package br.com.lucasalbuquerque.di.component

import dagger.Component
import br.com.lucasalbuquerque.MainActivity
import br.com.lucasalbuquerque.MyApplication
import br.com.lucasalbuquerque.di.module.ApplicationModule
import br.com.lucasalbuquerque.di.module.RemoteRetrofitModule
import javax.inject.Singleton

/**
 * Created by loop on 14/12/14.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class, RemoteRetrofitModule::class))
interface ApplicationComponent {
    fun inject(application: MyApplication)

    fun inject(mainActivity: MainActivity)
}
