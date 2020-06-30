package com.example.media

import android.content.Context
import android.os.Bundle
import android.telephony.TelephonyManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_phone.*

class PhoneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone)

        val tm = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

//        val IMEINumber = tm.deviceId
//        val subscriberID = tm.deviceId
//        val SIMSerialNumber = tm.simSerialNumber
        val networkCountryISO = tm.networkCountryIso
        val SIMCountryISO = tm.simCountryIso
//        val softwareVersion = tm.deviceSoftwareVersion
//        val voiceMailNumber = tm.voiceMailNumber

        var info = "Phone Details:\n"
//        info+="\n IMEI Number:"+IMEINumber
//        info+="\n SubscriberID:"+subscriberID
//        info+="\n Sim Serial Number:"+SIMSerialNumber
        info+="\n Network Country ISO:"+networkCountryISO
        info+="\n SIM Country ISO:"+SIMCountryISO
//        info+="\n Software Version:"+softwareVersion
//        info+="\n Voice Mail Number:"+voiceMailNumber

        textView_phone.text = info
    }
}