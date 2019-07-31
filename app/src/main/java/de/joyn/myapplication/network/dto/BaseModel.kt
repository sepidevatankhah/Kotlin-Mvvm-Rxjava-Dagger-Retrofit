package de.joyn.myapplication.network.dto

import com.google.gson.annotations.SerializedName

class BaseModel<R>(@SerializedName("meta") val meta: Meta,
                   @SerializedName("response") val response: R)


class Meta(@SerializedName("code") val code:Int)