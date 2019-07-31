package de.joyn.myapplication.ui.photoList.flowerViewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import de.joyn.myapplication.R
import de.joyn.myapplication.ui.base.recyclerview.ViewHolderFactory
import javax.inject.Inject

class FlowerViewHolderFactory @Inject constructor() : ViewHolderFactory<FlowerViewHolder> {
    override fun create(parent: ViewGroup): FlowerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_flower, parent, false)
        return FlowerViewHolder(view, FlowerViewModel())
    }
}