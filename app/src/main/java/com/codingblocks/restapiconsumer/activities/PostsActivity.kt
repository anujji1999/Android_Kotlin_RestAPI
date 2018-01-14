package com.codingblocks.restapiconsumer.activities

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import com.codingblocks.restapiconsumer.App
import com.codingblocks.restapiconsumer.Post
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ui = PostsActivityUI()
        ui.setContentView(this)
        ui.rvPosts.backgroundColor = Color.RED

        (application as App).getApi().getPosts().enqueue(object: Callback<Array<Post>> {
            override fun onFailure(call: Call<Array<Post>>?, t: Throwable?) {
                warn("Posts not fetched", t)
            }

            override fun onResponse(call: Call<Array<Post>>?, response: Response<Array<Post>>?) {
                info("Posts = " + response?.body()?.size)
            }

        })
    }

    class PostsActivityUI: AnkoComponent<PostsActivity> {
        lateinit var rvPosts: RecyclerView
        override fun createView(ui: AnkoContext<PostsActivity>): View  = with(ui) {
            frameLayout {
                lparams(width = matchParent, height = matchParent)
                rvPosts = recyclerView {
                }.lparams(width = matchParent, height = matchParent)
            }
        }

    }
}
