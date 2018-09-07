package com.muzadev.dicodingkotlin.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.muzadev.dicodingkotlin.R
import com.muzadev.dicodingkotlin.helper.DatabaseHelper
import com.muzadev.dicodingkotlin.model.Favorite
import com.muzadev.dicodingkotlin.model.TableConstant
import com.muzadev.dicodingkotlin.model.Team
import com.muzadev.dicodingkotlin.presenter.Presenter
import com.muzadev.dicodingkotlin.presenter.TeamDetailView
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh

class TeamDetailActivity : AppCompatActivity(), TeamDetailView, AnkoLogger {


    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var presenter: Presenter<TeamDetailView>
    private lateinit var teamName: String
    private lateinit var team: Team


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        supportActionBar!!.title = "Team Detail"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        presenter = Presenter(this)
        teamName = intent.getStringExtra("team")
        presenter.getSepecificTeam(teamName)
        swipeRefresh.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light
        )
        swipeRefresh.onRefresh {
            presenter.getSepecificTeam(teamName)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favourite_menu, menu)
        menuItem = menu
        return true
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_on)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_off)
    }

    private fun favoriteState() {
        DatabaseHelper.getInstance(this).use {
            val result = select(TableConstant.TABLE_NAME).whereArgs("${TableConstant.TEAM_ID} = ${team.teamId}")
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
            setFavorite()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_favourite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
            }
            android.R.id.home -> {
                finish()
            }
        }
        return true
    }

    private fun addToFavorite() {
        try {
            DatabaseHelper.getInstance(this).use {
                insert(TableConstant.TABLE_NAME,
                        TableConstant.TEAM_ID to team.teamId,
                        TableConstant.TEAM_NAME to team.teamName,
                        TableConstant.TEAM_BADGE to team.teamBadge
                )
            }
            snackbar(scrollView, "${team.teamName} succesfully added to favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(scrollView, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            DatabaseHelper.getInstance(this).use {
                delete(TableConstant.TABLE_NAME, "${TableConstant.TEAM_ID} = ${team.teamId}")
            }
            snackbar(scrollView, "${team.teamName} is removed from favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(scrollView, e.localizedMessage).show()
        }
    }

    override fun showTeamDetail(team: Team) {
        this.team = team
        favoriteState()
        Glide.with(this).load(team.teamBadge).into(imgTeam)
        tvTeamName.text = team.teamName
        tvTeamYear.text = team.formedYear.toString()
        tvTeamStadium.text = team.stadium
        tvTeamDescription.text = team.temDescription
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

}
