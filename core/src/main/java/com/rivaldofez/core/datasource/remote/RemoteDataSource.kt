package com.rivaldofez.core.datasource.remote

import android.util.Log
import com.rivaldofez.core.BuildConfig.API_KEY
import com.rivaldofez.core.datasource.remote.network.ApiResponse
import com.rivaldofez.core.datasource.remote.network.ApiService
import com.rivaldofez.core.datasource.remote.response.*
import com.rivaldofez.core.utils.MovieDataMapper
import com.rivaldofez.core.utils.TvShowDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getMovies(type: MoviesType, page: String): Flow<ApiResponse<List<MovieListItem>>> =
        flow {
            try {
                val response = apiService.getMovies(key = API_KEY, type = MovieDataMapper.getMoviesType(type),page = page )
                val dataArray = response.results
                if(dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getTvShows(type: TvShowsType, page: String): Flow<ApiResponse<List<TvShowListItem>>> =
        flow {
            try {
                val response = apiService.getTvShows(key = API_KEY, type = TvShowDataMapper.getTvShowsType(type),page = page )
                val dataArray = response.results
                if(dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getDetailMovie(id: String): Flow<ApiResponse<MovieDetailResponse>> =
        flow {
            try {
                val response: MovieDetailResponse? = apiService.getDetailMovie(id = id, key = "d63d4fcb8d25c828fe89669f635ff545")
                if(response != null){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.d("Teston", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getDetailTvShow(id: String): Flow<ApiResponse<TvShowDetailResponse>> =
        flow {
            try {
                val response: TvShowDetailResponse? = apiService.getDetailTvShow(id = id, key = "d63d4fcb8d25c828fe89669f635ff545")
                if(response != null){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

}