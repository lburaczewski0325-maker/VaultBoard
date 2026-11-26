package com.vaultboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(saved: Bundle?) {
        super.onCreate(saved)
        setContentView(R.layout.activity_settings)

        findViewById<MaterialButton>(R.id.enableKeyboard).setOnClickListener {
            startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS))
        }

        findViewById<MaterialButton>(R.id.openKeyboardPicker).setOnClickListener {
            (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
                .showInputMethodPicker()
        }

        findViewById<MaterialButton>(R.id.openVault).setOnClickListener {
            startActivity(Intent(this, KeyVaultActivity::class.java))
        }

        findViewById<MaterialButton>(R.id.downloadApk).setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, android.net.Uri.parse(
                "https://github.com/lburaczewski0325-maker/VaultBoard/releases/latest"
            )))
        }
    }

    override fun onResume() {
        super.onResume()
        val enabled = Settings.Secure.getString(contentResolver, Settings.Secure.ENABLED_INPUT_METHODS)
            ?.split(':')?.any { it.startsWith("com.vaultboard/") } == true
        findViewById<TextView>(R.id.status).text = if (enabled)
            "VaultBoard is enabled. Choose it as your active keyboard."
        else "Enable VaultBoard in Android Settings to begin."
    }
}
