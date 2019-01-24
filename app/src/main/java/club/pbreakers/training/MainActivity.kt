package club.pbreakers.training

import android.content.Intent
import android.graphics.Bitmap
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loader = LoaderManager.getInstance(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnViewPager -> startActivity(Intent(this, ViewPagerActivity::class.java))
            R.id.btnRoom      -> startActivity(Intent(this, ProfileActivity::class.java))
            R.id.btnDialog    -> startActivity(Intent(this, DialogActivity::class.java))
            R.id.btnDrawer    -> startActivity(Intent(this, DrawerActivity::class.java))
        }
    }

    class MyAsyncTask : AsyncTask<String, Int, Bitmap>() {
        override fun doInBackground(vararg params: String?): Bitmap {
            TODO("Slut")
        }
    }
}
