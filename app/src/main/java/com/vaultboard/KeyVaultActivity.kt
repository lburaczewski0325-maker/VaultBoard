package com.vaultboard

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.LinearLayout
import android.widget.TextView

class KeyVaultActivity : AppCompatActivity() {
    override fun onCreate(saved: Bundle?) {
        super.onCreate(saved)
        setTitle("Key Vault")

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 48, 48, 48)
        }

        val vault = KeyVault(this)
        val contacts = vault.contacts()
        layout.addView(TextView(this).apply {
            text = if (contacts.isEmpty())
                "No shared keys yet.\n\nEstablishing a connection shares only public keys; private keys remain on-device."
            else "Shared contact keys:\n" + contacts.sorted().joinToString("\n")
            textSize = 16f
        })
        setContentView(layout)
    }
}
