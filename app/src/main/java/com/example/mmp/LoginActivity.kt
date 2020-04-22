package com.example.mmp

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.AuthUI.IdpConfig.EmailBuilder
import com.firebase.ui.auth.AuthUI.IdpConfig.GoogleBuilder
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    // Request code for sign in
    private val RC_SIGN_IN = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation =  (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            // Already signed in
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        } else {
            // Not signed in. Start the login flow.
            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(
                        listOf(
                            GoogleBuilder().build()
                        )
                    )
                    .setTheme(R.style.AppTheme)
                    .setIsSmartLockEnabled(false)
                    .build(),
                RC_SIGN_IN
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // RC_SIGN_IN is the request code you passed when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
                return
            } else {
                // Sign in failed
                when {
                    response == null -> {
                        // User pressed back button
                        Log.e("Login", "Login canceled by User")
                    }
                    response.error!!.errorCode == ErrorCodes.NO_NETWORK -> {
                        Log.e("Login", "No Internet Connection")
                    }
                    response.error!!.errorCode == ErrorCodes.UNKNOWN_ERROR -> {
                        Log.e("Login", "Unknown Error")
                    }
                }
            }
            Log.e("Login", "Unknown sign in response")
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
    }
}