package com.trailblazing.modernfoodrecipesapp.data

import com.trailblazing.modernfoodrecipesapp.data.network.FoodRecipesApi
import com.trailblazing.modernfoodrecipesapp.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }

}