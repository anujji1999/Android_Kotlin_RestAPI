package com.codingblocks.restapiconsumer.api

import com.codingblocks.restapiconsumer.Post
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by championswimmer on 14/01/18.
 */
interface JpApi {
    @GET("posts")
    fun getPosts(): Call<Array<Post>>
}