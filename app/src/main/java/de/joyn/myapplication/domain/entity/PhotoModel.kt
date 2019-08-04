package de.joyn.myapplication.domain.entity

data class PhotoModel(
                       val query : String? = "flowers",
                       val id: String?="",
                       val userName: String?="",
                       val viewNumber: String?="",
                       val likeNumber: String?="",
                       val downloadNumber: String?="")