package de.joyn.myapplication.ui.fragments.photos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.joyn.myapplication.R
import de.joyn.myapplication.databinding.ItemPhotoBinding
import de.joyn.myapplication.network.dto.Models
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotoViewHolder private constructor(private val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(photo: Models.PhotoResponse?) {
        if (photo != null) {
            with(photo) {
                var imgPreview = binding.imgPreview
                var imgUrl = previewImageUrl
                val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
                itemView.apply {
                    Glide.with(imgPreview.context)
                        .load(imgUri)
                        .apply(
                            RequestOptions()
                                .placeholder(R.drawable.loading_animation)
                                .error(R.drawable.ic_broken_image)
                        )
                        .into(imgPreview)
                    binding.txtLikes.text = likeNumber
                    binding.txtDownload.text = downloadNumber
                    binding.txtUserName.text = userName
                    binding.txtView.text = viewNumber
                }
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): PhotoViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemPhotoBinding.inflate(layoutInflater, parent, false)
            return PhotoViewHolder(binding)
        }
    }

}