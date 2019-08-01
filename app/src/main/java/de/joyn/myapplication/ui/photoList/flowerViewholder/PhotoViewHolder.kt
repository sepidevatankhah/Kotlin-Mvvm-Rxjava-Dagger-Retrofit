package de.joyn.myapplication.ui.photoList.flowerViewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jakewharton.rxbinding2.view.RxView
import de.joyn.myapplication.R
import de.joyn.myapplication.network.dto.Models
import de.joyn.myapplication.ui.base.recyclerview.BaseViewHolder
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotoViewHolder(itemView: View, viewModel: PhotoViewModel) :
    BaseViewHolder<PhotoViewHolderAction, Models.FlowerResponse, PhotoViewModel>(itemView, viewModel) {

    private val imgPreviewUrl: ImageView by lazy { itemView.img_flower }
    private val txtLikes: TextView by lazy { itemView.txtLikes }
    private val txtDownloads: TextView by lazy { itemView.txtDownload }
    private val txtUserName: TextView by lazy { itemView.txtUserName }
    private val txtViews: TextView by lazy { itemView.txtView }
    private val container: ConstraintLayout by lazy { itemView.flower_container }

    override fun bind() {

        var imgUrl = vm.`object`!!.previewImageUrl
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgPreviewUrl.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgPreviewUrl)
        txtLikes.text = vm.`object`!!.likeNumber
        txtDownloads.text = vm.`object`!!.downloadNumber
        txtUserName.text = vm.`object`!!.userName
        txtViews.text = vm.`object`!!.viewNumber

//        container.setOnClickListener {
//            startActivity(Intent(PhotoListActivity::class.java, PhotoDetailActivity::class.java))
//        }
    }

    override fun itemOnClick(actionSubject: PublishSubject<PhotoViewHolderAction> , currentRowData : Models.FlowerResponse? ) {
        RxView.clicks(itemView.findViewById(R.id.flower_container))
            .map { o -> PhotoViewHolderAction.createSelectAction(adapterPosition , currentRowData ) }
            .repeat()
            .subscribe(actionSubject)
    }
}