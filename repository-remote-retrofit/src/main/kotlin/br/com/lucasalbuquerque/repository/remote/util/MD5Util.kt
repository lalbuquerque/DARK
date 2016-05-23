package br.com.lucasalbuquerque.repository.remote.util

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.inject.Singleton

@Singleton
class MD5Util {

    fun hash(publicApiKey: String, privateApiKey: String, timestamp: String): String? {
        try {
            val md = MessageDigest.getInstance("MD5")
            val toHash = timestamp + privateApiKey + publicApiKey
            return BigInteger(1, md.digest(toHash.toByteArray())).toString(16)
        } catch (e: NoSuchAlgorithmException) {
        }

        return null
    }
}