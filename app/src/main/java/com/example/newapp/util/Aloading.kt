package com.example.newapp.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import com.example.newapp.R

class Aloading(context: Context):Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        val params: WindowManager.LayoutParams = window!!.attributes
        params.gravity = Gravity.CENTER
        window!!.setAttributes(params)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setTitle(null)
        setCancelable(false)
        setOnCancelListener(null)
        setContentView(R.layout.aloading)
    }

}