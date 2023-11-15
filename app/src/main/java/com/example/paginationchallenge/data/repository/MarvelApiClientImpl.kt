package com.example.paginationchallenge.data.repository

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.paginationchallenge.core.utils.Constants.ALL_CHARACTERS_ENDPOINT
import com.example.paginationchallenge.core.utils.Constants.BASE_URL
import com.example.paginationchallenge.core.utils.Constants.ONE_CHARACTER_ENDPOINT
import com.example.paginationchallenge.core.utils.urlProperties
import com.example.paginationchallenge.data.remote.ApiResponse
import com.example.paginationchallenge.data.remote.Character
import com.example.paginationchallenge.domain.repository.MarvelApiClient
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.URLBuilder
import io.ktor.http.Url
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

internal class MarvelApiClientImpl(
    private val client: HttpClient,
    private val context: Context
) : MarvelApiClient {

    override suspend fun getCharacters(offset: Long, limit: Long): ApiResponse {
        val urlBuilder =
            URLBuilder(urlString = "https://gateway.marvel.com/v1/public/characters?offset=$offset&limit=$limit")
        return client.get(url = Url(urlBuilder))
    }

    override suspend fun getCharacters2(offset: Long, limit: Long): ApiResponse? =
        suspendCoroutine { apiResponse ->
            val queue = Volley.newRequestQueue(context)

            val url =
                BASE_URL + ALL_CHARACTERS_ENDPOINT + urlProperties() + "&limit=$limit&offset=$offset"

            val request = StringRequest(
                Request.Method.GET, url, { response ->
                    try {
                        val data =
                            Gson().fromJson(response.toString(), ApiResponse::class.java)
                        apiResponse.resume(data)
                    } catch (e: Exception) {
                        Log.e("api error1", e.message.toString())
                        apiResponse.resume(null)
                    }
                }, { error ->
                    Log.e("api error2", error.message.toString())
                    apiResponse.resume(null)
                }
            )
            queue.add(request)
        }

    override suspend fun findCharacter(id: Long): ApiResponse? =
//        val urlBuilder =
//            URLBuilder(urlString = "https://gateway.marvel.com/v1/public/characters/$id")
//        return client.get<ApiResponse>(url = Url(urlBuilder)).data.results.first()
        suspendCoroutine { apiResponse ->
            val queue = Volley.newRequestQueue(context)

            val url =
                BASE_URL + ONE_CHARACTER_ENDPOINT + "$id" + urlProperties()

            val request = StringRequest(
                Request.Method.GET, url, { response ->
                    try {
                        val data =
                            Gson().fromJson(response.toString(), ApiResponse::class.java)
                        Log.e("Character", data.toString())
                        apiResponse.resume(data)
                    } catch (e: Exception) {
                        Log.e("Character error1", e.message.toString())
                        apiResponse.resume(null)
                    }
                }, { error ->
                    Log.e("Character error2", error.message.toString())
                    apiResponse.resume(null)
                }
            )
            queue.add(request)
        }
}
