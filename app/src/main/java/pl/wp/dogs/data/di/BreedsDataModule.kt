package pl.wp.dogs.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.wp.dogs.common.networking.ApiProvider
import pl.wp.dogs.common.networking.provide
import pl.wp.dogs.data.BreedsRepository
import pl.wp.dogs.data.BreedsRepositoryImpl
import pl.wp.dogs.data.network.BreedsApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface BreedsDataModule {

    @Binds
    @Singleton
    fun bindBreedsRepository(impl: BreedsRepositoryImpl): BreedsRepository

    companion object {
        private const val baseUrl = "https://dog.ceo/"

        @Provides
        fun provideBreedsApi(apiProvider: ApiProvider): BreedsApi = apiProvider.provide(baseUrl)
    }
}
