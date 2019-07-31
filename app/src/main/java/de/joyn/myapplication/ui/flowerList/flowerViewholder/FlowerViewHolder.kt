package de.joyn.myapplication.ui.flowerList.flowerViewholder

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jakewharton.rxbinding2.view.RxView
import de.joyn.myapplication.R
import de.joyn.myapplication.network.dto.Models
import de.joyn.myapplication.ui.base.recyclerview.BaseViewHolder
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_flower.view.*

class FlowerViewHolder (itemView : View , viewModel : FlowerViewModel) :
BaseViewHolder<FlowerViewHolderAction , Models.FlowerResponse , FlowerViewModel>(itemView , viewModel){

    val imgPreviewUrl: ImageView by lazy {itemView.img_flower}

    override fun bind() {

        var imgUrl = vm.`object`!!.previewImageUrl
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgPreviewUrl.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imgPreviewUrl)
    }

    override fun itemOnClick(actionSubject: PublishSubject<FlowerViewHolderAction>) {
        RxView.clicks(itemView.findViewById(R.id.flower_container))
            .map { o -> FlowerViewHolderAction.createSelectAction(adapterPosition) }
            .repeat()
            .subscribe(actionSubject)
    }
}