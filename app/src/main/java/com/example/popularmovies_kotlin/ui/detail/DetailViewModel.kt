package com.example.popularmovies_kotlin.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
 import com.gnova.data.repositories.MovieRepoImpl
import com.gnova.domain.models.Movie
 import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
 import javax.inject.Inject

class DetailViewModel @Inject constructor(private val movieRepoImpl: MovieRepoImpl) : ViewModel() {

    fun onViewInit(movie: Movie) {
        _selectedMovie.value = movie
        _movieId.value = movie.id!!
    }

    // MutableLiveData that stores the selected movie
    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie>
        get() = _selectedMovie

    // View State
    private val _viewState = MutableLiveData<DetailViewState>()
    val viewState: LiveData<DetailViewState>
        get() = _viewState

    // ID to get the Trailers and the Reviews.
    private val _movieId = MutableLiveData<Int>()
    val movieId: LiveData<Int>
        get() = _movieId


    val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    protected fun add(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

}