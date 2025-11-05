package com.example.propose

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.propose.databinding.ActivityMainBinding
import com.example.propose.databinding.DialogStoryBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null

    private val stories = listOf(
        StoryItem(R.drawable.first, "처음 만난 날 💖"),
        StoryItem(R.drawable.hunread, "100일 기념 💐"),
        StoryItem(R.drawable.third, "우리의 3주년 💞"),
        StoryItem(R.drawable.lastpropose, "이제 우리의 다음 챕터는... 💍")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🎵 배경음악 재생
        mediaPlayer = MediaPlayer.create(this, R.raw.ost)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()

        // 🌸 스토리 클릭 동작
        binding.storyPager.adapter = StoryAdapter(stories) { position ->
            when (position) {
                0 -> showCustomStoryDialog(
                    title = "처음 만난 날 🌼",
                    message = "우리의 이야기,\n\n그날부터 시작됐어."
                )
                1 -> showCustomStoryDialog(
                    title = "100일 기념 💐",
                    message = "짧다면 짧고, 길다면 긴 100일.\n\n그 모든 순간이 자기라서 참 행복했어."
                )
                2 -> showCustomStoryDialog(
                    title = "우리의 3주년 💞",
                    message = "처음의 설렘은 익숙한 미소가 되었고,\n\n매일의 순간이 우리의 이야기가 되었어.\n\n사랑해. 오늘도 그리고 앞으로도."
                )
                else -> {
                    // 💍 마지막 → 영상 화면 이동
                    mediaPlayer?.stop()
                    mediaPlayer?.release()
                    mediaPlayer = null
                    startActivity(Intent(this, ProposalVideoActivity::class.java))
                }
            }
        }
    }

    // 💖 커스텀 다이얼로그 함수
    private fun showCustomStoryDialog(title: String, message: String) {
        val dialogBinding = DialogStoryBinding.inflate(layoutInflater)
        val dialog = android.app.Dialog(this)

        dialog.setContentView(dialogBinding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)

        // 텍스트 설정
        dialogBinding.storyTitle.text = title
        dialogBinding.storyMessage.text = message

        // “다음으로” 버튼 클릭
        dialogBinding.nextButton.setOnClickListener {
            dialog.dismiss()
            val current = binding.storyPager.currentItem
            binding.storyPager.currentItem = current + 1
        }

        dialog.show()
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.pause()
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
