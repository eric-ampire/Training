package club.pbreakers.training.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import club.pbreakers.training.R


class DatabaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnEcole -> startActivity(Intent(baseContext, EcoleActivity::class.java))
            R.id.btnParent -> startActivity(Intent(baseContext, ParentActivity::class.java))
        }
    }
}


