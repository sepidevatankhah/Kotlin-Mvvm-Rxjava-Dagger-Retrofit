package de.joyn.myapplication.network.dto

import com.google.gson.annotations.SerializedName

sealed class Models {

    data class PhotoResponse(@SerializedName("id") val id: Long,
                             @SerializedName("user") val userName: String,
                             @SerializedName("largeImageURL") val largeImageUrl: String,
                             @SerializedName("webformatURL") val webFormatUrl: String,
                             @SerializedName("previewURL") val previewImageUrl: String,
                             @SerializedName("userImageURL") val userImageUrl: String,
                             @SerializedName("views") val viewNumber: String,
                             @SerializedName("likes") val likeNumber: String,
                             @SerializedName("tags") val tags: String,
                             @SerializedName("downloads") val downloadNumber: String,
                             @SerializedName("page") val pageNum: Int)
}