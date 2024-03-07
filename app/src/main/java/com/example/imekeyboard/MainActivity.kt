package com.example.imekeyboard

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    private val REQUEST_PHONE_STATE_PERMISSION = 1001
    private  var t : Long = 0
//    private lateinit var t1 : TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.keyboard)
        Log.d("MainActivity", "onCreate: ")

//        t1= findViewById(R.id.t1)
//        t1.setOnClickListener {
//            val uri = Uri.parse("https://wa.me/$t")
//            val intent = Intent(Intent.ACTION_VIEW, uri)
//            startActivity(intent)
//        }
        try {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_PHONE_STATE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Request the permission
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.READ_PHONE_STATE),
                    REQUEST_PHONE_STATE_PERMISSION
                )
            } else {
                // Permission already granted, so get the phone number
                t = if(!getPhoneNumber().isNullOrEmpty())
                    getPhoneNumber().toLong()
                else
                    0
//                if (t < 10)
//                    t = "0000000000"
            }
        }
        catch (e :ArithmeticException)
        {
            Log.d("Exception" , "Permission error")
        }

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putLong("phone_no", t)
        editor.apply()

        Log.d("Shared Get " , sharedPreferences.getLong("phone_no" , 0).toString())
    }

    @SuppressLint("HardwareIds")
    private fun getPhoneNumber() :String {
        val telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
        try {
            val phoneNumber = if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_SMS
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_PHONE_NUMBERS
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_PHONE_STATE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(this, "Phone number not available", Toast.LENGTH_SHORT).show()
                Log.d("@@@", telephonyManager.line1Number)
                return ""
            } else {
                Log.d("@@@", telephonyManager.line1Number)
                Toast.makeText(this, "Phone ${telephonyManager.line1Number}", Toast.LENGTH_SHORT)
                    .show()
            }
            telephonyManager.line1Number
            Log.d("@@@", telephonyManager.line1Number)
        }catch (e:SecurityException){
            Log.d("exception","p2 Error")
        }
        return telephonyManager.line1Number
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        try {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)

            if (requestCode == REQUEST_PHONE_STATE_PERMISSION) {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, so get the phone number

                    getPhoneNumber()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
        catch (e: SecurityException){
            Log.d("ERROR" ,  "onRequest persmission result")
        }
    }

    companion object SharedPreferencesUtil {
        fun getLong(sharedPreferences: SharedPreferences): Long {
            return sharedPreferences.getLong("key_long", 0L)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d("Destroyed" , "Mai")
    }
}