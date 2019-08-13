package de.joyn.myapplication.ui.fragments.photos

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import de.joyn.myapplication.R
import de.joyn.myapplication.databinding.FragmentPhotoListBinding
import de.joyn.myapplication.network.dto.Models
import de.joyn.myapplication.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_photo_list.*
import timber.log.Timber

class PhotosFragment : BaseFragment<PagedList<Models.PhotoResponse>, PhotosViewModel>(),
    SearchView.OnQueryTextListener {

    override fun handleState(state: PagedList<Models.PhotoResponse>) {
        render(state)
    }

    private val clickListener: ClickListener = this::onPhotoClicked

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

    private val photoListAdapter = PhotoAdapter(clickListener)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val binding = DataBindingUtil.inflate<FragmentPhotoListBinding>(inflater, getLayout(), container, false)
        initRecyclerView(binding)
        return binding.root
    }

    override fun getLayout(): Int {
        return R.layout.fragment_photo_list
    }

    override fun onCreateCompleted() {

        createViewModel(PhotosViewModel::class.java)
        //set default value for searchView
        viewModel.setFilter(getString(R.string.search_filter_default_value))

    }


    private fun render(pagedPhotoList: PagedList<Models.PhotoResponse>) {
        photoListAdapter.submitList(pagedPhotoList)
        Timber.d("pagedPhotoList : %s", pagedPhotoList)
    }

    private fun initRecyclerView(binding: FragmentPhotoListBinding) {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
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