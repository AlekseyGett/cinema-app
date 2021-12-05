package com.github.alekseygett.cinemaapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.github.alekseygett.cinemaapp.feature.movies.ui.MoviesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigateTo(MoviesFragment.newInstance())
    }

    private fun navigateTo(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(android.R.id.content, fragment)
        }
    }

}