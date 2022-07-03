package com.rivaldofez.core.datasource

import com.rivaldofez.core.datasource.local.LocalDataSource
import com.rivaldofez.core.datasource.remote.RemoteDataSource
import com.rivaldofez.core.datasource.remote.network.ApiResponse
import com.rivaldofez.core.datasource.remote.response.MovieDetailResponse
import com.rivaldofez.core.datasource.remote.response.MovieListItem
import com.rivaldofez.core.domain.model.Movie
import com.rivaldofez.core.domain.model.MovieDetail
import com.rivaldofez.core.domain.model.TvShow
import com.rivaldofez.core.domain.model.TvShowDetail
import com.rivaldofez.core.domain.repository.ICinemaRepository
import com.rivaldofez.core.utils.AppExecutors
import com.rivaldofez.core.utils.DataMapper
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
                DataMapper.mapMovieListLocalToDomain(movies)
            }
        }

        override fun shouldFetch(data: List<Movie>?): Boolean = true

        override suspend fun createCall(): Flow<ApiResponse<List<MovieListItem>>> {
            return remoteDataSource.getPopularMovies(page)
        }

        override suspend fun saveCallResult(data: List<MovieListItem>) {
            val movieList = DataMapper.mapMovieListResponseToLocal(data)
            val idMovieList = DataMapper.mapMovieListResponseToPopularId(data)
            localDataSource.insertMovieList(movieList)
            localDataSource.insertIdPopularMovies(idMovieList)
        }
    }.asFlow()

    override fun getTopRatedMovies(page: String): Flow<Resource<List<Movie>>> = object : NetworkBoundResource<List<Movie>, List<MovieListItem>>(){
        override fun loadFromDB(): Flow<List<Movie>> {
            return localDataSource.getTopRatedMovies().map { movies ->
                DataMapper.mapMovieListLocalToDomain(movies)
            }
        }

        override fun shouldFetch(data: List<Movie>?): Boolean = true

        override suspend fun createCall(): Flow<ApiResponse<List<MovieListItem>>> {
            return remoteDataSource.getTopRatedMovies(page)
        }

        override suspend fun saveCallResult(data: List<MovieListItem>) {
            val movieList = DataMapper.mapMovieListResponseToLocal(data)
            val idMovieList = DataMapper.mapMovieListResponseToTopRatedId(data)
            localDataSource.insertMovieList(movieList)
            localDataSource.insertIdTopRatedMovies(idMovieList)
        }
    }.asFlow()

    override fun getUpComingMovies(page: String): Flow<Resource<List<Movie>>> = object : NetworkBoundResource<List<Movie>, List<MovieListItem>>(){
        override fun loadFromDB(): Flow<List<Movie>> {
            return localDataSource.getUpComingMovies().map { movies ->
                DataMapper.mapMovieListLocalToDomain(movies)
            }
        }

        override fun shouldFetch(data: List<Movie>?): Boolean = true

        override suspend fun createCall(): Flow<ApiResponse<List<MovieListItem>>> {
            return remoteDataSource.getUpComingMovies(page)
        }

        override suspend fun saveCallResult(data: List<MovieListItem>) {
            val movieList = DataMapper.mapMovieListResponseToLocal(data)
            val idMovieList = DataMapper.mapMovieListResponseToUpComingId(data)
            localDataSource.insertMovieList(movieList)
            localDataSource.insertIdUpComingMovies(idMovieList)
        }
    }.asFlow()

    override fun getNowPlayingMovies(page: String): Flow<Resource<List<Movie>>> = object : NetworkBoundResource<List<Movie>, List<MovieListItem>>(){
        override fun loadFromDB(): Flow<List<Movie>> {
            return localDataSource.getNowPlayingMovies().map { movies ->
                DataMapper.mapMovieListLocalToDomain(movies)
            }
        }

        override fun shouldFetch(data: List<Movie>?): Boolean = true

        override suspend fun createCall(): Flow<ApiResponse<List<MovieListItem>>> {
            return remoteDataSource.getNowPlayingMovies(page)
        }

        override suspend fun saveCallResult(data: List<MovieListItem>) {
            val movieList = DataMapper.mapMovieListResponseToLocal(data)
            val idMovieList = DataMapper.mapMovieListResponseToNowPlayingId(data)
            localDataSource.insertMovieList(movieList)
            localDataSource.insertIdNowPlayingMovies(idMovieList)
        }
    }.asFlow()

    override fun getDetailMovie(id: String): Flow<Resource<MovieDetail?>> = object: NetworkBoundResource<MovieDetail?, MovieDetailResponse>(){
        override fun loadFromDB(): Flow<MovieDetail?> {
            val loaded = localDataSource.getDetailMovie(id)
            return loaded.map {
                if(it != null){
                    DataMapper.mapDetailMovieLocalToDomain(it)
                }else{
                    null
                }
            }
        }

        override fun shouldFetch(data: MovieDetail?): Boolean =
            data == null


        override suspend fun createCall(): Flow<ApiResponse<MovieDetailResponse>> =
            remoteDataSource.getDetailMovie(id)


        override suspend fun saveCallResult(data: MovieDetailResponse) {
            val detailMovie = DataMapper.mapDetailMovieResponseToLocal(data)
        }
    }.asFlow()


    override fun getPopularTvShow(page: String): Flow<Resource<List<TvShow>>> = object : NetworkBoundResource<List<Movie>, List<MovieListItem>>(){
        override fun loadFromDB(): Flow<List<Movie>> {
            TODO("Not yet implemented")
        }

        override fun shouldFetch(data: List<Movie>?): Boolean {
            TODO("Not yet implemented")
        }

        override suspend fun createCall(): Flow<ApiResponse<List<MovieListItem>>> {
            TODO("Not yet implemented")
        }

        override suspend fun saveCallResult(data: List<MovieListItem>) {
            TODO("Not yet implemented")
        }
    }

    override fun getTopRatedTvShow(page: String): Flow<Resource<List<TvShow>>> {
        TODO("Not yet implemented")
    }

    override fun getOnTheAirTvShow(page: String): Flow<Resource<List<TvShow>>> {
        TODO("Not yet implemented")
    }

    override fun getAiringTvShow(page: String): Flow<Resource<List<TvShow>>> {
        TODO("Not yet implemented")
    }

    override fun getDetailTvShow(id: String): Flow<Resource<TvShowDetail>> {
        TODO("Not yet implemented")
    }
}