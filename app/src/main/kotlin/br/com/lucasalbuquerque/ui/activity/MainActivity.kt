package br.com.lucasalbuquerque.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.lucasalbuquerque.KardApplication
import br.com.lucasalbuquerque.ui.layout.MainActivityUI
import org.jetbrains.anko.setContentView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.simpleName

    @Inject lateinit var ui: MainActivityUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KardApplication.graph.inject(this)

        ui.setContentView(this)
    }
}
