package com.example.paginationchallenge.data.remote

import com.example.paginationchallenge.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import kotlinx.serialization.json.Json
import java.util.Date

private const val TIME_OUT = 60_000
private const val API_KEY = "apikey"
private const val TS = "ts"
private const val HASH = "hash"

internal val ktorHttpClient = HttpClient(Android) {

    install(JsonFeature) {
        serializer = KotlinxSerializer(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
        engine {
            connectTimeout = TIME_OUT
            socketTimeout = TIME_OUT
        }
    }
    val date = Date().time
    val hash = generateHash(
        time = date,
        privateKey = BuildConfig.PRIVATE_KEY,
        publicKey = BuildConfig.PUBLIC_KEY
    )

    install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
        parameter(API_KEY, BuildConfig.PUBLIC_KEY)
        parameter(TS, date.toString())
        parameter(HASH, hash)
    }
}
