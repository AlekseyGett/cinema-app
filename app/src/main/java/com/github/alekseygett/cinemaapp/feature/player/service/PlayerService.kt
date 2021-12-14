package com.github.alekseygett.cinemaapp.feature.player.service

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.widget.Toast
import com.github.alekseygett.cinemaapp.R
import com.github.alekseygett.cinemaapp.domain.models.Movie
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import org.koin.android.ext.android.inject

private const val PLAYBACK_CHANNEL_ID = "playback_channel"
private const val PLAYBACK_NOTIFICATION_ID = 42
private const val MEDIA_SESSION_TAG = "media_session"

class PlayerService : Service() {

    companion object {
        const val MOVIE_KEY = "movie"
    }

    private val player: ExoPlayer by inject()
    private val descriptionAdapter: PlayerNotificationManager.MediaDescriptionAdapter by inject()

    private val notificationListener = object : PlayerNotificationManager.NotificationListener {
        override fun onNotificationPosted(
            notificationId: Int,
            notification: Notification,
            ongoing: Boolean
        ) {
            if (ongoing) {
                startForeground(notificationId, notification)
            } else {
                stopForeground(false)
            }
        }

        override fun onNotificationCancelled(notificationId: Int, dismissedByUser: Boolean) {
            stopSelf()
        }
    }

    private lateinit var notificationManager: PlayerNotificationManager

    override fun onBind(intent: Intent?): IBinder {
        intent?.getParcelableExtra<Movie>(MOVIE_KEY)?.let { movie ->
            setupPlayer(movie)
        }

        return PlayerServiceBinder()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        stopPlayer()
        stopSelf()
        return super.onUnbind(intent)
    }

    override fun onCreate() {
        super.onCreate()

        notificationManager = PlayerNotificationManager.Builder(
            this,
            PLAYBACK_NOTIFICATION_ID,
            PLAYBACK_CHANNEL_ID
        )
            .setChannelNameResourceId(R.string.playback_channel_name)
            .setMediaDescriptionAdapter(descriptionAdapter)
            .setNotificationListener(notificationListener)
            .build()

        notificationManager.apply {
            setUseNextAction(false)
            setUsePreviousAction(false)
            setUseStopAction(false)

            setUseRewindAction(true)
            setUseFastForwardAction(true)

            setPlayer(player)
        }
    }

    override fun onDestroy() {
        notificationManager.setPlayer(null)
        player.release()

        super.onDestroy()
    }

    private fun setupPlayer(movie: Movie) {
        player.apply {
            addListener(PlayerEventListener())

            val mediaMetadata = MediaMetadata.Builder()
                .setTitle(movie.title)
                .build()

            val mediaItem = MediaItem.Builder()
                .setUri(movie.videoUrl)
                .setMediaMetadata(mediaMetadata)
                .build()

            setMediaItem(mediaItem)
            prepare()
            play()
        }
    }

    private fun stopPlayer() {
        player.apply {
            stop()
        }
    }

    private inner class PlayerEventListener : Player.Listener {
        override fun onPlayerError(error: PlaybackException) {
            error.localizedMessage?.let { errorMessage ->
                showErrorMessage(errorMessage)
            }
        }

        private fun showErrorMessage(errorMessage: String) {
            Toast.makeText(this@PlayerService, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    inner class PlayerServiceBinder : Binder() {
        val player: ExoPlayer = this@PlayerService.player
    }

}