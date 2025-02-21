package pl.wp.dogs.data

import pl.wp.dogs.data.network.model.AllBreedsResponse

interface BreedsRepository {

    suspend fun getAllBreeds(): AllBreedsResponse
}
