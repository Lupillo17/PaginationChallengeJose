package com.example.paginationchallenge.data.remote

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

private const val MD5 = "MD5"

fun generateHash(time: Long, privateKey: String, publicKey: String): String =
    md5(time.toString() + privateKey + publicKey)

private fun md5(stringToHash: String): String {
    return try {
        val digest = MessageDigest.getInstance(MD5)
        digest.update(stringToHash.toByteArray())
        val messageDigest = digest.digest()
        val hexString = StringBuilder()
        for (aMessageDigest in messageDigest) {
            var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
            while (h.length < 2) {
                h = "0$h"
            }
            hexString.append(h)
        }
        hexString.toString()
    } catch (e: NoSuchAlgorithmException) {
        ""
    }
}
