package com.vaultboard

import android.inputmethodservice.InputMethodService
import android.view.View

class VaultBoardIME : InputMethodService() {
    override fun onCreateInputView(): View = layoutInflater.inflate(R.layout.keyboard, null)
}
