package de.joyn.myapplication.ui.photoList

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.joyn.myapplication.R
import de.joyn.myapplication.ui.base.BaseDaggerActivity
import de.joyn.myapplication.ui.photoDetail.PhotoDetailActivity
import de.joyn.myapplication.ui.photoList.flowerViewholder.PhotoRecyclerView
import de.joyn.myapplication.util.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.activity_flower_list.*
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider


class PhotoListActivity : BaseDaggerActivity<PhotoListViewState, PhotoListViewModel>() {

    @Inject
    lateinit var flowerAdapterProvider: Provider<PhotoRecyclerView>
    lateinit var flowerAdapter: PhotoRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flower_list)
        createViewModel(PhotoListViewModel::class.java)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        flowerAdapter = flowerAdapterProvider.get()
        recyclerView.adapter = flowerAdapter
        var gridLayoutManager = GridLayoutManager(this, 1)
        recyclerView.layoutManager = gridLayoutManager

        recyclerView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(gridLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                //todo load more
            }
        })
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
     * Inflates the overflow menu that contains filtering options.
     */
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.overflow_menu, menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }
}