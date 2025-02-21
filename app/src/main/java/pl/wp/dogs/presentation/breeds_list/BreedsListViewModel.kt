package pl.wp.dogs.presentation.breeds_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pl.wp.dogs.common.newBuilder
import pl.wp.dogs.domain.GetAllBreedsUseCase
import pl.wp.dogs.presentation.breeds_list.BreedsListUiAction.OpenBreedDetails
import pl.wp.dogs.presentation.breeds_list.BreedsListUiAction.ShowError
import pl.wp.dogs.presentation.breeds_list.model.BreedMapper
import javax.inject.Inject

@HiltViewModel
class BreedsListViewModel @Inject constructor(
    private val breedMapper: BreedMapper,
    private val getAllBreedsUseCase: GetAllBreedsUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData(BreedsListUiState())
    val uiState: LiveData<BreedsListUiState> = _uiState

    private val actionChannel = Channel<BreedsListUiAction>(Channel.BUFFERED)
    val actions = actionChannel.receiveAsFlow()

    init {
        fetchBreeds()
    }

    fun handleUiEvent(uiEvent: BreedsListUiEvent) {
        when (uiEvent) {
            is BreedsListUiEvent.BreedsPressed -> onBreedsPressed(uiEvent.breedName)
        }
    }

    private fun fetchBreeds() = viewModelScope.launch {
        val result = getAllBreedsUseCase()
        result.onSuccess { response ->
            val breedsList = breedMapper.mapToBreedUiModel(response.message)
            _uiState.value = uiState.newBuilder { copy(breeds = breedsList) }
        }.onFailure { exception ->
            actionChannel.send(ShowError(errorMessage = exception.message))
        }
    }


    private fun onBreedsPressed(breedName: String) = viewModelScope.launch {
        actionChannel.send(OpenBreedDetails(breedName = breedName))
    }
}
