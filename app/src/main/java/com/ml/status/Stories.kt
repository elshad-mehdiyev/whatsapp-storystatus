package com.ml.status

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import jp.shts.android.storiesprogressview.StoriesProgressView


class Stories : AppCompatActivity(), StoriesProgressView.StoriesListener {

    private val resource = intArrayOf(
        R.drawable.image_one,
        R.drawable.image_two,
        R.drawable.image_three,
        R.drawable.image_four,
        R.drawable.image_five,
        R.drawable.image_six,
        R.drawable.image_eight
    )

    var pressTime = 0L
    var limit = 500L

    private var storiesProgressView: StoriesProgressView? = null
    private var image: ImageView? = null
    private var counter = 0

    private val onTouchListener = View.OnTouchListener { v, event ->

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

                pressTime = System.currentTimeMillis()

                storiesProgressView?.pause()
                false
            }
            MotionEvent.ACTION_UP -> {

                val now = System.currentTimeMillis()

                storiesProgressView?.resume()

                limit < now - pressTime
            }
            else -> false
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stories)

        image = findViewById(R.id.image)
        storiesProgressView = findViewById(R.id.stories)

        storiesProgressView?.setStoriesCount(resource.size)
        storiesProgressView?.setStoryDuration(3000L)
        storiesProgressView?.setStoriesListener(this)
        storiesProgressView?.startStories(counter)
        image?.setImageResource(resource[counter])

        val reverse = findViewById<View>(R.id.reverse)

        reverse.setOnClickListener {
            storiesProgressView?.reverse();

        }
        reverse.setOnTouchListener(onTouchListener)
        val skip = findViewById<View>(R.id.skip)

        skip.setOnClickListener {
            storiesProgressView?.skip()
        }
        skip.setOnTouchListener(onTouchListener)


    }

    override fun onNext() {
        image?.setImageResource(resource[++counter]);
    }

    override fun onPrev() {
        if ((counter - 1) < 0) return
        image?.setImageResource(resource[--counter]);
    }

    override fun onComplete() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        storiesProgressView?.destroy()
    }
}