package com.muzadev.dicodingkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import org.jetbrains.anko.dip
import org.jetbrains.anko.padding
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

class SecondActivity : AppCompatActivity() {
    private var name: String = ""
    lateinit var tvName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            padding = dip(16)
            tvName = textView()
        }

        val intent = intent
        name = intent.getStringExtra("name")
        tvName.text = name

    }
//
//    class SecondActivityUI:AnkoComponent<SecondActivity>{
//        override fun createView(ui: AnkoContext<SecondActivity>)= with(ui) {
//            verticalLayout {
//                padding = dip(16)
//                tvName = textView()
//            }
//        }
//
//    }
}
