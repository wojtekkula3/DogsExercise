package pl.wp.dogs.common.networking

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

internal class RetrofitApiProvider @Inject constructor() : ApiProvider {
    private val moshi by lazy {
        Moshi.Builder().build()
    }

    override fun <T> provide(url: String, type: Class<T>): T {
        val httpClient = OkHttpClient.Builder().build()
        return Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .baseUrl(url)
            .build()
            .create(type)
    }
}
