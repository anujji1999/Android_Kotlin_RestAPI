package com.codingblocks.restapiconsumer.activities

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.codingblocks.restapiconsumer.App
import com.codingblocks.restapiconsumer.Album
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

class AlbumsActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ui = AlbumsActivityUI()
        ui.setContentView(this)

        (application as App).getApi().getAlbums().enqueue({
            t, r ->
            t?.let { warn("Could not fetch posts", t)}
            r?.body()?.let {
                info("Albums = " + it.size)
                ui.rvAlbums.apply {
                    layoutManager = LinearLayoutManager(this@AlbumsActivity)
                    adapter = GenericAdapter<Album>(
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

    class AlbumsActivityUI: AnkoComponent<AlbumsActivity> {
        lateinit var rvAlbums: RecyclerView
        override fun createView(ui: AnkoContext<AlbumsActivity>): View  = with(ui) {
            frameLayout {
                lparams(width = matchParent, height = matchParent)
                rvAlbums = recyclerView {
                }.lparams(width = matchParent, height = matchParent)
            }
        }

    }
}
