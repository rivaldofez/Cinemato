package com.rivaldofez.core.datasource

import android.util.Log
import com.rivaldofez.core.datasource.local.LocalDataSource
import com.rivaldofez.core.datasource.local.entity.MovieItemLocalEntity
import com.rivaldofez.core.datasource.remote.RemoteDataSource
import com.rivaldofez.core.datasource.remote.network.ApiResponse
import com.rivaldofez.core.datasource.remote.response.MovieDetailResponse
import com.rivaldofez.core.datasource.remote.response.MovieListItem
import com.rivaldofez.core.domain.model.Movie
import com.rivaldofez.core.domain.model.MovieDetail
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
            Log.d("Teston", "load from db")
            return localDataSource.getPopularMovies().map { movies ->
                DataMapper.mapMovieListLocalToDomain(movies)
            }
        }

        override fun shouldFetch(data: List<Movie>?): Boolean = false

        override suspend fun createCall(): Flow<ApiResponse<List<MovieListItem>>> {
            Log.d("Teston", "create call")
            return remoteDataSource.getPopularMovies(page)
        }

        override suspend fun saveCallResult(data: List<MovieListItem>) {

            Log.d("Teston", "save call")
            val movieList = DataMapper.mapMovieListResponseToLocal(data)


            localDataSource.insertPopularMovies(movieList)
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
}