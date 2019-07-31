package de.joyn.myapplication.ui.flowerList

import android.os.Bundle
import de.joyn.myapplication.R
import de.joyn.myapplication.ui.base.BaseDaggerActivity
import javax.inject.Inject
import javax.inject.Provider

class FlowerListActivity : BaseDaggerActivity<FlowerListViewState, FlowerListViewModel>() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flower_list)
        createViewModel(FlowerListViewModel::class.java)

    }

    override fun handleState(state: FlowerListViewState) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}