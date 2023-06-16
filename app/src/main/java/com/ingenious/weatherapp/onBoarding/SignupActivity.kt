package com.ingenious.weatherapp.onBoarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.ingenious.weatherapp.activities.BaseActivity
import com.ingenious.weatherapp.controllers.DataStoreController
import com.ingenious.weatherapp.controllers.RealmController
import com.ingenious.weatherapp.activities.HomeActivity
import com.ingenious.weatherapp.models.User
import com.ingenious.weatherapp.R
import com.ingenious.weatherapp.utils.AppUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignupActivity : BaseActivity() {

    lateinit var et_email: EditText
    lateinit var et_password: EditText
    lateinit var btn_signup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        initViews()
        setupClicks()
    }

    private fun initViews() {
        et_email = findViewById(R.id.et_email)
        et_password = findViewById(R.id.et_password)
        btn_signup = findViewById(R.id.btn_signup)
    }

    private fun setupClicks() {
        btn_signup.setOnClickListener { signUp()}
    }

    private fun signUp() {
        var user = User()
        user.id = et_email.text.toString()
        user.password = et_password.text.toString()
        if(AppUtils.isValidEmail(user.id)) {
            RealmController.createUser(user)
            redirectToHome()
            CoroutineScope(Dispatchers.IO).launch {
                val store = DataStoreController(this@SignupActivity)
                store.setLoggedIn(true)
            }
        }else
            showErrorAlertDialog("Enter valid Email ID")
    }

    private fun redirectToHome() {
        finishAffinity()
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

}