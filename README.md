# VaultBoard

VaultBoard is a native Android keyboard for Android 8.0+.

## Included
- Native `InputMethodService` keyboard scaffold
- Android SpeechRecognizer entry point
- AES-256-GCM message encryption primitive
- AndroidX EncryptedSharedPreferences key vault
- Light/dark Material theme support
- Settings, keyboard picker, vault, and APK release link

## Security model
Public keys identify contacts. Private keys remain in Android-keystore-backed encrypted storage. The key vault stores contact public keys and never exports private keys.

## Build
Open the project in Android Studio, select `app`, and choose **Build > Build APK(s)**.
