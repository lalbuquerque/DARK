package org.loop.example

import android.app.Application
import android.location.LocationManager
import org.loop.example.di.component.ApplicationComponent
import org.loop.example.di.module.ApplicationModule
import org.loop.example.di.module.RemoteRetrofitModule
import javax.inject.Inject


/**
 * Created by loop on 09/12/14.
 */

class MyApplication : Application() {

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