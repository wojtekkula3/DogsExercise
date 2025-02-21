package pl.wp.dogs.presentation.breeds_list.model

import javax.inject.Inject

class BreedMapper @Inject constructor() {

    fun mapToBreedUiModel(breeds: Map<String, List<String>>): List<BreedUiModel> {
        return breeds.keys.map { BreedUiModel(it) }
    }
}