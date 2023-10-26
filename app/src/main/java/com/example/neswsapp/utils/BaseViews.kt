package com.example.neswsapp.utils

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog


object BaseViews {

    fun showDialogue(context: Context,positiveAction:()->Unit,negativeAction:()->Unit,onDismiss:()->Unit){
        var dialog = AlertDialog.Builder(context)
        dialog
            .setMessage("No Internet Connection")
            .setNeutralButton("Try again"
            ) { p0, p1 -> positiveAction() }
            .setNegativeButton("Cancel"){ p0, p1 -> negativeAction() }
            .setOnCancelListener { onDismiss() }
        var build = dialog.create()
        build.show()
    }
}