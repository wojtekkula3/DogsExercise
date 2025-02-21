package pl.wp.dogs.common.navigation

import android.os.Bundle
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.compose.AndroidFragment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import pl.wp.dogs.presentation.breed_details.BreedFragment
import pl.wp.dogs.common.navigation.BreedsGraph.BreedDetailsDestination
import pl.wp.dogs.common.navigation.BreedsGraph.BreedsListDestination
import pl.wp.dogs.presentation.breeds_list.BreedsList

@Serializable
object BreedsGraph {
    @Serializable
    data object BreedsListDestination

    @Serializable
    data class BreedDetailsDestination(val breed: String)
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        contentWindowInsets = WindowInsets.systemBars
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = BreedsGraph,
            modifier = Modifier.padding(contentPadding)
        ) {
            navigation<BreedsGraph>(startDestination = BreedsListDestination) {
                composable<BreedsListDestination> {
                    BreedsList(navigation = BreedsNavigationImpl(navController))
                }

                composable<BreedDetailsDestination>(
                    typeMap = mapOf(navigationType<BreedDetailsDestination>())
                ) { entry ->
                    val args = entry.toRoute<BreedDetailsDestination>()
                    AndroidFragment(
                        clazz = BreedFragment::class.java,
                        modifier = Modifier.fillMaxSize(),
                        arguments = Bundle().apply { putString(DETAILS_FRAGMENT_BREED_NAME_ARG, args.breed) }
                    )
                }
            }
        }
    }
}

const val DETAILS_FRAGMENT_BREED_NAME_ARG = "breed_name"
