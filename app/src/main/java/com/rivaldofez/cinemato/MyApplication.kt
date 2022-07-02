package com.rivaldofez.cinemato

import android.app.Application
import com.rivaldofez.cinemato.di.useCaseModule
import com.rivaldofez.cinemato.di.viewModelModule
import com.rivaldofez.core.di.databaseModule
import com.rivaldofez.core.di.networkModule
import com.rivaldofez.core.di.repositoryModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@ExperimentalCoroutinesApi
@FlowPreview
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}