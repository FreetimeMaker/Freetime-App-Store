package com.freetime.appstore.german.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.freetime.appstore.R
import com.freetime.appstore.english.apps.freetime.*
import com.freetime.appstore.english.contributions.GH_Activity
import com.freetime.appstore.english.main.MainActivity
import com.freetime.appstore.german.apps.freetime.SSMPCJ_Activity

class DE_MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_de_main)

        val gwInfobtn: Button = findViewById(R.id.gwInfobtn)
        gwInfobtn.setOnClickListener {
            startActivity(Intent(this@DE_MainActivity, GW_Activity::class.java))
        }

        val donoInfobtn: Button = findViewById(R.id.donoInfobtn)
        donoInfobtn.setOnClickListener {
            startActivity(Intent(this@DE_MainActivity, Dono_Activity::class.java))
        }

        val fasInfobtn: Button = findViewById(R.id.fasInfobtn)
        fasInfobtn.setOnClickListener {
            startActivity(Intent(this@DE_MainActivity, FAS_Activity::class.java))
        }

        val catInfobtn: Button = findViewById(R.id.catInfobtn)
        catInfobtn.setOnClickListener {
            startActivity(Intent(this@DE_MainActivity, CatClickerActivity::class.java))
        }

        val PLCInfobtn: Button = findViewById(R.id.PLCInfobtn)
        PLCInfobtn.setOnClickListener {
            startActivity(Intent(this@DE_MainActivity, PLC_Activity::class.java))
        }

        val plc2Infobtn: Button = findViewById(R.id.plc2Infobtn)
        plc2Infobtn.setOnClickListener {
            startActivity(Intent(this@DE_MainActivity, PLC2Activity::class.java))
        }

        val platInfobtn: Button = findViewById(R.id.platInfobtn)
        platInfobtn.setOnClickListener {
            startActivity(Intent(this@DE_MainActivity, PlatformerActivity::class.java))
        }

        val osInfobtn: Button = findViewById(R.id.osInfobtn)
        osInfobtn.setOnClickListener {
            startActivity(Intent(this@DE_MainActivity, OS_Activity::class.java))
        }

        val cryptoInfobtn: Button = findViewById(R.id.cryptoInfobtn)
        cryptoInfobtn.setOnClickListener {
            startActivity(Intent(this@DE_MainActivity, CryptoClickerActivity::class.java))
        }

        val RPJInfobtn: Button = findViewById(R.id.RPJInfobtn)
        RPJInfobtn.setOnClickListener {
            startActivity(Intent(this@DE_MainActivity, RadioPlayerJavaActivity::class.java))
        }

        val KPDHJInfobtn: Button = findViewById(R.id.KPDHJInfobtn)
        KPDHJInfobtn.setOnClickListener {
            startActivity(Intent(this@DE_MainActivity, KPDHJ_Activity::class.java))
        }

        val SSMPCJInfobtn: Button = findViewById(R.id.SSMPCJInfobtn)
        SSMPCJInfobtn.setOnClickListener {
            startActivity(Intent(this@DE_MainActivity, SSMPCJ_Activity::class.java))
        }

        val ghInfobtn: Button = findViewById(R.id.ghInfobtn)
        ghInfobtn.setOnClickListener {
            startActivity(Intent(this@DE_MainActivity, GH_Activity::class.java))
        }

        val enInfobtn: Button = findViewById(R.id.enInfobtn)
        enInfobtn.setOnClickListener {
            startActivity(Intent(this@DE_MainActivity, MainActivity::class.java))
        }
    }
}
