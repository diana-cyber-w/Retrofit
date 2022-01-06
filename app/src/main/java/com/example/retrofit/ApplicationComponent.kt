package com.example.retrofit

import android.content.Context
import com.example.retrofit.data.di.DataModule
import com.example.retrofit.domain.di.DomainModule
import com.example.retrofit.network.di.NetworkModule
import com.example.retrofit.presentation.FavouriteNewsFragment
import com.example.retrofit.presentation.MainActivity
import com.example.retrofit.presentation.NewsFragment
import com.example.retrofit.presentation.di.ViewModelModule
import com.example.retrofit.presentation.recycler.NewsAdapter
import com.example.retrofit.utils.di.UtilsModule
import dagger.BindsInstance
import dagger.Component


@Component(
    modules = [
        UtilsModule::class,
        DomainModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        DataModule::class
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindContext(context: Context): Builder

        fun build(): ApplicationComponent
    }

    fun inject(newsFragment: NewsFragment)
    fun inject(favouriteNewsFragment: FavouriteNewsFragment)
    fun inject(newsAdapter: NewsAdapter)
}