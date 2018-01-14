package com.codingblocks.restapiconsumer.models

data class Post(
        val id: Int? = null,
        val title: String? = null,
        val body: String? = null,
        val userId: Int? = null
)

data class Album(
        val id: Int? = null,
        val title: String? = null,
        val userId: Int? = null
)


data class Todo(
		val userId: Int? = null,
		val id: Int? = null,
		val title: String? = null,
		val completed: Boolean? = null
)
