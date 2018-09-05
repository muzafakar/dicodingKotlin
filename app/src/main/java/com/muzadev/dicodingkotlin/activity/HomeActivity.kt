package com.muzadev.dicodingkotlin.activity

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.muzadev.dicodingkotlin.R
import com.muzadev.dicodingkotlin.fragment.FavouriteFragment
import com.muzadev.dicodingkotlin.fragment.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger

class HomeActivity : AppCompatActivity(), AnkoLogger, FavouriteFragment.OnFragmentInteractionListener, TeamsFragment.OnFragmentInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, TeamsFragment()).commit()

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_teams -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout, TeamsFragment()).commit()
                    true
                }
                R.id.action_favourites -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout, FavouriteFragment()).commit()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

