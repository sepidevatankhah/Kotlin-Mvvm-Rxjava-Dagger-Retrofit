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
import kotlinx.android.synthetic.main.fragment_photo.*

class PhotoDetailFragment : BaseFragment<Boolean, PhotoDetailViewModel>() {

    override fun handleState(state: Boolean) {
    }

    override fun getLayout(): Int =
        R.layout.fragment_photo

   lateinit var binding: FragmentPhotoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentPhotoBinding>(inflater, getLayout(), container, false)

        return binding.root
    }

    override fun onCreateCompleted() {

        createViewModel(PhotoDetailViewModel::class.java)
    }


    override fun onResume() {
        super.onResume()
        bindBundle()
    }

//    private fun bindBundle() {
//        PhotoDetailFragmentArgs.fromBundle(arguments!!).apply {
//            val imgUri = imageUrl.toUri().buildUpon().scheme("https").build()
//            //imgLargePhoto.setImageURI(imgUri)
//            Glide.with(context!!)
//                .load(imgUri)
//                .apply(
//                    RequestOptions()
//                        .placeholder(R.drawable.loading_animation)
//                        .error(R.drawable.ic_broken_image)
//                )
//                .into(imgLargePhoto)
//            txtTags.text = tags
//            txtUserName.text = userName
//
//        }
//    }

    private fun bindBundle() {
        PhotoDetailFragmentArgs.fromBundle(arguments!!).apply {
            val imgUri = imageUrl.toUri().buildUpon().scheme("https").build()
            //imgLargePhoto.setImageURI(imgUri)
            Glide.with(context!!)
                .load(imgUri)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image)
                )
                .into(binding.imgLargePhoto)
            binding.txtTags.text = tags
            binding.txtUserName.text = userName

        }
    }


}