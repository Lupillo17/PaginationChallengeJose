package com.example.paginationchallenge.data.repository

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.paginationchallenge.data.remote.ApiResponse
import com.example.paginationchallenge.data.remote.Character
import com.example.paginationchallenge.domain.repository.MarvelApiClient
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.URLBuilder
import io.ktor.http.Url
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp
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
            val apikey = "23b2ad405e912c539345fc643fdc09dd"
            val privatekey = "c5cc4610932acbac762b6b03577dad17b6493505"
            val ts = Timestamp(System.currentTimeMillis()).time.toString()

            fun hash(): String {
                val input = "$ts$privatekey$apikey"
                val md = MessageDigest.getInstance("MD5")
                return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(31, '0')
            }

            val url =
                "https://gateway.marvel.com/v1/public/characters?apikey=$apikey&ts=$ts&hash=${hash()}&offset=10"
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

    override suspend fun findCharacter(id: Long): Character {
        val urlBuilder =
            URLBuilder(urlString = "https://gateway.marvel.com/v1/public/characters/$id")
        return client.get<ApiResponse>(url = Url(urlBuilder)).data.results.first()
    }
}
