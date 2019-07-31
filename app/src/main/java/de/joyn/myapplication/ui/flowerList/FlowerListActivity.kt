package de.joyn.myapplication.ui.flowerList

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import de.joyn.myapplication.R
import de.joyn.myapplication.ui.base.BaseDaggerActivity
import de.joyn.myapplication.ui.flowerList.flowerViewholder.FlowerRecyclerView
import kotlinx.android.synthetic.main.activity_flower_list.*
import javax.inject.Inject
import javax.inject.Provider

class FlowerListActivity : BaseDaggerActivity<FlowerListViewState, FlowerListViewModel>() {

    @Inject
    lateinit var flowerAdapterProvider: Provider<FlowerRecyclerView>
    lateinit var flowerAdapter: FlowerRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flower_list)
        createViewModel(FlowerListViewModel::class.java)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        flowerAdapter = flowerAdapterProvider.get()
        recyclerView.adapter = flowerAdapter
        var gridLayoutManager = GridLayoutManager(this,1)
        recyclerView.layoutManager = gridLayoutManager

    }

    override fun handleState(state: FlowerListViewState) {
        flowerAdapter.submitList(state.flowerModels)
    }
}