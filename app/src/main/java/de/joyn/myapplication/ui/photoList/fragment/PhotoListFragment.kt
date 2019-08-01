package de.joyn.myapplication.ui.photoList.fragment

//class FlowerListFragment : BaseDaggerFragment<PhotoViewState, PhotoViewModel>() {
//    @Inject
//    lateinit var flowerAdapterProvider: Provider<PhotoRecyclerView>
//    lateinit var flowerAdapter: PhotoRecyclerView
//
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view = inflater.inflate(R.layout.activity_photo_list,container,false)
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
//    override fun handleState(state: PhotoViewState) {
//        flowerAdapter.submitList(state.flowerModels)
//    }
//}