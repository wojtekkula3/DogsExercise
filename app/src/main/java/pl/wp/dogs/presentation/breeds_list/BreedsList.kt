package pl.wp.dogs.presentation.breeds_list

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import pl.wp.dogs.R
import pl.wp.dogs.common.theme.LocalDimensions
import pl.wp.dogs.presentation.BreedsNavigation
import pl.wp.dogs.presentation.breeds_list.BreedsListUiAction.OpenBreedDetails
import pl.wp.dogs.presentation.breeds_list.BreedsListUiAction.ShowError
import pl.wp.dogs.presentation.breeds_list.BreedsListUiEvent.BreedsPressed
import pl.wp.dogs.presentation.breeds_list.model.BreedUiModel

@Composable
fun BreedsList(
    navigation: BreedsNavigation,
    viewModel: BreedsListViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.observeAsState(BreedsListUiState())
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.actions.collect { action ->
            when (action) {
                is OpenBreedDetails -> navigation.openBreedDetails(action.breedName)
                is ShowError -> Toast.makeText(
                    context,
                    action.errorMessage ?: "Something went wrong. Check your internet connection",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    BreedsListScreen(uiState = uiState.value, uiEvent = viewModel::handleUiEvent)
}

@Composable
private fun BreedsListScreen(
    uiState: BreedsListUiState,
    uiEvent: (BreedsListUiEvent) -> Unit = {}
) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = { TopBar() }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .verticalScroll(scrollState)
        ) {
            Column(modifier = Modifier.padding(horizontal = LocalDimensions.current.paddingLarge)) {
                uiState.breeds.forEach {
                    Text(
                        text = it.name,
                        modifier = Modifier
                            .clickable { uiEvent(BreedsPressed(it.name)) }
                            .padding(vertical = LocalDimensions.current.paddingMedium)
                    )
                }
            }

        }
    }
}

@Composable
private fun TopBar() {
    Text(
        text = stringResource(R.string.breeds),
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(LocalDimensions.current.paddingMedium)
    )
}

@Composable
@PreviewLightDark
fun BreedsListScreenPreview() {
    BreedsListScreen(
        uiState = BreedsListUiState(
            breeds = listOf(
                BreedUiModel("Afghan Hound"),
                BreedUiModel("Akita"),
                BreedUiModel("Bulldog"),
                BreedUiModel("Golden Retriever"),
                BreedUiModel("Labrador"),
                BreedUiModel("Terrier")
            )
        )
    )
}
