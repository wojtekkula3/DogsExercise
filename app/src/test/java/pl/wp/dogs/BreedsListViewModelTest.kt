package pl.wp.dogs

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.wp.dogs.data.network.model.AllBreedsResponse
import pl.wp.dogs.domain.GetAllBreedsUseCase
import pl.wp.dogs.presentation.breeds_list.BreedsListUiAction
import pl.wp.dogs.presentation.breeds_list.BreedsListUiEvent
import pl.wp.dogs.presentation.breeds_list.BreedsListViewModel
import pl.wp.dogs.presentation.breeds_list.model.BreedMapper
import pl.wp.dogs.presentation.breeds_list.model.BreedUiModel

@ExperimentalCoroutinesApi
class BreedsListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val breedMapper: BreedMapper = mockk()
    private val getAllBreedsUseCase: GetAllBreedsUseCase = mockk()
    private lateinit var viewModel: BreedsListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = BreedsListViewModel(breedMapper, getAllBreedsUseCase)
        every { breedMapper.mapToBreedUiModel(any()) } returns mockBreedUiModel
        coEvery { getAllBreedsUseCase() } returns Result.success(mockAllBreedsResponse)
    }

    @Test
    fun `On init should show breeds when fetch is successful`() = runTest {

        viewModel = BreedsListViewModel(breedMapper, getAllBreedsUseCase)
        advanceUntilIdle()

        assertEquals(viewModel.uiState.value?.breeds, mockBreedUiModel)
    }

    @Test
    fun `On init should show show failure when fetch failed`() = runTest {
        val errorMessage = "Error while fetching breeds"
        coEvery { getAllBreedsUseCase() } returns Result.failure(Exception(errorMessage))

        viewModel = BreedsListViewModel(breedMapper, getAllBreedsUseCase)

        viewModel.actions.test {
            advanceUntilIdle()
            val action = expectMostRecentItem()
            assertEquals(action, BreedsListUiAction.ShowError(errorMessage))
        }
    }

    @Test
    fun `On BreedsPressed should open breed details`() = runTest {
        val breedName = "Labrador"

        viewModel.handleUiEvent(BreedsListUiEvent.BreedsPressed(breedName))

        viewModel.actions.test {
            advanceUntilIdle()
            val action = expectMostRecentItem()
            assertEquals(action, BreedsListUiAction.OpenBreedDetails(breedName))
        }
    }
}

private val mockAllBreedsResponse = AllBreedsResponse(
    message = mapOf(
        "Labrador" to listOf("subBreed1"),
        "Poodle" to emptyList()
    ),
    status = "success"
)

private val mockBreedUiModel = listOf(BreedUiModel("Labrador"), BreedUiModel("Poodle"))