package com.muzadev.dicodingkotlin

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.muzadev.dicodingkotlin.R.color.favColor
import com.muzadev.dicodingkotlin.model.Club
import org.jetbrains.anko.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {
    private var club: MutableList<Club> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        MainActivityUI().setContentView(this)
//        initData()
//        rvClub.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
//        rvClub.adapter = ClubAdapter(this, club) {
//            val name = it.name.toString()
//            alert(name + "?") {
//                yesButton {
//                    Toasty.info(applicationContext, name, Toast.LENGTH_SHORT).show()
//                }
//                noButton { toast("no") }
//            }.show()
//        }
    }

//    private fun initData() {
//        val name = resources.getStringArray(R.array.club_name)
//        val emblem = resources.obtainTypedArray(R.array.club_emblem)
//        club.clear()
//        for (i in name.indices) {
//            club.add(Club(name[i], emblem.getResourceId(i, 0)))
//        }
//
//        emblem.recycle()
//
//    }

    class MainActivityUI : AnkoComponent<MainActivity> {

        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            verticalLayout {
                padding = dip(16)
                val name = editText {
                    hint = "What's your name?"
                }

                button("Say Hello") {
                    backgroundColor = ContextCompat.getColor(context, favColor)
                    textColor = Color.WHITE
                    onClick {
                        toast("Hello, ${name.text}!")
                    }
                }.lparams(width = matchParent, height = wrapContent) {
                    topMargin = dip(6)
                }
                button("Show Alert") {
                    backgroundColor = ContextCompat.getColor(context, favColor)
                    textColor = Color.WHITE
                    onClick {
                        alert("Happy coding!, hello ${name.text}") {
                            yesButton { toast("hohoho") }
                            noButton {}
                        }.show()
                    }
                }.lparams(width = matchParent, height = wrapContent) {
                    topMargin = dip(6)
                }
                button("Show Selector") {
                    backgroundColor = ContextCompat.getColor(context, favColor)
                    textColor = Color.WHITE
                    onClick {
                        val club = resources.getStringArray(R.array.club_name)
                        selector("Hello ${name.text}, what's your favourite footbal club?", club.toList()) { _, i ->
                            toast("so you love ${club[i]}, right?")
                        }
                    }
                }.lparams(width = matchParent, height = wrapContent) {
                    topMargin = dip(6)
                }
                button("Show Snackbar") {
                    backgroundColor = ContextCompat.getColor(context, favColor)
                    textColor = Color.WHITE
                    onClick {
                        toast("Snackbar here")
                    }
                }.lparams(width = matchParent, height = wrapContent) {
                    topMargin = dip(6)
                }
                button("Show Progressbar") {
                    backgroundColor = ContextCompat.getColor(context, favColor)
                    textColor = Color.WHITE
                    onClick {
                        indeterminateProgressDialog("Hello ${name.text}! \n please wait...").show()
                    }
                }.lparams(width = matchParent, height = wrapContent) {
                    topMargin = dip(6)
                }
                button("Go to second activity") {
                    backgroundColor = ContextCompat.getColor(context, favColor)
                    textColor = Color.WHITE
                    onClick {
                        startActivity<SecondActivity>("name" to "${name.text}")
                    }
                }.lparams(width = matchParent, height = wrapContent) {
                    topMargin = dip(6)
                }

            }

        }

    }
}

