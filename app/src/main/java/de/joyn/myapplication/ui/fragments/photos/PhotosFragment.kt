package de.joyn.myapplication.ui.fragments.photos

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import de.joyn.myapplication.R
import de.joyn.myapplication.network.dto.Models
import de.joyn.myapplication.ui.base.BaseFragment
import de.joyn.myapplication.ui.photoList.PhotoAdapter
import kotlinx.android.synthetic.main.activity_photo_list.*
import timber.log.Timber
import javax.inject.Inject

class PhotosFragment : BaseFragment() {

    private lateinit var viewModel: PhotosViewModel
    @Inject
    lateinit var photosViewModelFactory: PhotosViewModelFactory
    private val photoListAdapter = PhotoAdapter()

    override fun injectDependencies(fragment: Fragment) {
        AndroidSupportInjection.inject(this)
    }

    override fun getLayout(): Int {
        return R.layout.activity_photo_list
    }

    override fun onCreateCompleted() {
        initRecyclerView()
        viewModel = ViewModelProviders.of(this, photosViewModelFactory).get(PhotosViewModel::class.java)
        viewModel.photoList.observe(this, Observer { pagedPhotoList ->
            pagedPhotoList?.let { render(pagedPhotoList) }
        })

    }

    private fun render(pagedPhotoList: PagedList<Models.PhotoResponse>) {
        photoListAdapter.submitList(pagedPhotoList)
        Timber.d("pagedPhotoList : %s", pagedPhotoList)
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = photoListAdapter
        }

    }
}