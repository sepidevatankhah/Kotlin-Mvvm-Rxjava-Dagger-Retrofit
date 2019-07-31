package de.joyn.myapplication.ui.photoList

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.joyn.myapplication.R
import de.joyn.myapplication.ui.base.BaseDaggerActivity
import de.joyn.myapplication.ui.photoDetail.PhotoDetailActivity
import de.joyn.myapplication.ui.photoList.flowerViewholder.FlowerRecyclerView
import de.joyn.myapplication.util.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.activity_flower_list.*
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider



class PhotoListActivity : BaseDaggerActivity<PhotoListViewState, PhotoListViewModel>() {

    @Inject
    lateinit var flowerAdapterProvider: Provider<FlowerRecyclerView>
    lateinit var flowerAdapter: FlowerRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flower_list)
        createViewModel(PhotoListViewModel::class.java)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        flowerAdapter = flowerAdapterProvider.get()
        recyclerView.adapter = flowerAdapter
        var gridLayoutManager = GridLayoutManager(this,1)
        recyclerView.layoutManager = gridLayoutManager

        recyclerView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(gridLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                //todo load more
            }
        })
        val disposable = flowerAdapter.mClickPS
            .subscribe { action ->
                Timber.i("clicked-- ${action.adapterPosition}")
//                val bundle = Bundle()
//                bundle.putString("key", ) //Your id
//                intent.putExtras(b) //Put your id to your next Intent
                startActivity(Intent(this@PhotoListActivity,PhotoDetailActivity::class.java))
            }
        compositDesposable.add(disposable)

    }

    override fun handleState(state: PhotoListViewState) {
        flowerAdapter.submitList(state.flowerModels)
    }
}