package com.swbvelasquez.complextestingandroidktx.data.remote

import com.swbvelasquez.complextestingandroidktx.BuildConfig
import com.swbvelasquez.complextestingandroidktx.data.remote.responses.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {
    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery:String,
        @Query("key") apiKey:String = BuildConfig.API_KEY //Llave de pixabai configurada en gradle.properties
    ) : Response<ImageResponse>
}