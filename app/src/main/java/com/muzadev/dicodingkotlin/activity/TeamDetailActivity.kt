package com.muzadev.dicodingkotlin.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.muzadev.dicodingkotlin.R
import com.muzadev.dicodingkotlin.helper.DatabaseHelper
import com.muzadev.dicodingkotlin.model.TableConstant
import com.muzadev.dicodingkotlin.model.Team
import com.muzadev.dicodingkotlin.presenter.FavoriteView
import com.muzadev.dicodingkotlin.presenter.Presenter
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.info
import org.jetbrains.anko.toast

class TeamDetailActivity : AppCompatActivity(), FavoriteView, AnkoLogger {


    private var menuItem: Menu? = null
    //    private var isFavorite: Boolean = false
    private lateinit var presenter: Presenter<FavoriteView>
    private lateinit var teamName: String
    private lateinit var team: Team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter = Presenter(this)
        teamName = intent.getStringExtra("team")


        presenter.getSepecificTeam(teamName)

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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_favourite -> {
                toast("favorite")
                info { "favorite" }
                addToFavorite()
            }
            android.R.id.home -> {
                toast("home")
                info { "home" }
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

    override fun showTeamDetail(team: Team) {
        this.team = team
        Glide.with(this).load(team.teamBadge).into(imgTeam)
        tvTeamName.text = team.teamName
        tvTeamYear.text = team.formedYear.toString()
        tvTeamStadium.text = team.stadium
        tvTeamDescription.text = team.temDescription
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
