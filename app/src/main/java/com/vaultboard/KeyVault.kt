package com.vaultboard

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class KeyVault(context: Context) {
    private val prefs: SharedPreferences

    init {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        prefs = EncryptedSharedPreferences.create(
            context,
            "vaultboard_key_vault",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun contacts(): Set<String> = prefs.all.keys.filter { it.startsWith("key:") }
        .map { it.removePrefix("key:") }.toSet()

    fun put(contactId: String, publicKeyBase64: String) {
        prefs.edit().putString("key:$contactId", publicKeyBase64).apply()
    }

    fun get(contactId: String): String? = prefs.getString("key:$contactId", null)

    fun remove(contactId: String) {
        prefs.edit().remove("key:$contactId").apply()
    }
}
