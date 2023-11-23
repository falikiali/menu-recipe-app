package com.falikiali.menurecipeapp.presentation.bookmark

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.falikiali.menurecipeapp.databinding.ActivityBookmarkBinding
import com.falikiali.menurecipeapp.presentation.main.MenuAdapter
import com.falikiali.menurecipeapp.presentation.recipe.RecipeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkActivity : AppCompatActivity() {

    private val binding: ActivityBookmarkBinding by lazy { ActivityBookmarkBinding.inflate(layoutInflater) }
    private val viewModel: BookmarkViewModel by viewModels()

    private lateinit var menuAdapter: MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setActionBar()
        getAllBookmarkMenu()
        observeBookmarkMenu()
        initRvMenu()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setActionBar() {
        supportActionBar?.title = "Bookmark Recipe"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0F
    }

    private fun initRvMenu() {
        menuAdapter = MenuAdapter(onItemClick = {
            val i = Intent(this@BookmarkActivity, RecipeActivity::class.java).apply {
                putExtra(RecipeActivity.ID_MENU, it.id)
            }
            startActivity(i)
        })

        with(binding.rvMenu) {
            layoutManager = GridLayoutManager(this@BookmarkActivity, 2)
            adapter = menuAdapter
        }
    }

    private fun getAllBookmarkMenu() {
        viewModel.getAllBookmarkMenu()
    }

    private fun observeBookmarkMenu() {
        viewModel.bookmarkMenuState.observe(this@BookmarkActivity) {
            with(binding) {
                rvMenu.isVisible = it.isNotEmpty()
                ivEmpty.isVisible = it.isEmpty()
                tvEmpty.isVisible = it.isEmpty()
            }

            menuAdapter.submitList(it)
        }
    }

}