package de.joyn.myapplication.network

import de.joyn.myapplication.network.dto.BaseModel
import de.joyn.myapplication.network.dto.Models
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    companion object {
        val BASE_URL = "https://pixabay.com/"
        val API_KEY ="13173797-7e626eef5603a5f5f39b2369e"

    }

    //@GET("api/?key=13173797-7e626eef5603a5f5f39b2369e&q=yellow+flowers&image_type=photo&pretty=true")

    @GET("api/?key=13173797-7e626eef5603a5f5f39b2369e")
    fun getFlowers(@Query("q") query:String? = "flowers"): Single<BaseModel<Models.FlowerResponse>>

}