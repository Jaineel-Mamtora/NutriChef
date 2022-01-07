package com.trailblazing.modernfoodrecipesapp.adapters

import android.view.*
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.trailblazing.modernfoodrecipesapp.R
import com.trailblazing.modernfoodrecipesapp.data.database.entities.FavoritesEntity
import com.trailblazing.modernfoodrecipesapp.databinding.FavoriteRecipesRowLayoutBinding
import com.trailblazing.modernfoodrecipesapp.ui.fragments.favorites.FavoriteRecipesFragmentDirections
import com.trailblazing.modernfoodrecipesapp.util.RecipesDiffUtil
import kotlinx.android.synthetic.main.favorite_recipes_row_layout.view.*

class FavoriteRecipesAdapter(
    private val requireActivity: FragmentActivity,
) : RecyclerView.Adapter<FavoriteRecipesAdapter.MyViewHolder>(),
    ActionMode.Callback {

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

        /**
         * Long Click Listener
         */

        holder.itemView.favorite_recipes_row_layout.setOnLongClickListener {
            requireActivity.startActionMode(this)

            true
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

    override fun onCreateActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {
        actionMode?.menuInflater?.inflate(R.menu.favorites_contextual_menu, menu)
        return true
    }

    override fun onPrepareActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {
        return true
    }

    override fun onActionItemClicked(actionMode: ActionMode?, menu: MenuItem?): Boolean {
        return true
    }

    override fun onDestroyActionMode(actionMode: ActionMode?) {

    }
}