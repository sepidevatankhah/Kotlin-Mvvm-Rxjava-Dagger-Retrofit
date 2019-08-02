package de.joyn.myapplication.ui.base.recyclerview

import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffUtilCallBack<T>
protected constructor(
    private val oldList: List<T>,
    private val newList: List<T>) : DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }
}