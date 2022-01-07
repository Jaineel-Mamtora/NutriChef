package com.trailblazing.modernfoodrecipesapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.trailblazing.modernfoodrecipesapp.data.database.entities.FavoritesEntity
import com.trailblazing.modernfoodrecipesapp.databinding.FavoriteRecipesRowLayoutBinding
import com.trailblazing.modernfoodrecipesapp.ui.fragments.favorites.FavoriteRecipesFragmentDirections
import com.trailblazing.modernfoodrecipesapp.util.RecipesDiffUtil
import kotlinx.android.synthetic.main.favorite_recipes_row_layout.view.*

class FavoriteRecipesAdapter : RecyclerView.Adapter<FavoriteRecipesAdapter.MyViewHolder>() {

    private var favoriteRecipes = emptyList<FavoritesEntity>()

    class MyViewHolder(
        private val binding: FavoriteRecipesRowLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(favoritesEntity: FavoritesEntity) {
            binding.favoritesEntity = favoritesEntity
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavoriteRecipesRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val selectedRecipe = favoriteRecipes[position]
        holder.bind(selectedRecipe)

        /**
         * Single Click Listener
         */

        holder.itemView.favorite_recipes_row_layout.setOnClickListener {
            val action =
                FavoriteRecipesFragmentDirections.actionFavoriteRecipesFragmentToDetailsActivity(
                    selectedRecipe.result
                )
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return favoriteRecipes.size
    }

    fun setData(newFavoriteRecipes: List<FavoritesEntity>) {
        val favoriteRecipesDiffUtil = RecipesDiffUtil(favoriteRecipes, newFavoriteRecipes)
        val diffUtilResult = DiffUtil.calculateDiff(favoriteRecipesDiffUtil)
        favoriteRecipes = newFavoriteRecipes
        diffUtilResult.dispatchUpdatesTo(this)
    }
}