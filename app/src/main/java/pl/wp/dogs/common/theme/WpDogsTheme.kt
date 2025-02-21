package pl.wp.dogs.common.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object WpDogsTheme {
    val dimensions: WpDogsDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current
}

@Composable
fun WpDogsTheme(
    dimensions: WpDogsDimensions = WpDogsTheme.dimensions,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalDimensions provides dimensions
    ) {
        content()
    }
}

@Composable
fun WpDogsAppTheme(content: @Composable () -> Unit) {
    WpDogsTheme(content = content)
}
