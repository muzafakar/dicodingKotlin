package com.muzadev.dicodingkotlin.activity

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity.CENTER_HORIZONTAL
import android.widget.ImageView
import android.widget.TextView
import com.muzadev.dicodingkotlin.model.Club
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class SecondActivity : AppCompatActivity() {
    lateinit var clubName: TextView
    lateinit var clubEmblem: ImageView
    lateinit var clubDesc: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SecondActivityUI().setContentView(this)

        val intent = intent
        val club = intent.getSerializableExtra("club") as Club

        Picasso.get().load(club.emblem!!).fit().into(clubEmblem)
        clubName.text = club.name
        clubDesc.text = club.desc

    }

    //Anko layout
    inner class SecondActivityUI : AnkoComponent<SecondActivity> {
        override fun createView(ui: AnkoContext<SecondActivity>) = with(ui) {
            verticalLayout {
                padding = dip(16)
                gravity = CENTER_HORIZONTAL

                clubEmblem = imageView().lparams(width = dip(100), height = dip(100))
                clubName = textView {
                    textSize = 20f
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = wrapContent)
                clubDesc = textView().lparams(width = wrapContent, height = wrapContent)
            }
        }

    }
}
