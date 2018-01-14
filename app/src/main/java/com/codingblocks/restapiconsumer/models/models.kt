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

