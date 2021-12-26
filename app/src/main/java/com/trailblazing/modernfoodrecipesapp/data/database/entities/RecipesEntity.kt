package com.trailblazing.modernfoodrecipesapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.trailblazing.modernfoodrecipesapp.models.FoodRecipe
import com.trailblazing.modernfoodrecipesapp.util.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0

}