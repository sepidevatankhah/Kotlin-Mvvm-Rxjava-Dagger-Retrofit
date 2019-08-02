package de.joyn.myapplication.di.app

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import de.joyn.myapplication.BuildConfig
import de.joyn.myapplication.data.repository.RepositoryImp
import de.joyn.myapplication.di.scope.ForApplication
import de.joyn.myapplication.domain.repository.ConnectivityManager
import de.joyn.myapplication.domain.repository.Repository
import de.joyn.myapplication.network.RestApi
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideRepository(api: RestApi): Repository {
        return RepositoryImp(api)
    }

    @Singleton
    @Provides
    fun provideForSquareApi(retrofit: Retrofit): RestApi =
        retrofit.create(RestApi::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(RestApi.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()


    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()


    @Singleton
    @Provides
    fun provideOkHttpClient(
        connectivityManager: ConnectivityManager,
        @ForApplication context: Context
    ): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()

        val myCache = Cache(context.cacheDir, cacheSize)


        val builder = OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            // Specify the cache we created earlier.
            .cache(myCache)
            // Add an Interceptor to the OkHttpClient.
            .addInterceptor { chain ->

                // Get the request from the chain.
                var request = chain.request()
                val url = request.url().newBuilder()
                    .addQueryParameter("key", RestApi.API_KEY)
                    .build()
                request = request.newBuilder()
                    .url(url)
                    .build()

                /*
                *  Leveraging the advantage of using Kotlin,
                *  we initialize the request and change its header depending on whether
                *  the device is connected to Internet or not.
                */
                request = if (connectivityManager.hasNetwork()!!)
                /*
                *  If there is Internet, get the cache that was stored 5 seconds ago.
                *  If the cache is older than 5 seconds, then discard it,
                *  and indicate an error in fetching the response.
                *  The 'max-age' attribute is responsible for this behavior.
                */
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                /*
                *  If there is no Internet, get the cache that was stored 7 days ago.
                *  If the cache is older than 7 days, then discard it,
                *  and indicate an error in fetching the response.
                *  The 'max-stale' attribute is responsible for this behavior.
                *  The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
                */
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                // End of if-else statement

                // Add the modified request to the chain.
                chain.proceed(request)
            }


        return builder.build()
    }


}