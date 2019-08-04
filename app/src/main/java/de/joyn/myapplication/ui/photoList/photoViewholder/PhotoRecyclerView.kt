package de.joyn.myapplication.ui.photoList.photoViewholder

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.annotation.NonNull
import de.joyn.myapplication.network.dto.Models
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class PhotoRecyclerView @Inject constructor(
    @NonNull var diffCallback: PhotoDiffCallback,
    var factory: PhotoViewHolderFactory
) :
    ListAdapter<Models.FlowerResponse, PhotoViewHolder>(diffCallback) {

    val mClickPS: PublishSubject<PhotoViewHolderAction> = PublishSubject.create()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return factory.create(parent)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.vm.`object` = getItem(position)
        holder.bind()
        holder.itemOnClick(mClickPS , holder.vm.`object` )
    }

}