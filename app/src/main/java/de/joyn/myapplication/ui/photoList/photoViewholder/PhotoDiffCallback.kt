package de.joyn.myapplication.ui.photoList.photoViewholder

import androidx.recyclerview.widget.DiffUtil
import de.joyn.myapplication.network.dto.Models
import javax.inject.Inject

class PhotoDiffCallback
@Inject constructor() : DiffUtil.ItemCallback<Models.FlowerResponse>() {
    override fun areItemsTheSame(oldItem: Models.FlowerResponse, newItem: Models.FlowerResponse): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Models.FlowerResponse, newItem: Models.FlowerResponse): Boolean {
        return false
    }
}