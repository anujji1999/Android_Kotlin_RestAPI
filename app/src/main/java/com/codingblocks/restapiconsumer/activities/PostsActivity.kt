package com.codingblocks.restapiconsumer.activities

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.codingblocks.restapiconsumer.App
import com.codingblocks.restapiconsumer.Post
import com.codingblocks.restapiconsumer.R
import com.codingblocks.restapiconsumer.adapters.GenericAdapter
import com.codingblocks.restapiconsumer.extensions.enqueue
import com.codingblocks.restapiconsumer.rfcb
import kotlinx.android.synthetic.main.list_item_post.view.*
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

        (application as App).getApi().getPosts().enqueue({
            t, r ->
            t?.let { warn("Could not fetch posts", t)}
            r?.body()?.let {
                info("Posts = " + it.size)
                ui.rvPosts.apply {
                    layoutManager = LinearLayoutManager(this@PostsActivity)
                    adapter = GenericAdapter<Post>(
                            it,
                            R.layout.list_item_post,
                            {
                                item, itemView ->
                                itemView.tvTitle.text = item.title
                            }

                    )
                }
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
