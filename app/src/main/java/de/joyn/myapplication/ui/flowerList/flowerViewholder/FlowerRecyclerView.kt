package de.joyn.myapplication.ui.flowerList.flowerViewholder

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.annotation.NonNull
import de.joyn.myapplication.network.dto.Models
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class FlowerRecyclerView @Inject constructor(
    @NonNull var diffCallback: FlowerDiffCallback,
    var factory: FlowerViewHolderFactory
) :
    ListAdapter<Models.FlowerResponse, FlowerViewHolder>(diffCallback) {

    val mClickPS: PublishSubject<FlowerViewHolderAction> = PublishSubject.create()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {
        return factory.create(parent)
    }

    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        holder.vm.`object` = getItem(position)
        holder.bind()
        holder.itemOnClick(mClickPS)
    }

}