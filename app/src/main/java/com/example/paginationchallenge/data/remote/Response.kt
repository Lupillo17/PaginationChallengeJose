package com.example.paginationchallenge.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val code: Int = 0,
    val status: String = "",
    val copyright: String = "",
    val attributionText: String = "",
    val attributionHTML: String = "",
    val etag: String = "",
    val data: Data
)

@Serializable
data class Data(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Character>
)

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Thumbnail,
    val resourceURI: String,
    val comics: Comics,
    val series: Comics,
    val stories: Stories,
    val events: Comics,
    val urls: List<URL>
)

@Serializable
data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<ComicsItem>,
    val returned: Int
)

@Serializable
data class ComicsItem(
    val resourceURI: String,
    val name: String
)

@Serializable
data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<StoriesItem>,
    val returned: Int
)

@Serializable
data class StoriesItem(
    val resourceURI: String,
    val name: String,
    val type: String
)

@Serializable
data class Thumbnail(
    val path: String,
    val extension: String
)

fun Thumbnail.asString() = "$path.$extension".replace("http", "https")

@Serializable
data class URL(
    val type: String,
    val url: String
)
