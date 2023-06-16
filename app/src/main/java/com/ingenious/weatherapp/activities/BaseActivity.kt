package com.ingenious.weatherapp.activities

import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

open class BaseActivity : AppCompatActivity() {

    fun showErrorAlertDialog(msg: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(msg)
        builder.show()
    }

}