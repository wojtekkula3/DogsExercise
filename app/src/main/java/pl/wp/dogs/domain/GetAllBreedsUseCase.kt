package pl.wp.dogs.domain

import pl.wp.dogs.common.resultOf
import pl.wp.dogs.data.BreedsRepository
import pl.wp.dogs.data.network.model.AllBreedsResponse
import javax.inject.Inject

class GetAllBreedsUseCase @Inject constructor(
    private val repository: BreedsRepository
) {
    suspend operator fun invoke(): Result<AllBreedsResponse> {
        return resultOf {
            repository.getAllBreeds()
        }
    }
}
