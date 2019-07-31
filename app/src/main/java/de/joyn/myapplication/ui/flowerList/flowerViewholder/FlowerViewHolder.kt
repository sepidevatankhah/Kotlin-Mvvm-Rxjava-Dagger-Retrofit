package de.joyn.myapplication.ui.flowerList.flowerViewholder

import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import de.joyn.myapplication.R
import de.joyn.myapplication.network.dto.Models
import de.joyn.myapplication.ui.base.recyclerview.BaseViewHolder
import io.reactivex.subjects.PublishSubject

class FlowerViewHolder (itemView : View , viewModel : FlowerViewModel) :
BaseViewHolder<FlowerViewHolderAction , Models.FlowerResponse , FlowerViewModel>(itemView , viewModel){
    override fun bind() {

    }

    override fun itemOnClick(actionSubject: PublishSubject<FlowerViewHolderAction>) {
        RxView.clicks(itemView.findViewById(R.id.flower_container))
            .map { o -> FlowerViewHolderAction.createSelectAction(adapterPosition) }
            .repeat()
            .subscribe(actionSubject)
    }
}