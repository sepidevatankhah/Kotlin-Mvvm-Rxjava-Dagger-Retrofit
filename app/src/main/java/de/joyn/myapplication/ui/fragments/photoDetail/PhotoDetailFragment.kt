package de.joyn.myapplication.ui.fragments.photoDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.joyn.myapplication.R
import de.joyn.myapplication.databinding.FragmentPhotoBinding
import de.joyn.myapplication.ui.base.BaseFragment


class PhotoDetailFragment : BaseFragment<Boolean, PhotoDetailViewModel>() {

    override fun handleState(state: Boolean) {
    }

    override fun getLayout(): Int =
        R.layout.fragment_photo

    lateinit var binding: FragmentPhotoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)

        createViewModel(PhotoDetailViewModel::class.java)

        // Set the viewmodel for databinding - this allows the bound layout access to all of the
        // data in the VieWModel
        binding.photoDetail = viewModel

        // Specify the current activity as the lifecycle owner of the binding. This is used so that
        // the binding can observe LiveData updates
        binding.lifecycleOwner = this
        bindBundle()
        return binding.root
    }

    override fun onCreateCompleted() {

    }


    private fun bindBundle() {
        PhotoDetailFragmentArgs.fromBundle(arguments!!)?.let {
            viewModel.setData(it.tags, it.userName,it.imageUrl)
            val imgUri = it.imageUrl.toUri().buildUpon().scheme("https").build()
            Glide.with(context!!)
                .load(imgUri)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image)
                )
                .into(binding.imgLargePhoto)
        }
    }


}