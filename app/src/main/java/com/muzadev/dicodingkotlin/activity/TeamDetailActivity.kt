package com.muzadev.dicodingkotlin.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.muzadev.dicodingkotlin.R
import com.muzadev.dicodingkotlin.R.menu.favourite_menu
import com.muzadev.dicodingkotlin.helper.DatabaseHelper
import com.muzadev.dicodingkotlin.model.Favorite
import com.muzadev.dicodingkotlin.model.Team
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast

class TeamDetailActivity : AppCompatActivity(), AnkoLogger {
    private var menuItem: Menu? = null
    //    private var isFavorite: Boolean = false
    private lateinit var team: Team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        team = intent.getSerializableExtra("team") as Team
        Glide.with(this).load(team.teamBadge).into(imgTeam)
        tvTeamName.text = team.teamName
        tvTeamYear.text = team.formedYear.toString()
        tvTeamStadium.text = team.stadium
        tvTeamDescription.text = team.temDescription

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(favourite_menu, menu)
        menuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            favourite_menu -> {
                addToFavorite()
                true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun addToFavorite() {
        try {
            DatabaseHelper.getInstance(this).use {
                insert(Favorite.TABLE_NAME,
                        Favorite.TEAM_NAME to team.teamName,
                        Favorite.TEAM_ID to team.teamId,
                        Favorite.TEAM_BADGE to team.teamBadge,
                        Favorite.TEAM_DESC to team.temDescription
                )
            }
            snackbar(scrollView, "${team.teamName} is added to favorite")
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }
}
