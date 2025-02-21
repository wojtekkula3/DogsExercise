package pl.wp.dogs.data

import pl.wp.dogs.data.network.BreedsRemoteDataSource
import pl.wp.dogs.data.network.model.AllBreedsResponse
import javax.inject.Inject

class BreedsRepositoryImpl @Inject constructor(
    private val dataSource: BreedsRemoteDataSource
) : BreedsRepository {

    override suspend fun getAllBreeds(): AllBreedsResponse = dataSource.getAllBreeds()
}
