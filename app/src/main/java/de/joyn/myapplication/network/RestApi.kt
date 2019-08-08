package de.joyn.myapplication.network

import de.joyn.myapplication.network.dto.BaseModel
import de.joyn.myapplication.network.dto.Models
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    companion object {
        const val BASE_URL = "https://pixabay.com/"
        const val API_KEY = "13173797-7e626eef5603a5f5f39b2369e"
    }


    //@GET("api/?key=13173797-7e626eef5603a5f5f39b2369e")
    @GET("api/?")
    fun getPhotos(@Query("q") query:String? = "",
                  @Query("per_page") pageSize:Int? = 20,
                  @Query("page") pageNum :Int? ): Single<Models.BasePhoto>

}