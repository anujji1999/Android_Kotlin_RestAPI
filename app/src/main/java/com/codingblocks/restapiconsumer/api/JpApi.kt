package com.codingblocks.restapiconsumer.api

import com.codingblocks.restapiconsumer.models.Album
import com.codingblocks.restapiconsumer.models.Post
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by championswimmer on 14/01/18.
 */
interface JpApi {
    @GET("posts")
    fun getPosts(): Call<Array<Post>>

    @GET("albums")
    fun getAlbums(): Call<Array<Album>>
}