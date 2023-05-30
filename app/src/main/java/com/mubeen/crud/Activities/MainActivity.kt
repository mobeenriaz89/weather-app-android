package com.mubeen.crud.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mubeen.crud.Controllers.DataStoreController
import com.mubeen.crud.Home.HomeActivity
import com.mubeen.crud.OnBoarding.LoginActivity
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
        finishAffinity()
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun redirectToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

}