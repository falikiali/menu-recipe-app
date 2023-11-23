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
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()

    private lateinit var filterAdapter: FilterAdapter
    private lateinit var menuAdapter: MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getCategories()
        getFirstMenu()
        observeCategories()
        observeMenuByCategory()
        observeSearchMenu()
        initRvFilter()
        initRvMenu()
        searchMenu()
        actionBtnBookmark()
        actionBtnRetry()
    }

    private fun getCategories() {
        viewModel.getCategories()
    }

    private fun getFirstMenu() {
        viewModel.getMenuByCategory("Beef")
    }

    private fun observeCategories() {
        viewModel.categoriesState.observe(this@MainActivity) {
            if (it is ResultState.Success) {
                binding.rvFilter.scrollToPosition(0)
                filterAdapter.submitList(it.data)
            } else if (it is ResultState.Failed) {
                showSnackbar(it.error)
            }
        }
    }

    private fun observeMenuByCategory() {
        viewModel.menuByCategoryState.observe(this@MainActivity) {
            with(binding) {
                progressBar.isVisible = it is ResultState.Loading
                rvMenu.isVisible = it is ResultState.Success
                btnRetry.isVisible = it is ResultState.Failed
            }

            if (it is ResultState.Success) {
                binding.rvMenu.scrollToPosition(0)
                menuAdapter.submitList(it.data)
            } else if (it is ResultState.Failed) {
                showSnackbar(it.error)
            }
        }
    }

    private fun observeSearchMenu() {
        viewModel.searchState.observe(this@MainActivity) {
            with(binding) {
                progressBar.isVisible = it is ResultState.Loading
                rvMenu.isVisible = it is ResultState.Success
                btnRetry.isVisible = it is ResultState.Failed
            }

            if (it is ResultState.Success) {
                binding.rvMenu.scrollToPosition(0)
                menuAdapter.submitList(it.data)
            } else if (it is ResultState.Failed) {
                showSnackbar(it.error)
            }
        }
    }

    private fun initRvFilter() {
        filterAdapter = FilterAdapter(onItemClick = {
            viewModel.getMenuByCategory(it.name)
        })

        with(binding.rvFilter) {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = filterAdapter
        }
    }

    private fun initRvMenu() {
        menuAdapter = MenuAdapter()
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

    private fun actionBtnRetry() {
        binding.btnRetry.setOnClickListener {
            getCategories()
            getFirstMenu()
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

}