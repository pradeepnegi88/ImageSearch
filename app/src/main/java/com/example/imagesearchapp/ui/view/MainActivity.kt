package com.example.imagesearchapp.ui.view

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesearchapp.R
import com.example.imagesearchapp.domain.query.model.Photo
import com.example.imagesearchapp.ui.adapter.MainAdapter
import com.example.imagesearchapp.ui.adapter.MainAdapter.OnItemClickListener
import com.example.imagesearchapp.ui.viewmodel.MainViewModel
import com.example.imagesearchapp.utils.Constants
import com.example.imagesearchapp.utils.Status
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupObserver()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.maxWidth = Integer.MAX_VALUE
        // listening to search query text change
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mainViewModel?.fetchQueryResource(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_search) {
            true
        } else super.onOptionsItemSelected(item)

    }

    private fun setupUI() {
        recyclerView.layoutManager =
            GridLayoutManager(this@MainActivity, 2, RecyclerView.VERTICAL, false)
        adapter = MainAdapter(arrayListOf(), object : OnItemClickListener {
            override fun onItemClick(item: Photo) {
                redirectToPhotoDescription(item)
            }
        })
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.imageResponse.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { imageResponse -> renderList(imageResponse) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(imageResponse: List<Photo>) {
        adapter.addData(imageResponse)
        adapter.notifyDataSetChanged()
    }

    private fun redirectToPhotoDescription(item: Photo) {
        val intent = Intent(this@MainActivity, PhotoDescriptionActivity::class.java)
        intent.putExtra(Constants.TITLE_KEY, item.title)
        intent.putExtra(Constants.IMAGE_KEY, item.imageUrl)
        intent.putExtra(Constants.ID_KEY, item.id)
        startActivity(intent)
    }
}