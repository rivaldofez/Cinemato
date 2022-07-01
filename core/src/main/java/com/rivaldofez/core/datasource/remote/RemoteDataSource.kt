package com.rivaldofez.core.datasource.remote

import android.graphics.Movie
import com.rivaldofez.core.datasource.remote.network.ApiResponse
import com.rivaldofez.core.datasource.remote.network.ApiService
import com.rivaldofez.core.datasource.remote.response.MovieDetailResponse
import com.rivaldofez.core.datasource.remote.response.MovieListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getPopularMovies(): Flow<ApiResponse<List<MovieListItem>>> =
        flow {
            try {
                val response = apiService.getPopularMovies("d63d4fcb8d25c828fe89669f635ff545",page = "0")
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
            }
        }.flowOn(Dispatchers.IO)

}