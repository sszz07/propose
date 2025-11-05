package com.example.propose

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StoryAdapter(
    private val stories: List<StoryItem>,
    private val onNextClick: (Int) -> Unit
) : RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

    inner class StoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val storyImage: ImageView = view.findViewById(R.id.storyImage)
        val storyText: TextView = view.findViewById(R.id.storyText)
        val nextButton: TextView = view.findViewById(R.id.nextButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_story_card, parent, false)
        return StoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story = stories[position]
        holder.storyImage.setImageResource(story.imageRes)
        holder.storyText.text = story.text

        // 마지막 카드일 경우 버튼 문구 변경
        if (position == stories.lastIndex) {
            holder.nextButton.text = "프로포즈 보기 💍"
        } else {
            holder.nextButton.text = "열어보기\uD83D\uDC95"
        }

        // 클릭 시 MainActivity로 콜백
        holder.nextButton.setOnClickListener {
            onNextClick(position)
        }
    }

    override fun getItemCount(): Int = stories.size
}
