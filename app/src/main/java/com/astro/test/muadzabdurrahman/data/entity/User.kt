package com.astro.test.muadzabdurrahman.data.entity

data class User(
    val id : Int,
    val name: String,
    val avatarUrl: String,
    var favorite: Boolean = false
)
