package de.joyn.myapplication.ui.photoList.fragment

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import de.joyn.myapplication.network.dto.Models

class PhotoAdapter (val onClickListener: OnClickListener) :
    ListAdapter<Models.FlowerResponse, PhotoAdapter.ViewHolder>(DiffCallback) {

    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    /**
     * The MarsPropertyViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [MarsProperty] information.
     */
    //class ViewHolder (private var binding: ListViewItemBinding):
    class ViewHolder (private var binding :View ):
        RecyclerView.ViewHolder(binding) {
        fun bind(flowers: Models.FlowerResponse) {
//            binding.property = flowers
//            // This is important, because it forces the data bindin g to execute immediately,
//            // which allows the RecyclerView to make the correct view size measurements
//            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [MarsProperty]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Models.FlowerResponse>() {
        override fun areItemsTheSame(oldItem: Models.FlowerResponse, newItem: Models.FlowerResponse): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Models.FlowerResponse, newItem: Models.FlowerResponse): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [MarsProperty]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [MarsProperty]
     */
    class OnClickListener(val clickListener: (flower:Models.FlowerResponse) -> Unit) {
        fun onClick(flower:Models.FlowerResponse) = clickListener(flower)
    }
}