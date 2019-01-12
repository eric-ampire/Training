package club.pbreakers.training

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_view_pager.*
import kotlinx.android.synthetic.main.viewpager_item.view.*

class ViewPagerActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        val data = arrayListOf(
            Item(
                "Upload",
                "To create a basic JUnit 4 test class, create a Java class for testing in the directory specified at the beginning of this section. It should contain one or more methods and behavior rules defined by JUnit annotations.",
                R.drawable.ic_backup_black_24dp
            ),
            Item(
                "Safe",
                "To create a basic JUnit 4 test class, create a Java class for testing in the directory specified at the beginning of this section. It should contain one or more methods and behavior rules defined by JUnit annotations.",
                R.drawable.ic_beenhere_black_24dp
            ),
            Item(
                "Business",
                "To create a basic JUnit 4 test class, create a Java class for testing in the directory specified at the beginning of this section. It should contain one or more methods and behavior rules defined by JUnit annotations.",
                R.drawable.ic_business_center_black_24dp
            ),
            Item(
                "Cloud",
                "To create a basic JUnit 4 test class, create a Java class for testing in the directory specified at the beginning of this section. It should contain one or more methods and behavior rules defined by JUnit annotations.",
                R.drawable.ic_cloud_done_black_24dp
            )
        )

        val adapter = ViewPagerAdapter(data)


        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(this)

        addDot(data.size)

        btnNext.setOnClickListener {
            viewPager.currentItem = currentPosition + 1
        }

        btnBack.setOnClickListener {
            viewPager.currentItem = currentPosition - 1
        }
    }

    override fun onPageScrollStateChanged(state: Int) {}
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(pos: Int) {
        currentPosition = pos

        for (a in 0 until dotLayout.childCount) {

            val view = dotLayout.getChildAt(a) as TextView

            if (a == pos) {
                view.setTextColor(resources.getColor(android.R.color.black))
            } else {
                view.setTextColor(resources.getColor(R.color.colorLight))
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

    private fun addDot(data: Int) {
        dotLayout.removeAllViews()

        for (value in 0 until data) {
            val textView = TextView(this).apply {
                text = Html.fromHtml("&#8226;")
                textSize = 35.0f

                if (value == 0)
                    setTextColor(resources.getColor(android.R.color.black))
                else
                    setTextColor(resources.getColor(R.color.colorLight))
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
