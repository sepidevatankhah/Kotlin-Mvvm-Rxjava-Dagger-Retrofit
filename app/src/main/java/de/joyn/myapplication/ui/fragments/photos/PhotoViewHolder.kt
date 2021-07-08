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
import de.joyn.myapplication.network.dto.Models
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bind(photo: Models.PhotoResponse?) {
            photo?.apply {
                val imgUri = previewImageUrl.toUri().buildUpon().scheme("https").build()
                itemView.apply {
                    Glide.with(imgPreview.context)
                        .load(imgUri)
                        .apply(
                            RequestOptions()
                                .placeholder(R.drawable.loading_animation)
                                .error(R.drawable.ic_broken_image)
                        )
                        .into(imgPreview)
                    txtLikes.text = likeNumber
                    txtDownload.text = downloadNumber
                    txtUserName.text = userName
                    txtView.text = viewNumber
                }
            }
    }

    companion object {
        fun from(parent: ViewGroup): PhotoViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                .inflate(R.layout.item_photo, parent, false)

            return PhotoViewHolder(view)
        }
    }

}