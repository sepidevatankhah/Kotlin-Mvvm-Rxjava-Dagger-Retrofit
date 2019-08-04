package de.joyn.myapplication.ui.photoList

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.GridLayoutManager
import de.joyn.myapplication.R
import de.joyn.myapplication.ui.base.BaseDaggerActivity
import de.joyn.myapplication.ui.photoDetail.PhotoDetailActivity
import de.joyn.myapplication.ui.photoList.photoViewholder.PhotoRecyclerView
import kotlinx.android.synthetic.main.activity_photo_list.*
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider
import android.app.SearchManager
import android.content.Context
import android.widget.SearchView


class PhotoListActivity : BaseDaggerActivity<PhotoListViewState, PhotoListViewModel>(), SearchView.OnQueryTextListener {


    @Inject
    lateinit var flowerAdapterProvider: Provider<PhotoRecyclerView>
    lateinit var flowerAdapter: PhotoRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_list)
        createViewModel(PhotoListViewModel::class.java)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        flowerAdapter = flowerAdapterProvider.get()
        recyclerView.adapter = flowerAdapter
        var gridLayoutManager = GridLayoutManager(this, 1)
        recyclerView.layoutManager = gridLayoutManager

//        recyclerView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(gridLayoutManager) {
//            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
//                //todo load more
//            }
//        })
        val disposable = flowerAdapter.mClickPS
            .subscribe { action ->
                Timber.i("clicked-- ${action.adapterPosition}")

                val model = action.currentRowData
                Timber.d("model : " + model)
                val bundle = Bundle()
                bundle.putString("IMAGE_URL", model!!.largeImageUrl)
                bundle.putString("USER_NAME", model!!.userName)
                bundle.putString("TAGS", model!!.tags)
                var intent = Intent(this@PhotoListActivity, PhotoDetailActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        compositDesposable.add(disposable)

    }

    override fun handleState(state: PhotoListViewState) {
        flowerAdapter.submitList(state.flowerModels)
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
        if (newText!!.trim().replace(" ","").length >= 3)
            viewModel.getPhotos(newText)
        return true
    }


}