package de.joyn.myapplication.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.joyn.myapplication.R
import de.joyn.myapplication.domain.dataSource.ApiStatus
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


/**
 * This binding adapter displays the [ApiStatus] of the network request in an image view.  When
 * the request is loading, it displays a loading_animation.  If the request has an error, it
 * displays a broken image to reflect the connection error.  When the request is finished, it
 * hides the image view.
 */
//@BindingAdapter("apiStatus")
//fun bindStatus(statusImageView: ImageView, status: ApiStatus?) {
//    when (status) {
//        ApiStatus.LOADING -> {
//            statusImageView.visibility = View.VISIBLE
//            statusImageView.setImageResource(R.drawable.loading_animation)
//        }
//        ApiStatus.ERROR -> {
//            statusImageView.visibility = View.VISIBLE
//            statusImageView.setImageResource(R.drawable.ic_connection_error)
//        }
//        ApiStatus.DONE -> {
//            statusImageView.visibility = View.GONE
//        }
//    }
//}

@BindingAdapter("imgLargePhoto")
fun setLargePhoto(imgLargePhoto: ImageView,item: Models.PhotoResponse?) {
    item?.let {
        var imgUri = item.largeImageUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgLargePhoto.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgLargePhoto)
    }
}