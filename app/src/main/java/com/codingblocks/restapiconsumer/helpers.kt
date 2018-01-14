package com.codingblocks.restapiconsumer

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by championswimmer on 14/01/18.
 */

fun  <T> rfcb (cb: (t: Throwable?, r: Response<T>?) -> Unit): Callback<T> {
    return object: Callback<T> {
        override fun onFailure(call: Call<T>?, t: Throwable?) {
            cb(t, null)
        }

        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            cb(null, response)
        }

    }
}