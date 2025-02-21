package pl.wp.dogs.presentation.breeds_list

import pl.wp.dogs.presentation.breeds_list.model.BreedUiModel

data class BreedsListUiState(
    val breeds: List<BreedUiModel> = emptyList(),
    val error: Throwable? = null
)

sealed class BreedsListUiEvent {
    data class BreedsPressed(val breedName: String) : BreedsListUiEvent()
}

sealed class BreedsListUiAction {
    data class OpenBreedDetails(val breedName: String) : BreedsListUiAction()
    data class ShowError(val errorMessage: String?) : BreedsListUiAction()
}
