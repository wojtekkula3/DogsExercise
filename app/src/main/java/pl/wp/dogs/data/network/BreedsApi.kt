package pl.wp.dogs.data.network

import pl.wp.dogs.data.network.model.AllBreedsResponse
import retrofit2.http.GET

interface BreedsApi {

    @GET("api/breeds/list/all")
    suspend fun getBreeds(): AllBreedsResponse
}
