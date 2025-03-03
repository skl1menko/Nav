package com.example.nav

import com.example.nav.model.CocktailResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailAPI {
    @GET("filter.php?a=Alcoholic")
    suspend fun getAlcoholicCocktails(): CocktailResponse

    @GET("filter.php?a=Non_Alcoholic")
    suspend fun getNonAlcoholicCocktails(): CocktailResponse

    @GET("search.php")
    suspend fun searchCocktails(@Query("s") name: String): CocktailResponse

    companion object {
        val retrofitService: CocktailAPI by lazy {
            Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CocktailAPI::class.java)
        }
    }
}
