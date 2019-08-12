package de.joyn.myapplication.ui.fragments.photos

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.joyn.myapplication.R
import de.joyn.myapplication.network.dto.Models

@BindingAdapter("imgPreview")
fun ImageView.setImagePreview(item: Models.PhotoResponse?) {
    item?.let {
        var imgUri = item.previewImageUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(this)
    }
}

@BindingAdapter("txtView")
fun TextView.setViewsText(item: Models.PhotoResponse?) {
    item?.let { text = item.viewNumber }
}

@BindingAdapter("txtDownload")
fun TextView.setDownloadsText(item: Models.PhotoResponse?) {
    item?.let { text = item.downloadNumber }
}

@BindingAdapter("txtUserName")
fun TextView.setUserNameText(item: Models.PhotoResponse?) {
    item?.let { text = item.userName }
}

@BindingAdapter("txtLikes")
fun TextView.setLikesText(item: Models.PhotoResponse?) {
    item?.let { text = item.likeNumber }
}