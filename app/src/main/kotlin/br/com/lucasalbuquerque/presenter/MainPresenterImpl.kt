package br.com.lucasalbuquerque.presenter

import br.com.lucasalbuquerque.ui.view.MainView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainPresenterImpl @Inject constructor() : MainPresenter {

    lateinit var mainView: MainView

    override fun attachView(mainView: MainView) {
        this.mainView = mainView
    }

    override fun onCharactersButtonClick() {
        mainView.goToCharactersActivity()
    }


}