package de.joyn.myapplication.network


import de.joyn.myapplication.network.dto.BaseModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    //@GET("api/?key=13173797-7e626eef5603a5f5f39b2369e")
    @GET("api/?")
    fun getPhotos(@Query("q") query: String? = "flowers"): Flowable<BaseModel>

    @GET("api/?")
    fun getPagedPhotos(
        @Query("q") query: String? = "flowers",
        @Query("page") pageNum: Int? = 1,
        @Query("per_page") pageSize: Int?
    ): Single<BaseModel>




    companion object {

        const val BASE_URL = "https://pixabay.com/"
        const val API_KEY = "13173797-7e626eef5603a5f5f39b2369e"


    }
}