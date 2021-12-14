package com.github.alekseygett.cinemaapp.feature.player.ui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.alekseygett.cinemaapp.R
import com.github.alekseygett.cinemaapp.databinding.FragmentPlayerBinding
import com.github.alekseygett.cinemaapp.domain.models.Movie
import com.github.alekseygett.cinemaapp.feature.player.service.PlayerService

class PlayerFragment : Fragment(R.layout.fragment_player) {

    companion object {
        private const val MOVIE_KEY = "movie"

        fun newInstance(movie: Movie) = PlayerFragment().apply {
            arguments = bundleOf(MOVIE_KEY to movie)
        }
    }

    private val binding: FragmentPlayerBinding by viewBinding()

    private val serviceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            if (service is PlayerService.PlayerServiceBinder) {
                binding.playerView.player = service.player
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) { }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startPlayerService()
    }

    override fun onResume() {
        hideSystemUi()
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        showSystemUi()
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().unbindService(serviceConnection)
    }

    private fun startPlayerService() {
        val movie = requireArguments().getParcelable<Movie>(MOVIE_KEY)!!
        val intent = Intent(requireContext(), PlayerService::class.java)
        intent.putExtra(PlayerService.MOVIE_KEY, movie)
        requireActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    private fun hideSystemUi() {
        val window = requireActivity().window
        ViewCompat.getWindowInsetsController(window.decorView)?.let { windowInsetsController ->
            windowInsetsController.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        }
    }

    private fun showSystemUi() {
        val window = requireActivity().window
        val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView)
        windowInsetsController?.show(WindowInsetsCompat.Type.systemBars())
    }

}