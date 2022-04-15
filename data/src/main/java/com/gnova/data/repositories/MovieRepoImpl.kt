package com.gnova.data.repositories

import com.gnova.data.api.MovieApi
import com.gnova.data.mappers.MovieDTOMapper
  import com.gnova.domain.models.Movie
 import com.gnova.domain.repositories.MovieRepo
import io.reactivex.Single
import javax.inject.Inject

class MovieRepoImpl@Inject constructor(
    private val movieApi: MovieApi,
    private val movieMapper: MovieDTOMapper,
     ) : MovieRepo {

    override fun getTopRatedMovies(sortBy: String): Single<List<Movie>> {

        return movieApi.getMovies("c50f5aa4e7c95a2a553d29b81aad6dd0", "en-us", sortBy,
        "false", "false", 1)
            .map {
                movieMapper.mapToDomainList(it.results)
            }
    }


}

