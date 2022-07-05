package com.rivaldofez.core.di

import androidx.room.Room
import com.rivaldofez.core.datasource.CinemaRepository
import com.rivaldofez.core.datasource.local.LocalDataSource
import com.rivaldofez.core.datasource.local.room.CinemaDatabase
import com.rivaldofez.core.datasource.remote.RemoteDataSource
import com.rivaldofez.core.datasource.remote.network.ApiService
import com.rivaldofez.core.domain.repository.ICinemaRepository
import com.rivaldofez.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
//        val hostname = "api.themoviedb.org"
//        val certificatePinner = CertificatePinner.Builder()
//            .add(hostname, "sha256/hS5jJ4P+iQUErBkvoWBQOd1T7VOAYlOVegvv1iMzpxA=")
//            .add(hostname, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
//            .build()
//
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//            .connectTimeout(120, TimeUnit.SECONDS)
//            .readTimeout(120, TimeUnit.SECONDS)
//            .certificatePinner(certificatePinner)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val databaseModule = module {
    factory { get<CinemaDatabase>().cinemaDao()}
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("rivaldofez".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            CinemaDatabase::class.java, "Movie.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ICinemaRepository> {
        CinemaRepository(
            get(),
            get(),
            get(),
            androidContext()
        )
    }
}