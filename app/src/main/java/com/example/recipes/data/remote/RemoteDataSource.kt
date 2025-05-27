package com.example.recipes.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RemoteDataSource {
    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val api: RecipeApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.sampleapis.com/recipes/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(RecipeApiService::class.java)
    }
}