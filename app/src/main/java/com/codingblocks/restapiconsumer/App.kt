package com.codingblocks.restapiconsumer

import android.app.Application
import com.codingblocks.restapiconsumer.api.JpApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by championswimmer on 14/01/18.
 */
class App: Application() {
    lateinit var jpApi: JpApi
    override fun onCreate() {
        super.onCreate()

         with (Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build()) {
             jpApi = create(JpApi::class.java)
         }
    }
    fun getApi () = jpApi
}