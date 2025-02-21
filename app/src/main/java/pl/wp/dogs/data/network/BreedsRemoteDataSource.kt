package pl.wp.dogs.data.network

import javax.inject.Inject

class BreedsRemoteDataSource @Inject constructor(
    private val breedsApi: BreedsApi
) {
    suspend fun getAllBreeds() = breedsApi.getBreeds()
}
