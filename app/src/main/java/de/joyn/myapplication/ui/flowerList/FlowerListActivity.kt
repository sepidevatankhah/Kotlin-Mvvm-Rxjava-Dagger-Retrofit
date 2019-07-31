package de.joyn.myapplication.ui.flowerList

import android.os.Bundle
import de.joyn.myapplication.R
import de.joyn.myapplication.ui.base.BaseDaggerActivity

class FlowerListActivity : BaseDaggerActivity<FlowerListViewState, FlowerListViewModel>() {

    //lateinit var venueAdapter: FlowerRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flower_list)

    }

    override fun handleState(state: FlowerListViewState) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}