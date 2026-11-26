package com.vaultboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class KeyVaultActivity : AppCompatActivity() {
    override fun onCreate(saved: Bundle?) {
        super.onCreate(saved)
        setTitle("Key Vault")
        val view = android.widget.TextView(this).apply {
            text = "No contact keys yet. Share a public key to establish an encrypted conversation."
            setPadding(48, 48, 48, 48)
            textSize = 16f
        }
        setContentView(view)
    }
}
