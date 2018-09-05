package com.noblemajesty.marvel.utils

import java.security.MessageDigest

object Utils {
    private val instance = "MD5"

    // referenced from https://www.samclarke.com/kotlin-hash-strings/
    fun hashWithAlgorithm(algorithm: String = instance, stringToBeHashed: String): String {
        val hexCharacters = "0123456789abcdef"
        val bytes = MessageDigest
                .getInstance(algorithm)
                .digest(stringToBeHashed.toByteArray())
        val result = StringBuilder(bytes.size * 2)

        bytes.forEach {
            val i = it.toInt()
            result.append(hexCharacters[i shr 4 and 0x0f])
            result.append(hexCharacters[i and 0x0f])
        }

        return result.toString()
    }
}