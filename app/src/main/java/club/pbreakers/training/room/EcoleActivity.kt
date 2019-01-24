package club.pbreakers.training.room

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.loader.content.AsyncTaskLoader
import club.pbreakers.training.R
import club.pbreakers.training.database.Ecole

class EcoleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecole)
    }

    fun showAddEcoleDialog(view: View) {

    }
}
