package com.falikiali.menurecipeapp.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.falikiali.menurecipeapp.databinding.ActivityMainBinding
import com.falikiali.menurecipeapp.helper.ResultState
import com.falikiali.menurecipeapp.presentation.bookmark.BookmarkActivity
import com.falikiali.menurecipeapp.presentation.recipe.RecipeActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()

    private lateinit var filterCategoryAdapter: FilterCategoryAdapter
    private lateinit var filterAreaAdapter: FilterAreaAdapter
    private lateinit var menuAdapter: MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initData()
        observeCategories()
        observeAreas()
        observeMenus()
        searchMenu()
        actionBtnBookmark()
        actionBtnRetry()
    }

    private fun initData() {
        observeFilter()
        initRvMenu()
    }

    private fun observeCategories() {
        viewModel.categoriesState.observe(this@MainActivity) {
            if (it is ResultState.Success) {
                binding.rvFilter.scrollToPosition(0)
                filterCategoryAdapter.submitList(it.data)
                actionBtnFilter()
            } else if (it is ResultState.Failed) {
                showSnackBar(it.error)
            }
        }
    }

    private fun observeAreas() {
        viewModel.areasState.observe(this@MainActivity) {
            if (it is ResultState.Success) {
                binding.rvFilter.scrollToPosition(0)
                filterAreaAdapter.submitList(it.data)
                actionBtnFilter()
            } else if (it is ResultState.Failed) {
                showSnackBar(it.error)
            }
        }
    }

    private fun observeFilter() {
        viewModel.filterCategoryState.observe(this@MainActivity) {
            if (it) {
                viewModel.getCategories()
                viewModel.getMenuByCategory("Beef")
                initRvFilterCategory()

                binding.btnFilter.text = "Category"
            } else {
                viewModel.getAreas()
                viewModel.getMenuByArea("American")
                initRvFilterArea()

                binding.btnFilter.text = "Area"
            }
        }
    }

    private fun observeMenus() {
        viewModel.menuState.observe(this@MainActivity) {
            with(binding) {
                progressBar.isVisible = it is ResultState.Loading
                rvMenu.isVisible = it is ResultState.Success
                btnRetry.isVisible = it is ResultState.Failed
            }

            if (it is ResultState.Success) {
                binding.rvMenu.scrollToPosition(0)
                menuAdapter.submitList(it.data)
            } else if (it is ResultState.Failed) {
                showSnackBar(it.error)
            }
        }
    }

    private fun initRvFilterCategory() {
        filterCategoryAdapter = FilterCategoryAdapter(onItemClick = {
            viewModel.getMenuByCategory(it.name)
        })

        with(binding.rvFilter) {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = filterCategoryAdapter
        }
    }

    private fun initRvFilterArea() {
        filterAreaAdapter = FilterAreaAdapter(onItemClick = {
            viewModel.getMenuByArea(it.name)
        })

        with(binding.rvFilter) {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = filterAreaAdapter
        }
    }

    private fun initRvMenu() {
        menuAdapter = MenuAdapter(onItemClick = {
            val i = Intent(this@MainActivity, RecipeActivity::class.java).apply {
                putExtra(RecipeActivity.ID_MENU, it.id)
            }
            startActivity(i)
        })

        with(binding.rvMenu) {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = menuAdapter
        }
    }

    private fun searchMenu() {
        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0 != null) {
                    if (p0.isNotBlank()) {
                        viewModel.searchMenu(p0)
                    }
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        } )
    }

    private fun actionBtnBookmark() {
        binding.btnBookmark.setOnClickListener {
            val i = Intent(this@MainActivity, BookmarkActivity::class.java)
            startActivity(i)
        }
    }

    private fun actionBtnFilter() {
        binding.btnFilter.setOnClickListener {
            viewModel.updateFilter()
        }
    }

    private fun actionBtnRetry() {
        binding.btnRetry.setOnClickListener {
            initData()
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

}