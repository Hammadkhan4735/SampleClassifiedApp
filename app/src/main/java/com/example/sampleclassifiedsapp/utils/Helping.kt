package com.example.sampleclassifiedsapp.utils


import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.util.Base64
import android.util.Log
import android.util.Patterns
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.example.sampleclassifiedsapp.R
import okhttp3.ResponseBody
import org.json.JSONObject
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.math.BigInteger
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.security.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


class Helping {
    
    companion object {

        fun showRespectiveErrorMessages(
            cntext: Context,
            code: Int) {
            if (code == 500) {
                Toast.makeText(cntext, "Internal server error", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(cntext, "Unable to communicate with server", Toast.LENGTH_SHORT).show()
            }
        }

        fun setProgressDialog(context: Context, message: String): AlertDialog {
            val llPadding = 30
            val ll = LinearLayout(context)
            ll.orientation = LinearLayout.HORIZONTAL
            ll.setPadding(llPadding, llPadding, llPadding, llPadding)
            ll.setBackgroundColor(context.resources.getColor(R.color.colorWhite))
            ll.gravity = Gravity.CENTER
            var llParam = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            llParam.gravity = Gravity.CENTER
            ll.layoutParams = llParam

            val progressBar = ProgressBar(context)
            progressBar.isIndeterminate = true
            progressBar.setPadding(0, 0, llPadding, 0)
            progressBar.layoutParams = llParam

            llParam = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            llParam.gravity = Gravity.CENTER
            val tvText = TextView(context)
            tvText.text = message
            tvText.setTextColor(Color.parseColor("#000000"))
            tvText.textSize = 16.toFloat()
            tvText.layoutParams = llParam

            ll.addView(progressBar)
            ll.addView(tvText)

            val builder = AlertDialog.Builder(context)
            builder.setCancelable(false)
            builder.setView(ll)

            val dialog = builder.create()
            val window = dialog.window
            if (window != null) {
                val layoutParams = WindowManager.LayoutParams()
                layoutParams.copyFrom(dialog.window?.attributes)
                layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
                layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
                dialog.window?.attributes = layoutParams
            }
            return dialog
        }


        fun convertStringDateToRequiredFormat12Hour(strDate: String): String {
            var mdate = ""
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            format.timeZone = TimeZone.getTimeZone("UTC")
            val format2 = SimpleDateFormat("dd/MM/yy hh:mm a")
            format2.timeZone = TimeZone.getDefault()
            try {
                val date = format.parse(strDate)
                mdate = format2.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return mdate
        }
    }
}