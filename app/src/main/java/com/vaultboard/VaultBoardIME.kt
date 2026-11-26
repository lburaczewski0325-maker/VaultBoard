package com.vaultboard

import android.inputmethodservice.InputMethodService
import android.speech.RecognizerIntent
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.content.Intent

class VaultBoardIME : InputMethodService() {
    private var preview: EditText? = null

    override fun onCreateInputView(): View {
        val view = layoutInflater.inflate(R.layout.keyboard, null)
        preview = view.findViewById(R.id.preview)

        view.findViewById<Button>(R.id.keyA).setOnClickListener { commit("a") }
        view.findViewById<Button>(R.id.keyB).setOnClickListener { commit("b") }
        view.findViewById<Button>(R.id.keyC).setOnClickListener { commit("c") }
        view.findViewById<Button>(R.id.keyBackspace).setOnClickListener {
            val ic = currentInputConnection
            ic?.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL))
        }
        view.findViewById<Button>(R.id.keyEnter).setOnClickListener { commit("\n") }
        view.findViewById<Button>(R.id.keyMic).setOnClickListener { startVoiceInput() }

        return view
    }

    private fun commit(text: String) {
        currentInputConnection?.commitText(text, 1)
    }

    private fun startVoiceInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
            putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
        }
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }
}
