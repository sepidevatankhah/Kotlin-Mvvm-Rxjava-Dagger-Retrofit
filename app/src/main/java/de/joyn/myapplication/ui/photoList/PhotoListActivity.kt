package de.joyn.myapplication.ui.photoList

import android.os.Bundle
import android.view.Menu
import de.joyn.myapplication.R
import de.joyn.myapplication.ui.base.BaseDaggerActivity
import kotlinx.android.synthetic.main.fragment_photo_list.*
import timber.log.Timber
import android.app.SearchManager
import android.content.Context
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import de.joyn.myapplication.network.dto.Models
import de.joyn.myapplication.ui.fragments.photos.ClickListener
import de.joyn.myapplication.ui.fragments.photos.PhotoAdapter


class PhotoListActivity : BaseDaggerActivity<PhotoListViewState, PhotoListViewModel>(), SearchView.OnQueryTextListener {

    private val clickListener: ClickListener = this::onPhotoClicked

    private fun onPhotoClicked(photo: Models.PhotoResponse) {

    }
    private val photoListAdapter = PhotoAdapter(clickListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_photo_list)
        //viewModel.stateLiveData = viewModel.photoList as MutableLiveData<PhotoListViewState>
        createViewModel(PhotoListViewModel::class.java)

        viewModel = ViewModelProviders.of(this,viewModelFactory ).get(viewModel::class.java)
        viewModel.photoList.observe(this, Observer { photoList ->
            photoList?.let {  render(photoList) }
        })
        initRecyclerView()
    }

    fun render(photoList : PagedList<Models.PhotoResponse>)
    {
        photoListAdapter.submitList(photoList)
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = photoListAdapter
        }

    }

    override fun handleState(state: PhotoListViewState) {
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
            //text = "flowers"
            isSubmitButtonEnabled = true
        }.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }


    override fun onQueryTextSubmit(query: String): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Timber.d("query : %s", newText)
        if (newText!!.trim().replace(" ", "").length >= 3)
            viewModel.getPhotos(newText)
        return true
    }


}