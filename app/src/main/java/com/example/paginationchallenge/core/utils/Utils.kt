package com.example.paginationchallenge.core.utils

import com.example.paginationchallenge.BuildConfig
import com.example.paginationchallenge.core.utils.Constants.PRIVATE_KEY
import com.example.paginationchallenge.core.utils.Constants.PUBLIC_KEY
import java.io.FileInputStream
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp
import java.util.Properties

fun getHash(publicKey:String, privateKey: String, ts:String): String {
    val input = "$ts$privateKey$publicKey"
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(31, '0')
}

fun urlProperties():String{
    val publicKey = BuildConfig.PUBLIC_KEY
    val privateKey = BuildConfig.PRIVATE_KEY
    val ts = Timestamp(System.currentTimeMillis()).time.toString()
    val hash = getHash(publicKey, privateKey, ts)

    return "?apikey=$publicKey&ts=$ts&hash=$hash"

}
