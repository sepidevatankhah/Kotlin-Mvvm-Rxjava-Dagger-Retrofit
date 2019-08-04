package de.joyn.myapplication.domain.entity

data class PhotoModel(
    val query: String? = "flowers",
    val id: String? = "",
    val userName: String? = "",
    val pageSize: Int? = 20,
    val pageNum: Int? = 1
)