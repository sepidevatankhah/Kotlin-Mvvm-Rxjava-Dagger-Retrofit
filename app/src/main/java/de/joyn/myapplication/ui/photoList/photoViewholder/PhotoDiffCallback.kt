package de.joyn.myapplication.ui.photoList.photoViewholder

import androidx.recyclerview.widget.DiffUtil
import de.joyn.myapplication.network.dto.Models
import javax.inject.Inject

class PhotoDiffCallback
@Inject constructor() : DiffUtil.ItemCallback<Models.PhotoResponse>() {
    override fun areItemsTheSame(oldItem: Models.PhotoResponse, newItem: Models.PhotoResponse): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Models.PhotoResponse, newItem: Models.PhotoResponse): Boolean {
        return false
    }
}