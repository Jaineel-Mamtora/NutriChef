package com.trailblazing.modernfoodrecipesapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.trailblazing.modernfoodrecipesapp.models.Result
import com.trailblazing.modernfoodrecipesapp.util.Constants.Companion.FAVORITE_RECIPES_TABLE

@Entity(tableName = FAVORITE_RECIPES_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result,
) {
}