package de.joyn.myapplication.ui.fragments.photoDetail

import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.android.support.AndroidSupportInjection
import de.joyn.myapplication.R
import de.joyn.myapplication.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_photo.*
import javax.inject.Inject

class PhotoDetailFragment : BaseFragment<PhotoDetailViewModel>() {

    @Inject
    lateinit var photoDetailViewModelFactory: PhotoDetailViewModelFactory

    override fun injectDependencies(fragment: Fragment) {
        AndroidSupportInjection.inject(this)
    }

    override fun getLayout(): Int =
        R.layout.fragment_photo


    override fun onCreateCompleted() {
        setHasOptionsMenu(true)
        createViewModel()
    }

    override fun onStart() {
        super.onStart()
        startObserving()
    }

    override fun onResume() {
        super.onResume()
        bindBundle()
    }

    private fun createViewModel() {
        viewModel = ViewModelProviders.of(this, photoDetailViewModelFactory).get(PhotoDetailViewModel::class.java)
    }

    private fun startObserving() {
        viewModel.observableStatus.observe(this, Observer { status ->
            status
        })
    }

    private fun bindBundle() {
        PhotoDetailFragmentArgs.fromBundle(arguments!!).apply {
            val imgUri = imageUrl.toUri().buildUpon().scheme("https").build()
            imgLargePhoto.setImageURI(imgUri)
            Glide.with(context!!)
                .load(imgUri)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image)
                )
                .into(imgLargePhoto)
            txtTags.text = tags
            txtUserName.text = userName

        }
    }

}