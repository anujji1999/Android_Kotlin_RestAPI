package com.codingblocks.restapiconsumer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.codingblocks.restapiconsumer.activities.AlbumsActivity
import com.codingblocks.restapiconsumer.activities.PostsActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainAcivityUI().setContentView(this)
    }

    class MainAcivityUI: AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
            verticalLayout {
                lparams(width = matchParent, height = matchParent)

                linearLayout {
                    lparams(width = matchParent, height = 0, weight = 1F)
                    button("POSTS").lparams(
                            height = matchParent,
                            width = 0,
                            weight = 1F
                    ).onClick { startActivity<PostsActivity>() }

                    button("ALBUMS")
                            .lparams(
                                    height = matchParent,
                                    width = 0,
                                    weight = 1F
                            ).onClick { startActivity<AlbumsActivity>() }
                }
                linearLayout {
                    lparams(width = matchParent, height = 0, weight = 1F)
                    button("USERS")
                            .lparams(
                                    height = matchParent,
                                    width = 0,
                                    weight = 1F
                            )
                    button("TODOS")
                            .lparams(
                                    height = matchParent,
                                    width = 0,
                                    weight = 1F
                            )
                }
            }
        }

    }

}
