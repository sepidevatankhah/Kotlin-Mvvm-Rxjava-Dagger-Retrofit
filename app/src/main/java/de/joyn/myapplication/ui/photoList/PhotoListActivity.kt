package de.joyn.myapplication.ui.photoList

import android.os.Bundle
import android.view.Menu
import de.joyn.myapplication.R
import de.joyn.myapplication.ui.base.BaseDaggerActivity
import kotlinx.android.synthetic.main.activity_photo_list.*
import timber.log.Timber
import android.app.SearchManager
import android.content.Context
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import de.joyn.myapplication.ui.photoList.photoViewholder.PhotoListAdapter


class PhotoListActivity : BaseDaggerActivity<PhotoListViewState, PhotosViewModel>(),
    SearchView.OnQueryTextListener {


    private lateinit var photoListAdapter: PhotoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_list)
        createViewModel(PhotosViewModel::class.java)
        initRecyclerView()
    }

    private fun initRecyclerView() {



        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = photoListAdapter
        }


    }

    override fun handleState(state: PhotoListViewState) {
        photoListAdapter.submitList(state.photoModels)
    }

    /**
     * Inflates the search menu that contains filtering options.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.search_menu, menu)

        // Get the SearchView and set the searchable configuration
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu!!.findItem(R.id.app_bar_search).actionView as SearchView).apply {
            // Assumes current activity is the searchable activity
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(false) // Do not iconify the widget; expand it by default
            isSubmitButtonEnabled = true
        }.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Timber.d("query : %s", newText)
        if (newText!!.trim().replace(" ","").length >= 3)
            viewModel.getPhotos(newText)
        return true
    }


}