package de.joyn.myapplication.ui.fragments.photoDetail

import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.joyn.myapplication.R
import de.joyn.myapplication.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_photo.*

class PhotoDetailFragment : BaseFragment<Boolean, PhotoDetailViewModel>() {

    override fun handleState(state: Boolean) {
    }

    override fun getLayout(): Int =
        R.layout.fragment_photo


    override fun onCreateCompleted() {
        setHasOptionsMenu(true)
        createViewModel(PhotoDetailViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        bindBundle()
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