package de.joyn.myapplication.ui.base.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.subjects.PublishSubject

abstract class BaseViewHolder<VHAction, Model, ViewModel:BaseViewHolderViewModel<Model>>
constructor(itemView: View, val vm: ViewModel)
    : RecyclerView.ViewHolder(itemView) {
    abstract fun bind()

    abstract fun itemOnClick(actionSubject: PublishSubject<VHAction>)
}