package com.example.propose

import android.os.Bundle
import android.widget.VideoView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.propose.databinding.ActivityProposalVideoBinding

class ProposalVideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proposal_video)

        val videoView: VideoView = findViewById(R.id.videoView)
//        val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.proposal_video)
//        videoView.setVideoURI(uri)
        videoView.start()
    }
}