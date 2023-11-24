package com.falikiali.menurecipeapp.presentation.recipe

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.falikiali.menurecipeapp.R
import com.falikiali.menurecipeapp.databinding.ActivityRecipeBinding
import com.falikiali.menurecipeapp.domain.model.Menu
import com.falikiali.menurecipeapp.helper.ResultState
import com.falikiali.menurecipeapp.presentation.bookmark.BookmarkActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeActivity : AppCompatActivity() {

    companion object {
        const val ID_MENU = "id menu"
    }

    private val binding: ActivityRecipeBinding by lazy { ActivityRecipeBinding.inflate(layoutInflater) }
    private val viewModel: RecipeViewModel by viewModels()
    private val idMenu: String by lazy { intent.getStringExtra(ID_MENU)!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setActionBar()
        getRecipe()
        observeRecipe()
        observeRecipeBookmark()
        observeBookmark()
        actionBtnRetry()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setActionBar() {
        supportActionBar?.title = "Recipe"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0F
    }

    private fun getRecipe() {
        viewModel.getRecipe(idMenu)
    }

    private fun checkRecipe() {
        viewModel.checkRecipeBookmark(idMenu)
    }

    private fun observeRecipe() {
        viewModel.recipeState.observe(this@RecipeActivity) {
            with(binding) {
                progressBar.isVisible = it is ResultState.Loading
                btnRetry.isVisible = it is ResultState.Failed
                vgContent.isVisible = it is ResultState.Success
            }

            if (it is ResultState.Success) {
                with(binding) {
                    tvMenuName.text = it.data.name
                    tvMenuCategoryArea.text = "${it.data.category} | ${it.data.area}"
                    tvInstructions.text = it.data.instructions

                    it.data.ingredients.forEach {ingredient ->
                        tvIngredients.append("\n~ ${ingredient.name} (${ingredient.measure})")
                    }
                }

                Glide.with(this@RecipeActivity)
                    .load(it.data.thumbnail)
                    .into(binding.ivThumbnail)

                checkRecipe()
                actionBtnYoutube(it.data.youtube)
                actionBtnBookmark(
                    Menu(
                        it.data.id,
                        it.data.name,
                        it.data.thumbnail
                    )
                )
            } else if (it is ResultState.Failed) {
                showSnackbar(it.error)
            }
        }
    }

    private fun observeRecipeBookmark() {
        viewModel.isRecipeBookmarked.observe(this@RecipeActivity) {
            if (it) {
                binding.btnBookmark.setImageResource(R.drawable.ic_bookmark_fill)
            } else {
                binding.btnBookmark.setImageResource(R.drawable.ic_bookmark_outline)
            }
        }
    }

    private fun observeBookmark() {
        viewModel.bookmarkState.observe(this@RecipeActivity) {
            if (!it.state) {
                showSnackbar("Add ${it.menu} to bookmark")
            } else {
                showSnackbar("Remove ${it.menu} from bookmark")
            }
        }
    }

    private fun actionBtnBookmark(menu: Menu) {
        binding.btnBookmark.setOnClickListener {
            viewModel.updateRecipeBookmark(menu)
        }
    }

    private fun actionBtnYoutube(url: String) {
        val uri = Uri.parse(url)
        binding.btnYoutube.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, uri)
            startActivity(i)
        }
    }

    private fun actionBtnRetry() {
        binding.btnRetry.setOnClickListener {
            getRecipe()
        }
    }

    private fun showSnackbar(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }

}