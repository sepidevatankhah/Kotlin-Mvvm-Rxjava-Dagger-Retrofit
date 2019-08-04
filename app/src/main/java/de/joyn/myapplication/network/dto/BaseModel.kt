package de.joyn.myapplication.network.dto

import com.google.gson.annotations.SerializedName

class BaseModel<R>(@SerializedName("totalHits") val totalHits: Int,
                   @SerializedName("hits") val response: List<R>,
                   @SerializedName("total") val total: Int)

