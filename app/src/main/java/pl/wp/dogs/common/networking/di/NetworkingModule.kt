package pl.wp.dogs.common.networking.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.wp.dogs.common.networking.ApiProvider
import pl.wp.dogs.common.networking.RetrofitApiProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface NetworkingModule {

    @Binds
    @Singleton
    fun bindApiProvider(impl: RetrofitApiProvider): ApiProvider
}
