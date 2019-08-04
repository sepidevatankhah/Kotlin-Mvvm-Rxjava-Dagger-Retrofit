package de.joyn.myapplication.ui.photoList.photoViewholder

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
import de.joyn.myapplication.ui.photoDetail.PhotoDetailActivity
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotoListViewHolder(containerView: View)
//(override val containerView: View) :
    : RecyclerView.ViewHolder(containerView) {//, LayoutContainer {

    fun bind(photo: Models.PhotoResponse?) {//, listener: (Models.PhotoResponse) -> Unit) {
        if (photo != null) {
            with(photo) {

                var imgUrl = previewImageUrl
                val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
                Glide.with(itemView.imgPreview.context)
                    .load(imgUri)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(itemView.imgPreview)
                itemView.txtLikes.text = likeNumber
                itemView.txtDownload.text = downloadNumber
                itemView.txtUserName.text = userName
                itemView.txtView.text = viewNumber
//                itemView.setOnClickListener {
//                    listener.invoke(this) }

                itemView.setOnClickListener {
                    Bundle().let { bundle ->
                        bundle.putString("IMAGE_URL", largeImageUrl)
                        bundle.putString("USER_NAME", userName)
                        bundle.putString("TAGS", tags)
                        var intent = Intent(itemView.context, PhotoDetailActivity::class.java)
                        intent.putExtras(bundle)
                        itemView.context.startActivity(intent)
                    }
                }

            }
        }


    }


    companion object {
        fun create(parent: ViewGroup): PhotoListViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_photo, parent, false)
            return PhotoListViewHolder(view)
        }
    }
}