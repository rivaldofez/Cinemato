package com.rivaldofez.core.datasource

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.rivaldofez.core.datasource.local.LocalDataSource
import com.rivaldofez.core.datasource.remote.MoviesType
import com.rivaldofez.core.datasource.remote.RemoteDataSource
import com.rivaldofez.core.datasource.remote.TvShowsType
import com.rivaldofez.core.datasource.remote.network.ApiResponse
import com.rivaldofez.core.datasource.remote.response.*
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
    private val appExecutors: AppExecutors,
    private val appContext: Context
): ICinemaRepository {
    fun checkConnectivity(): Boolean{
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }


    override fun getMovies(type: MoviesType, page: String): Flow<Resource<List<Movie>>> = object : NetworkBoundResource<List<Movie>, List<MovieListItem>>(){
        override fun loadFromDB(): Flow<List<Movie>> {
            return localDataSource.getMovieList(type = type).map { movies ->
                MovieDataMapper.mapMovieListLocalToDomain(movies)
            }
        }

        override fun isNetworkActive(): Boolean = checkConnectivity()

        override fun shouldFetch(data: List<Movie>?): Boolean = true

        override suspend fun createCall(): Flow<ApiResponse<List<MovieListItem>>> {
            return remoteDataSource.getMovies(type = type, page = page)
        }

        override suspend fun saveCallResult(data: List<MovieListItem>) {
            val movieList = MovieDataMapper.mapMovieListResponseToLocal(data)
            localDataSource.insertMovieList(type = type, movieList)
        }
    }.asFlow()

    override fun getTvShows(type: TvShowsType, page: String): Flow<Resource<List<TvShow>>> = object : NetworkBoundResource<List<TvShow>, List<TvShowListItem>>(){
        override fun loadFromDB(): Flow<List<TvShow>> {
            return localDataSource.getPopularTvShow().map { tvShows ->
                TvShowDataMapper.mapTvShowListLocalToDomain(tvShows)
            }
        }

        override fun isNetworkActive(): Boolean = checkConnectivity()

        override fun shouldFetch(data: List<TvShow>?): Boolean = true

        override suspend fun createCall(): Flow<ApiResponse<List<TvShowListItem>>> {
            return remoteDataSource.getTvShows(type = type, page = page)
        }

        override suspend fun saveCallResult(data: List<TvShowListItem>) {
            val tvShowList = TvShowDataMapper.mapTvShowListResponseToLocal(data)
            localDataSource.insertTvShowList(tvShowList)
            localDataSource.insertIdPopularTvShow(TvShowDataMapper.mapTvShowListResponseToPopularId(data))
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

        override fun isNetworkActive(): Boolean = checkConnectivity()
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

        override fun isNetworkActive(): Boolean = checkConnectivity()
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

    override suspend fun getSearchMovieResult(query: String): List<Movie> {
        return MovieDataMapper.mapMovieListLocalToDomain(localDataSource.getSearchMovieResult(query))
    }

    override suspend fun getSearchTvShowResult(query: String): List<TvShow> {
        return TvShowDataMapper.mapTvShowListLocalToDomain(localDataSource.getSearchTvShowResult(query))
    }

    override suspend fun getSearchNameFavoriteTvShowResult(query: String): List<TvShowDetail> {
        return TvShowDataMapper.mapDetailTvShowListLocalToDomain(localDataSource.getSearchNameFavoriteTvShowResult(query))
    }

    override suspend fun getSearchNameFavoriteMovieResult(query: String): List<MovieDetail> {
        return MovieDataMapper.mapDetailMovieListLocalToDomain(localDataSource.getSearchNameFavoriteMovieResult(query))
    }
}