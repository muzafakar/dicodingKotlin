package com.muzadev.dicodingkotlin.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.muzadev.dicodingkotlin.R
import com.muzadev.dicodingkotlin.adapter.ClubAdapter
import com.muzadev.dicodingkotlin.model.Club
import kotlinx.android.synthetic.main.activity_main.* //Kotlin android extension
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    private var club: MutableList<Club> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        rvClub.layoutManager = LinearLayoutManager(this)
        rvClub.adapter = ClubAdapter(this, club) {
            //Anko common
            toast("Showing ${it.name}")
            val intent = Intent(applicationContext, SecondActivity::class.java)
            intent.putExtra("club", it)
            startActivity(intent)
        }
    }

    private fun initData() {
        val name = resources.getStringArray(R.array.club_name)
        val desc = resources.getStringArray(R.array.club_desc)
        val emblem = resources.obtainTypedArray(R.array.club_emblem)

        club.clear()
        for (i in name.indices) {
            club.add(Club(name[i], emblem.getResourceId(i, 0), desc[i]))
        }

        emblem.recycle()

    }
}

