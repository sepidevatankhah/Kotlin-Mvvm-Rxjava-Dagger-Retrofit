package de.joyn.myapplication.ui.photoList


import android.os.Bundle
import android.view.Menu
import de.joyn.myapplication.R
import kotlinx.android.synthetic.main.activity_photo_list.*
import timber.log.Timber
import android.app.SearchManager
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import de.joyn.myapplication.data.repository.NetworkState
import de.joyn.myapplication.data.repository.Status
import de.joyn.myapplication.network.dto.Models
import de.joyn.myapplication.ui.photoList.photoViewholder.PhotoListAdapter


class PhotoActivity : AppCompatActivity(),
    SearchView.OnQueryTextListener {


    private lateinit var photoListAdapter: PhotoListAdapter
    private lateinit var viewModel: PhotoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_list)

        viewModel = ViewModelProviders.of(this).get(PhotoListViewModel::class.java)
        Timber.d("PhotoActivity %s" , viewModel)
        initRecyclerView()
        initSwipeToRefresh()
    }

    private fun initRecyclerView() {

        photoListAdapter = PhotoListAdapter {
            viewModel.retry()
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@PhotoActivity)
            adapter = photoListAdapter
        }

        viewModel.photoList.observe(this, Observer<PagedList<Models.PhotoResponse>> { photoListAdapter.submitList(it) })
        viewModel.getNetworkState().observe(this, Observer<NetworkState> { photoListAdapter.setNetworkState(it) })

        Timber.d("viewModel.photoList %s" , viewModel.photoList)
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
//        if (newText!!.trim().replace(" ","").length >= 3)
//            //TODO:
//            viewModel.get(newText)
        return true
    }


    /**
     * Init swipe to refresh and enable pull to refresh only when there are items in the adapter
     */
    private fun initSwipeToRefresh() {
        viewModel.getRefreshState().observe(this, Observer { networkState ->
            if (photoListAdapter.currentList != null) {
                if (photoListAdapter.currentList!!.size > 0) {
                    swipeRefreshLayout.isRefreshing = networkState?.status == NetworkState.LOADING.status
                } else {
                    setInitialLoadingState(networkState)
                }
            } else {
                setInitialLoadingState(networkState)
            }
        })
        swipeRefreshLayout.setOnRefreshListener({ viewModel.refresh() })
    }

    /**
     * Show the current network state for the first load when the user list
     * in the adapter is empty and disable swipe to scroll at the first loading
     *
     * @param networkState the new network state
     */
    private fun setInitialLoadingState(networkState: NetworkState?) {
        //error message
//       var errorMessageTextView  = findViewById<TextView>(R.id.txtErrorMessage)
//       var loadingProgressBar  = findViewById<ProgressBar>(R.id.progressBarLoading)
//       var retryLoadingButton  = findViewById<Button>(R.id.btnRetryLoading)
//
//        errorMessageTextView.visibility = if (networkState?.message != null) View.VISIBLE else View.GONE
//        if (networkState?.message != null) {
//            errorMessageTextView.text = networkState.message
//        }
//
//        //loading and retry
//        retryLoadingButton.visibility = if (networkState?.status == Status.FAILED) View.VISIBLE else View.GONE
//        loadingProgressBar.visibility = if (networkState?.status == Status.RUNNING) View.VISIBLE else View.GONE
//
//        swipeRefreshLayout.isEnabled = networkState?.status == Status.SUCCESS
//        retryLoadingButton.setOnClickListener { viewModel.retry() }
    }


}