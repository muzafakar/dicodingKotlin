package com.muzadev.dicodingkotlin.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.muzadev.dicodingkotlin.R
import com.muzadev.dicodingkotlin.fragment.FavoriteTeamFragment
import com.muzadev.dicodingkotlin.fragment.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity

class TeamHomeActivity : AppCompatActivity(), AnkoLogger {
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
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout, FavoriteTeamFragment()).commit()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_switch, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_team_app -> {
                startActivity<TeamHomeActivity>()
                finish()
            }
            R.id.action_match_app -> {
                startActivity<MatchHomeActivity>()
                finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }
}

