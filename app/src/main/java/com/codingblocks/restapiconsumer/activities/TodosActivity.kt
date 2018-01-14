package com.codingblocks.restapiconsumer.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.codingblocks.restapiconsumer.App
import com.codingblocks.restapiconsumer.adapters.GenericAnkoAdapter
import com.codingblocks.restapiconsumer.extensions.enqueue
import com.codingblocks.restapiconsumer.extensions.execute
import com.codingblocks.restapiconsumer.models.Todo
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class TodosActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ui = TodosActivityUI()
        ui.setContentView(this)

        (application as App).getApi().getTodos().execute {
            info("Todos = " + it.size)
            ui.rvTodos.apply {
                layoutManager = LinearLayoutManager(this@TodosActivity)
                adapter = GenericAnkoAdapter<Todo, TodosListItemUI> (
                        it,
                        { TodosListItemUI() },
                        { todo, ankoComponent ->
                            ankoComponent.apply { with(todo) {
                                tvTitle.text = title
                                cbDone.isChecked = completed ?: false
                            } }
                        }
                )
            }
        }

    }

    class TodosActivityUI: AnkoComponent<TodosActivity> {
        lateinit var rvTodos: RecyclerView
        override fun createView(ui: AnkoContext<TodosActivity>): View = with(ui) {
            frameLayout {
                lparams(matchParent, matchParent)
                rvTodos = recyclerView {
                    lparams(matchParent, matchParent)
                }
            }
        }

    }
    class TodosListItemUI: AnkoComponent<ViewGroup> {
        lateinit var tvTitle: TextView
        lateinit var cbDone: CheckBox

        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
            linearLayout {
                lparams(matchParent, wrapContent)
                padding = dip(10)
                tvTitle = textView("sample todo").lparams(0, wrapContent, 4F)
                cbDone = checkBox().lparams(0, wrapContent, 1F)

            }
        }

    }
}
