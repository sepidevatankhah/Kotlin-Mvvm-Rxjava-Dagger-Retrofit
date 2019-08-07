package de.joyn.myapplication.domain.entity

data class PhotoModel(
                       val query : String? = "flowers",
                       val pageSize: Int=20,
                       val pageNum: Int=1)