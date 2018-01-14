package com.codingblocks.restapiconsumer.extensions

import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by championswimmer on 14/01/18.
 */
fun <T> Call<T>.enqueue (cb: (t: Throwable?, r: Response<T>?) -> Unit) {
    enqueue(object: Callback<T> {
        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            cb(null, response)
        }

        override fun onFailure(call: Call<T>?, t: Throwable?) {
            cb(t, null)
        }

    })
}

fun <T> Call<T>.execute(block: (T) -> Unit) {
    async(CommonPool) {
        execute()?.body()?.let {
            withContext(UI) { block(it) }
        }
    }
}