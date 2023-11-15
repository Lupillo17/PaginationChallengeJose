package com.example.paginationchallenge

import android.app.Application
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.paginationchallenge.core.di.homeInfrastructureModules
import com.example.paginationchallenge.data.remote.MarvelClientKoin
import com.example.paginationchallenge.core.di.mainScreenModules
import com.example.paginationchallenge.core.di.presenterModule
import com.example.paginationchallenge.ui.theme.PaginationChallengeTheme
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
//            modules(
//                MarvelClientKoin.modules +
//                        mainScreenModules +
//            )
            modules(homeInfrastructureModules)
            modules(
                presenterModule
            )
        }
    }
}

@Composable
fun MainApplication(content: @Composable () -> Unit) {
    PaginationChallengeTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}
