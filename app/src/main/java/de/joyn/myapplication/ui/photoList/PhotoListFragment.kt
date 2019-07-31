//package de.joyn.myapplication.ui.flowerList
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.GridLayoutManager
//import de.joyn.myapplication.R
//import de.joyn.myapplication.ui.base.BaseDaggerFragment
//import de.joyn.myapplication.ui.flowerList.flowerViewholder.FlowerRecyclerView
//import kotlinx.android.synthetic.main.activity_flower_list.*
//import javax.inject.Inject
//import javax.inject.Provider
//
//class FlowerListFragment : BaseDaggerFragment<PhotoListViewState, PhotoListViewModel>() {
//    @Inject
//    lateinit var flowerAdapterProvider: Provider<FlowerRecyclerView>
//    lateinit var flowerAdapter: FlowerRecyclerView
//
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view = inflater.inflate(R.layout.activity_flower_list,container,false)
//
//        initRecyclerView()
//        return view.rootView
//    }
//
//    private fun initRecyclerView() {
//        flowerAdapter = flowerAdapterProvider.get()
//        recyclerView.adapter = flowerAdapter
//        var gridLayoutManager = GridLayoutManager(context,1)
//        recyclerView.layoutManager = gridLayoutManager
//
//    }
//
//    override fun handleState(state: PhotoListViewState) {
//        flowerAdapter.submitList(state.flowerModels)
//    }
//}