package club.pbreakers.training

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_view_pager.*
import kotlinx.android.synthetic.main.viewpager_item.view.*
import java.util.*
import kotlin.coroutines.experimental.coroutineContext

class ViewPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        var currentPosition = 0

        val data = arrayListOf(
            Item("Shekinah", "Lorem idfdfdfdfd", R.drawable.ic_backup_black_24dp),
            Item("Eric", "Lorem idfdfdfdfd", R.drawable.ic_backup_black_24dp),
            Item("Bigomoker", "Lorem idfdfdfdfd", R.drawable.ic_backup_black_24dp),
            Item("Bigomoker", "Lorem idfdfdfdfd", R.drawable.ic_backup_black_24dp)
        )

        val adapter = ViewPagerAdapter(data)


        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) { }
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { }

            override fun onPageSelected(pos: Int) {
                currentPosition = pos

                for (a in 0 until dotLayout.childCount) {

                    if (a == pos) {
                        dotLayout.getChildAt(a).setBackgroundResource(R.drawable.background_light)
                    } else {
                        dotLayout.getChildAt(a).setBackgroundResource(R.drawable.background)
                    }
                }

                when (pos) {
                    0 -> btnBack.isEnabled = false

                    dotLayout.childCount - 1 -> {
                        btnBack.visibility = View.VISIBLE
                        btnNext.text = "Finish"
                    }
                    else -> {
                        btnBack.isEnabled = true
                        btnBack.visibility = View.VISIBLE
                        btnNext.text = "Next"
                    }
                }
            }
        })

        btnBack.setOnClickListener {
            viewPager.currentItem = currentPosition - 1
        }

        btnNext.setOnClickListener {
            viewPager.currentItem = currentPosition + 1
        }

        addDot(data.size)
    }

    private fun addDot(data: Int) {
        dotLayout.removeAllViews()

        for (value in 1..data) {
            val textView = TextView(this).apply {
                setBackgroundResource(R.drawable.background)
            }

            dotLayout.addView(textView)
        }
    }
}

class ViewPagerAdapter(val data: List<Item>) : PagerAdapter() {

    override fun isViewFromObject(view: View, objects: Any): Boolean {
        return view == objects as ConstraintLayout
    }

    override fun getCount(): Int = data.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.viewpager_item, container, false)

        view.apply {
            tvBody.text = data[position].body
            tvTitle.text = data[position].title
            ivPicture.setImageResource(data[position].image)
        }

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}

data class Item(val title: String, val body: String, val image: Int)
