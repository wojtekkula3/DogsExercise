package pl.wp.dogs.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AllBreedsResponse(
    val message: Map<String, List<String>>,
    val status: String
)
