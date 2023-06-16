package com.ingenious.weatherapp.onBoarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.ingenious.weatherapp.activities.BaseActivity
import com.ingenious.weatherapp.controllers.DataStoreController
import com.ingenious.weatherapp.controllers.RealmController
import com.ingenious.weatherapp.activities.HomeActivity
import com.ingenious.weatherapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity() {

    lateinit var et_email: EditText
    lateinit var et_password: EditText
    lateinit var btn_login: Button
    lateinit var btn_signup: Button
    lateinit var btn_skip: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initViews()
        setupClicks()
    }

    private fun initViews() {
        et_email = findViewById(R.id.et_email)
        et_password = findViewById(R.id.et_password)
        btn_login = findViewById(R.id.btn_login)
        btn_signup = findViewById(R.id.btn_signup)
        btn_skip = findViewById(R.id.btn_skip)
    }

    private fun setupClicks() {
        btn_login.setOnClickListener { login() }
        btn_signup.setOnClickListener { redirectToSignUp() }
        btn_skip.setOnClickListener { redirectToHome() }
    }

    private fun login() {
        val id = et_email.text.toString()
        val password = et_password.text.toString()
        val userExist = RealmController.getUser(id, password)
        if (userExist != null) {
            redirectToHome()
            CoroutineScope(Dispatchers.IO).launch {
                val store = DataStoreController(this@LoginActivity)
                store.setLoggedIn(true)
            }
        }else
            showErrorAlertDialog("Email ID or Password is wrong.")
    }

    private fun redirectToSignUp() {
        startActivity(Intent(this, SignupActivity::class.java))
    }

    private fun redirectToHome() {
        finishAffinity()
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
}