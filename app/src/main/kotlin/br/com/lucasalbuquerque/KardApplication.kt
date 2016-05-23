package br.com.lucasalbuquerque

import android.app.Application
import br.com.lucasalbuquerque.di.component.ApplicationComponent
import br.com.lucasalbuquerque.di.component.DaggerApplicationComponent
import br.com.lucasalbuquerque.di.module.ApplicationModule
import br.com.lucasalbuquerque.di.module.RemoteRetrofitModule

class KardApplication : Application() {

    companion object {
        //platformStatic allow access it from java code
        @JvmStatic lateinit var graph: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        graph = DaggerApplicationComponent.builder()
                    .applicationModule(ApplicationModule(this))
                    .remoteRetrofitModule(RemoteRetrofitModule(this))
                    .build()
        graph.inject(this)
    }
}