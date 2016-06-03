package br.com.lucasalbuquerque.presenter

import android.util.Log
import br.com.lucasalbuquerque.domain.MarvelCharacter
import br.com.lucasalbuquerque.domain.RemoteCharactersRepository
import br.com.lucasalbuquerque.repository.remote.di.annotations.Retrofit
import br.com.lucasalbuquerque.ui.view.CharactersView
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class CharactersPresenterImpl @Inject constructor(@Retrofit val charactersRepository: RemoteCharactersRepository) : CharactersPresenter {
    val TAG = CharactersPresenterImpl::class.java.simpleName
    val IMAGE_NOT_AVAILABLE = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available"

    lateinit var charactersView: CharactersView

    override fun attachView(charactersView: CharactersView) {
        this.charactersView = charactersView
    }

    override fun retrieveCharacters() {
        charactersRepository.retrieveAllCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { charactersView.showLoadingLayout() }
                .flatMap { chars -> Observable.from(chars) }
                .filter { char -> char.thumbnail!!.path != IMAGE_NOT_AVAILABLE }
                .toList()
                .subscribe({ list ->  hideLoadingAndShowCharactersList(list) },
                        { t -> handleError(t)})
    }

    fun hideLoadingAndShowCharactersList(characters: List<MarvelCharacter>) {
        charactersView.populateCharactersList(characters)
        charactersView.hideLoadingLayout()
    }

    fun handleError(t: Throwable) {
        Log.e(TAG, t.message)
        charactersView.showErrorLayout()
    }
}