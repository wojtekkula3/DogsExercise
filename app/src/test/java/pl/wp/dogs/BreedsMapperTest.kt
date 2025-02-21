package pl.wp.dogs

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import pl.wp.dogs.presentation.breeds_list.model.BreedMapper
import pl.wp.dogs.presentation.breeds_list.model.BreedUiModel

class BreedMapperTest {

    private val mapper = BreedMapper()

    @Test
    fun `Should correctly map breeds response to breed ui model`() = runTest {
        val breedsResponse = mapOf("Labrador" to emptyList<String>())
        val expectedModel = listOf(BreedUiModel("Labrador"))

        val result = mapper.mapToBreedUiModel(breedsResponse)

        assertEquals(result, expectedModel)
    }

    @Test
    fun `Should correctly map empty breeds response to empty breed ui model`() {
        val breedsResponse = emptyMap<String, List<String>>()

        val result = mapper.mapToBreedUiModel(breedsResponse)

        assertEquals(result, emptyList<BreedUiModel>())
    }
}
