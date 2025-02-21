package pl.wp.dogs.common.navigation

import androidx.navigation.NavController
import pl.wp.dogs.presentation.BreedsNavigation

class BreedsNavigationImpl(
    private val navController: NavController
) : BreedsNavigation {
    override fun navigateUp() {
        navController.popBackStack()
    }

    override fun openBreedDetails(breedName: String) {
        navController.navigate(BreedsGraph.BreedDetailsDestination(breedName))
    }
}
