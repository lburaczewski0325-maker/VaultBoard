package com.vaultboard.crypto

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

/** AES-256-GCM authenticated encryption with a fresh nonce for every message. */
object MessageCrypto {
    private const val TAG_BITS = 128

    fun encrypt(message: String, sharedSecret: ByteArray): String {
        val key = deriveKey(sharedSecret)
        val nonce = ByteArray(12).also { java.security.SecureRandom().nextBytes(it) }
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, SecretKeySpec(key, "AES"), GCMParameterSpec(TAG_BITS, nonce))
        val encrypted = cipher.doFinal(message.toByteArray(Charsets.UTF_8))
        val payload = nonce + encrypted
        return "VB1:" + Base64.encodeToString(payload, Base64.NO_WRAP)
    }

    fun decrypt(payload: String, sharedSecret: ByteArray): String {
        require(payload.startsWith("VB1:")) { "Not a VaultBoard encrypted message" }
        val bytes = Base64.decode(payload.removePrefix("VB1:"), Base64.NO_WRAP)
        require(bytes.size >= 12 + 16)
        val nonce = bytes.copyOfRange(0, 12)
        val encrypted = bytes.copyOfRange(12, bytes.size)
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.DECRYPT_MODE, SecretKeySpec(deriveKey(sharedSecret), "AES"), GCMParameterSpec(TAG_BITS, nonce))
        return String(cipher.doFinal(encrypted), Charsets.UTF_8)
    }

    private fun deriveKey(secret: ByteArray): ByteArray {
        val digest = java.security.MessageDigest.getInstance("SHA-256")
        return digest.digest(secret)
    }
}
