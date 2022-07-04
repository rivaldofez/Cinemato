package com.rivaldofez.core.datasource

import android.util.Log
import com.rivaldofez.core.datasource.local.LocalDataSource
import com.rivaldofez.core.datasource.remote.RemoteDataSource
import com.rivaldofez.core.datasource.remote.network.ApiResponse
import com.rivaldofez.core.datasource.remote.response.MovieDetailResponse
import com.rivaldofez.core.datasource.remote.response.MovieListItem
import com.rivaldofez.core.datasource.remote.response.TvShowDetailResponse
import com.rivaldofez.core.datasource.remote.response.TvShowListItem
import com.rivaldofez.core.domain.model.Movie
import com.rivaldofez.core.domain.model.MovieDetail
import com.rivaldofez.core.domain.model.TvShow
import com.rivaldofez.core.domain.model.TvShowDetail
import com.rivaldofez.core.domain.repository.ICinemaRepository
import com.rivaldofez.core.utils.AppExecutors
import com.rivaldofez.core.utils.MovieDataMapper
import com.rivaldofez.core.utils.TvShowDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CinemaRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): ICinemaRepository {
    override fun getPopularMovies(page: String): Flow<Resource<List<Movie>>> = object : NetworkBoundResource<List<Movie>, List<MovieListItem>>(){
        override fun loadFromDB(): Flow<List<Movie>> {
            return localDataSource.getPopularMovies().map { movies ->
                MovieDataMapper.mapMovieListLocalToDomain(movies)
            }
        }

        override fun shouldFetch(data: List<Movie>?): Boolean = true

        override suspend fun createCall(): Flow<ApiResponse<List<MovieListItem>>> {
            return remoteDataSource.getPopularMovies(page)
        }

        override suspend fun saveCallResult(data: List<MovieListItem>) {
            val movieList = MovieDataMapper.mapMovieListResponseToLocal(data)
            val idMovieList = MovieDataMapper.mapMovieListResponseToPopularId(data)
            localDataSource.insertMovieList(movieList)
            localDataSource.insertIdPopularMovies(idMovieList)
        }
    }.asFlow()

    override fun getTopRatedMovies(page: String): Flow<Resource<List<Movie>>> = object : NetworkBoundResource<List<Movie>, List<MovieListItem>>(){
        override fun loadFromDB(): Flow<List<Movie>> {
            return localDataSource.getTopRatedMovies().map { movies ->
                MovieDataMapper.mapMovieListLocalToDomain(movies)
            }
        }

        override fun shouldFetch(data: List<Movie>?): Boolean = true

        override suspend fun createCall(): Flow<ApiResponse<List<MovieListItem>>> {
            return remoteDataSource.getTopRatedMovies(page)
        }

        override suspend fun saveCallResult(data: List<MovieListItem>) {
            val movieList = MovieDataMapper.mapMovieListResponseToLocal(data)
            val idMovieList = MovieDataMapper.mapMovieListResponseToTopRatedId(data)
            localDataSource.insertMovieList(movieList)
            localDataSource.insertIdTopRatedMovies(idMovieList)
        }
    }.asFlow()

    override fun getUpComingMovies(page: String): Flow<Resource<List<Movie>>> = object : NetworkBoundResource<List<Movie>, List<MovieListItem>>(){
        override fun loadFromDB(): Flow<List<Movie>> {
            return localDataSource.getUpComingMovies().map { movies ->
                MovieDataMapper.mapMovieListLocalToDomain(movies)
            }
        }

        override fun shouldFetch(data: List<Movie>?): Boolean = true

        override suspend fun createCall(): Flow<ApiResponse<List<MovieListItem>>> {
            return remoteDataSource.getUpComingMovies(page)
        }

        override suspend fun saveCallResult(data: List<MovieListItem>) {
            val movieList = MovieDataMapper.mapMovieListResponseToLocal(data)
            val idMovieList = MovieDataMapper.mapMovieListResponseToUpComingId(data)
            localDataSource.insertMovieList(movieList)
            localDataSource.insertIdUpComingMovies(idMovieList)
        }
    }.asFlow()

    override fun getNowPlayingMovies(page: String): Flow<Resource<List<Movie>>> = object : NetworkBoundResource<List<Movie>, List<MovieListItem>>(){
        override fun loadFromDB(): Flow<List<Movie>> {
            return localDataSource.getNowPlayingMovies().map { movies ->
                MovieDataMapper.mapMovieListLocalToDomain(movies)
            }
        }

        override fun shouldFetch(data: List<Movie>?): Boolean = true

        override suspend fun createCall(): Flow<ApiResponse<List<MovieListItem>>> {
            return remoteDataSource.getNowPlayingMovies(page)
        }

        override suspend fun saveCallResult(data: List<MovieListItem>) {
            val movieList = MovieDataMapper.mapMovieListResponseToLocal(data)
            val idMovieList = MovieDataMapper.mapMovieListResponseToNowPlayingId(data)
            localDataSource.insertMovieList(movieList)
            localDataSource.insertIdNowPlayingMovies(idMovieList)
        }
    }.asFlow()

    override fun getPopularTvShow(page: String): Flow<Resource<List<TvShow>>> = object : NetworkBoundResource<List<TvShow>, List<TvShowListItem>>(){
        override fun loadFromDB(): Flow<List<TvShow>> {
            return localDataSource.getPopularTvShow().map {
                TvShowDataMapper.mapTvShowListLocalToDomain(it)
            }
        }

        override fun shouldFetch(data: List<TvShow>?): Boolean = true

        override suspend fun createCall(): Flow<ApiResponse<List<TvShowListItem>>> {
            return remoteDataSource.getPopularTvShow(page)
        }

        override suspend fun saveCallResult(data: List<TvShowListItem>) {
            val tvShowList = TvShowDataMapper.mapTvShowListResponseToLocal(data)
            val idTvShowList = TvShowDataMapper.mapTvShowListResponseToPopularId(data)
            localDataSource.insertTvShowList(tvShowList)
            localDataSource.insertIdPopularTvShow(idTvShowList)
        }
    }.asFlow()

    override fun getTopRatedTvShow(page: String): Flow<Resource<List<TvShow>>> = object : NetworkBoundResource<List<TvShow>, List<TvShowListItem>>(){
        override fun loadFromDB(): Flow<List<TvShow>> {
            return localDataSource.getTopRatedTvShow().map {
                TvShowDataMapper.mapTvShowListLocalToDomain(it)
            }
        }

        override fun shouldFetch(data: List<TvShow>?): Boolean = true

        override suspend fun createCall(): Flow<ApiResponse<List<TvShowListItem>>> {
            return remoteDataSource.getTopRatedTvShow(page)
        }

        override suspend fun saveCallResult(data: List<TvShowListItem>) {
            val tvShowList = TvShowDataMapper.mapTvShowListResponseToLocal(data)
            val idTvShowList = TvShowDataMapper.mapTvShowListResponseToTopRatedId(data)
            localDataSource.insertTvShowList(tvShowList)
            localDataSource.insertIdTopRatedTvShow(idTvShowList)
        }
    }.asFlow()

    override fun getOnTheAirTvShow(page: String): Flow<Resource<List<TvShow>>> = object : NetworkBoundResource<List<TvShow>, List<TvShowListItem>>(){
        override fun loadFromDB(): Flow<List<TvShow>> {
            return localDataSource.getOnTheAirTvShow().map {
                TvShowDataMapper.mapTvShowListLocalToDomain(it)
            }
        }

        override fun shouldFetch(data: List<TvShow>?): Boolean = true

        override suspend fun createCall(): Flow<ApiResponse<List<TvShowListItem>>> {
            return remoteDataSource.getOnTheAirTvShow(page)
        }

        override suspend fun saveCallResult(data: List<TvShowListItem>) {
            val tvShowList = TvShowDataMapper.mapTvShowListResponseToLocal(data)
            val idTvShowList = TvShowDataMapper.mapTvShowListResponseToOnTheAirId(data)
            localDataSource.insertTvShowList(tvShowList)
            localDataSource.insertIdOnTheAIrTvShow(idTvShowList)
        }
    }.asFlow()

    override fun getAiringTvShow(page: String): Flow<Resource<List<TvShow>>> = object : NetworkBoundResource<List<TvShow>, List<TvShowListItem>>(){
        override fun loadFromDB(): Flow<List<TvShow>> {
            return localDataSource.getAiringTodayTvShow().map {
                TvShowDataMapper.mapTvShowListLocalToDomain(it)
            }
        }

        override fun shouldFetch(data: List<TvShow>?): Boolean = true

        override suspend fun createCall(): Flow<ApiResponse<List<TvShowListItem>>> {
            return remoteDataSource.getAiringTodayTvShow(page)
        }

        override suspend fun saveCallResult(data: List<TvShowListItem>) {
            val tvShowList = TvShowDataMapper.mapTvShowListResponseToLocal(data)
            val idTvShowList = TvShowDataMapper.mapTvShowListResponseToAiringTodayId(data)
            localDataSource.insertTvShowList(tvShowList)
            localDataSource.insertIdAiringTodayTvShow(idTvShowList)
        }
    }.asFlow()

    override fun getDetailTvShow(id: String): Flow<Resource<TvShowDetail?>> = object: NetworkBoundResource<TvShowDetail?, TvShowDetailResponse>(){
        override fun loadFromDB(): Flow<TvShowDetail?> {
            val loaded = localDataSource.getDetailTvShow(id)
            return loaded.map {
                if(it != null){
                    TvShowDataMapper.mapDetailTvShowLocalToDomain(it)
                }else{
                    null
                }
            }
        }

        override fun shouldFetch(data: TvShowDetail?): Boolean = true

        override suspend fun createCall(): Flow<ApiResponse<TvShowDetailResponse>> = remoteDataSource.getDetailTvShow(id)

        override suspend fun saveCallResult(data: TvShowDetailResponse) {
            val dataMapped = TvShowDataMapper.mapDetailTvShowResponseToLocal(data)
            localDataSource.insertDetailTvShow(dataMapped)
        }
    }.asFlow()

    override fun getDetailMovie(id: String): Flow<Resource<MovieDetail?>> = object: NetworkBoundResource<MovieDetail?, MovieDetailResponse>(){
        override fun loadFromDB(): Flow<MovieDetail?> {
            val loaded = localDataSource.getDetailMovie(id)
            return loaded.map {
                if(it != null){
                    MovieDataMapper.mapDetailMovieLocalToDomain(it)
                }else{
                    null
                }
            }
        }

        override fun shouldFetch(data: MovieDetail?): Boolean = true

        override suspend fun createCall(): Flow<ApiResponse<MovieDetailResponse>> = remoteDataSource.getDetailMovie(id)

        override suspend fun saveCallResult(data: MovieDetailResponse) {
            val dataMapped = MovieDataMapper.mapDetailMovieResponseToLocal(data)
            Log.d("Teston", "save call " + dataMapped.toString())
            localDataSource.insertDetailMovie(dataMapped)
        }
    }.asFlow()

    override fun getFavoriteMovies(): Flow<List<MovieDetail>> {
        return localDataSource.getFavoriteMovies().map {
            MovieDataMapper.mapDetailMovieListLocalToDomain(it)
        }
    }

    override fun getFavoriteTvShows(): Flow<List<TvShowDetail>> {
        return localDataSource.getFavoriteTvShow().map {
            TvShowDataMapper.mapDetailTvShowListLocalToDomain(it)
        }
    }

    override fun setFavoriteMovie(detailMovie: MovieDetail, state: Boolean) {
        val movieEntity = MovieDataMapper.mapDomainDetailMovieToLocal(detailMovie)
        appExecutors.diskIO().execute{localDataSource.setFavoriteMovie(movieEntity,state)}
    }

    override fun setFavoriteTvShow(detailTvShow: TvShowDetail, state: Boolean) {
        val tvShowEntity = TvShowDataMapper.mapDomainDetailTvShowToLocal(detailTvShow)
        appExecutors.diskIO().execute{localDataSource.setFavoriteTvShow(tvShowEntity, state)}
    }
}