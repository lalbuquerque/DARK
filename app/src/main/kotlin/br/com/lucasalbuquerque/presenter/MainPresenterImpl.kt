package br.com.lucasalbuquerque.presenter

import android.util.Log
import br.com.lucasalbuquerque.domain.MarvelCharacter
import br.com.lucasalbuquerque.domain.RemoteCharactersRepository
import br.com.lucasalbuquerque.repository.remote.di.annotations.Retrofit
import br.com.lucasalbuquerque.ui.view.MainView
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainPresenterImpl @Inject constructor(@Retrofit val charactersRepository: RemoteCharactersRepository) : MainPresenter {
    val TAG = MainPresenterImpl::class.java.simpleName

    lateinit var mainView: MainView
    val IMAGE_NOT_AVAILABLE = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available"

    override fun attachView(mainView: MainView) {
        this.mainView = mainView
    }

    override fun retrieveCharacters() {
        mainView.showLoadingLayout()
        charactersRepository.retrieveAllCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { chars -> Observable.from(chars) }
                .filter { char -> char.thumbnail!!.path != IMAGE_NOT_AVAILABLE }
                .toList()
                .subscribe({ list ->  hideLoadingAndShowCharactersList(list) },
                        { t -> Log.e(TAG, t.message)
                            mainView.showErrorLayout()
                        },
                        { Log.v("log", "completed") })
    }

    fun hideLoadingAndShowCharactersList(characters: List<MarvelCharacter>) {
        mainView.populateCharactersList(characters)
        mainView.hideLoadingLayout()
    }
}