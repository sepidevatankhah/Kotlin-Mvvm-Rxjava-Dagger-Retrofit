package de.joyn.myapplication.network

import android.content.Context
import de.joyn.myapplication.di.scope.ForApplication
import de.joyn.myapplication.domain.repository.ConnectivityManager
import de.joyn.myapplication.network.dto.BaseModel
import de.joyn.myapplication.network.dto.Models
import io.reactivex.Flowable
import io.reactivex.Single
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface RestApiService {

    @GET("api/?key=13173797-7e626eef5603a5f5f39b2369e")
    //@GET("api/?")
    fun getPhotos(@Query("q") query: String? = "flowers"): Flowable<BaseModel>

    @GET("api/?key=13173797-7e626eef5603a5f5f39b2369e")
    //@GET("api/?")
    fun getPhoto(@Query("q") query: String? = "flowers"): Single<BaseModel>

   // @GET("api/?")
    @GET("api/?key=13173797-7e626eef5603a5f5f39b2369e")
    fun getPagedPhotos(
        @Query("q") query: String? = "",
        //@Query("page") pageNum: Int?
        @Query("per_page") pageSize: Int?
    ): Single<BaseModel>


    companion object {

        const val BASE_URL = "https://pixabay.com/"
        const val API_KEY = "13173797-7e626eef5603a5f5f39b2369e"

        fun getService(): RestApiService {

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                //.client(provideOkHttpClient())
                .build()
            return retrofit.create(RestApiService::class.java)
        }

        private fun provideOkHttpClient(
        ): OkHttpClient {
            val cacheSize = (5 * 1024 * 1024).toLong()

            //val myCache = Cache(context.cacheDir, cacheSize)


            val builder = OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                // Specify the cache we created earlier.
                //.cache(myCache)
                // Add an Interceptor to the OkHttpClient.
                .addInterceptor { chain ->

                    // Get the request from the chain.
                    var request = chain.request()
                    val url = request.url().newBuilder()
                        .addQueryParameter("key", API_KEY)
                        .build()
                    request = request.newBuilder()
                        .url(url)
                        .build()


                    // End of if-else statement

                    // Add the modified request to the chain.
                    chain.proceed(request)
                }


            return builder.build()
        }
    }
}