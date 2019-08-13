package de.joyn.myapplication.ui.fragments.photos

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import de.joyn.myapplication.R
import de.joyn.myapplication.databinding.FragmentPhotoListBinding
import de.joyn.myapplication.network.dto.Models
import de.joyn.myapplication.ui.base.BaseFragment
import timber.log.Timber

class PhotosFragment : BaseFragment<PagedList<Models.PhotoResponse>, PhotosViewModel>(),
    SearchView.OnQueryTextListener {

    override fun handleState(state: PagedList<Models.PhotoResponse>) {
        Timber.d("pagedList : %s", state)
        render(state)
    }


    private fun onPhotoClicked(photo: Models.PhotoResponse) {
        view?.let {
            findNavController(it).navigate(
                PhotosFragmentDirections.actionPhotosFragmentToPhotoDetailFragment(
                    photo.previewImageUrl,
                    photo.userName,
                    photo.tags
                )
            )
        }
    }

    private lateinit var photoListAdapter: PhotoAdapter

    /**
     * Called when the Fragment is ready to display content to the screen.
     *
     * This function uses DataBindingUtil to inflate R.layout.fragment_photo_list.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)

        val binding = DataBindingUtil.inflate<FragmentPhotoListBinding>(inflater, getLayout(), container, false)

        createViewModel(PhotosViewModel::class.java)

        binding.photoList = viewModel
        binding.lifecycleOwner = this

        //set default value for searchView
        viewModel.setFilter(getString(R.string.search_filter_default_value))


        initRecyclerView(binding)

        viewModel.navigateToPhotoDetail.observe(this, Observer { photo ->
            photo?.let {
                onPhotoClicked(photo)
                viewModel.onPhotoDetailNavigated()
            }
        })
        return binding.root
    }

    override fun getLayout(): Int {
        return R.layout.fragment_photo_list
    }

    override fun onCreateCompleted() {
    }


    private fun render(pagedPhotoList: PagedList<Models.PhotoResponse>) {
        photoListAdapter.submitList(pagedPhotoList)
        Timber.d("pagedPhotoList : %s", pagedPhotoList)
    }

    private fun initRecyclerView(binding: FragmentPhotoListBinding) {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            photoListAdapter = PhotoAdapter(PhotoClickListener { photo ->
                viewModel.onPhotoClicked(photo)
            })
            adapter = photoListAdapter
        }

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu, menu)

        // Get the SearchView and set the searchable configuration
        val searchManager = activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.app_bar_search).actionView as SearchView).apply {
            // Assumes current activity is the searchable activity
            setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))
            setIconifiedByDefault(false) // Do not iconify the widget; expand it by default
            queryHint = getString(R.string.search_view_hint)
            setQuery(
                if (viewModel.cachedFilter.isEmpty()) getString(R.string.search_filter_default_value) else viewModel.cachedFilter,
                true
            )
            isSubmitButtonEnabled = true
        }.setOnQueryTextListener(this)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!, view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Timber.d("query : %s", newText)
        if (newText!!.trim().replace(" ", "").length >= 3 || newText!!.isEmpty()) {
            viewModel.cachedFilter = newText
            viewModel.setFilter(newText!!)
            viewModel.createLiveData()
            startObserving()

        }
        return true
    }


}