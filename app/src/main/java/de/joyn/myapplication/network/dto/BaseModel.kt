package de.joyn.myapplication.network.dto

import androidx.paging.PagedList
import com.google.gson.annotations.SerializedName

class BaseModel(@SerializedName("totalHits") val totalHits: Int,
                   @SerializedName("hits") val response: ArrayList<Models.PhotoResponse>,
                   @SerializedName("total") val total: Long)

