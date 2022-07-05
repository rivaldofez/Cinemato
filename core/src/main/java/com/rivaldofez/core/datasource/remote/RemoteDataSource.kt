package com.rivaldofez.core.datasource.remote

import android.util.Log
import com.rivaldofez.core.BuildConfig.API_KEY
import com.rivaldofez.core.datasource.remote.network.ApiResponse
import com.rivaldofez.core.datasource.remote.network.ApiService
import com.rivaldofez.core.datasource.remote.response.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getMovies(type: String, page: String): Flow<ApiResponse<List<MovieListItem>>> =
        flow {
            try {
                val response = apiService.getMovies(key = API_KEY, type = type, page = page )
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

    suspend fun getPopularMovies(page: String): Flow<ApiResponse<List<MovieListItem>>> =
        flow {
            try {
                val response = apiService.getPopularMovies("d63d4fcb8d25c828fe89669f635ff545",page = page)
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

    suspend fun getTopRatedMovies(page: String): Flow<ApiResponse<List<MovieListItem>>> =
        flow {
            try {
                val response = apiService.getTopRatedMovies("d63d4fcb8d25c828fe89669f635ff545",page = page)
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

    suspend fun getUpComingMovies(page: String): Flow<ApiResponse<List<MovieListItem>>> =
        flow {
            try {
                val response = apiService.getUpComingMovies("d63d4fcb8d25c828fe89669f635ff545",page = page)
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

    suspend fun getNowPlayingMovies(page: String): Flow<ApiResponse<List<MovieListItem>>> =
        flow {
            try {
                val response = apiService.getNowPlayingMovies("d63d4fcb8d25c828fe89669f635ff545",page = page)
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

    suspend fun getPopularTvShow(page: String): Flow<ApiResponse<List<TvShowListItem>>> =
        flow {
            try {
                val response = apiService.getPopularTvShow("d63d4fcb8d25c828fe89669f635ff545",page = page)
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

    suspend fun getTopRatedTvShow(page: String): Flow<ApiResponse<List<TvShowListItem>>> =
        flow {
            try {
                val response = apiService.getTopRatedTvShow("d63d4fcb8d25c828fe89669f635ff545",page = page)
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

    suspend fun getOnTheAirTvShow(page: String): Flow<ApiResponse<List<TvShowListItem>>> =
        flow {
            try {
                val response = apiService.getOnAirTvShow("d63d4fcb8d25c828fe89669f635ff545",page = page)
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

    suspend fun getAiringTodayTvShow(page: String): Flow<ApiResponse<List<TvShowListItem>>> =
        flow {
            try {
                val response = apiService.getAiringTodayTvShow("d63d4fcb8d25c828fe89669f635ff545",page = page)
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