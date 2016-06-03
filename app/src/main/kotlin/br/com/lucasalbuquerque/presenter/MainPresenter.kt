package br.com.lucasalbuquerque.presenter

import br.com.lucasalbuquerque.ui.view.MainView

interface  MainPresenter {
    fun attachView(mainView: MainView)
    fun onRetrieveCharactersButtonClick()
}