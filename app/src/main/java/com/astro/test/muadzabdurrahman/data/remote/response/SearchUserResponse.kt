package com.astro.test.muadzabdurrahman.data.remote.response


import com.google.gson.annotations.SerializedName

data class SearchUserResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val items: List<User>?,
    @SerializedName("total_count")
    val totalCount: Int?
)