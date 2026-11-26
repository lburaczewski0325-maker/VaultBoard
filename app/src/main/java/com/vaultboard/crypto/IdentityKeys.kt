package com.vaultboard.crypto

import java.security.KeyPair
import java.security.KeyPairGenerator

/** Generates an Ed25519-style key identity placeholder using Android's standard key providers. */
object IdentityKeys {
    fun generate(): KeyPair {
        val generator = KeyPairGenerator.getInstance("EC")
        generator.initialize(256)
        return generator.generateKeyPair()
    }
}
