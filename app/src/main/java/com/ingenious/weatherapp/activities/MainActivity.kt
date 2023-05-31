package com.ingenious.weatherapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ingenious.weatherapp.controllers.DataStoreController
import com.ingenious.weatherapp.home.HomeActivity
import com.ingenious.weatherapp.onBoarding.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isLoggedIn()
    }

    private fun isLoggedIn() {
        val store = DataStoreController(this)
        CoroutineScope(Dispatchers.IO).launch{
            val isLoggedIn = store.isLoggedIn.first()
            if(isLoggedIn)
                redirectToHome()
            else
                redirectToLogin()
        }
    }

    private fun redirectToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finishAffinity()
    }

    private fun redirectToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

}