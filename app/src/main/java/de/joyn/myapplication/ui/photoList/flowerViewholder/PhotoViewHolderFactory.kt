package de.joyn.myapplication.ui.photoList.flowerViewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import de.joyn.myapplication.R
import de.joyn.myapplication.ui.base.recyclerview.ViewHolderFactory
import javax.inject.Inject

class PhotoViewHolderFactory @Inject constructor() : ViewHolderFactory<PhotoViewHolder> {
    override fun create(parent: ViewGroup): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(view, PhotoViewModel())
    }
}