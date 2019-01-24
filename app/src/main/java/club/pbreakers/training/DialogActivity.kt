package club.pbreakers.training

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import java.util.*

class DialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
    }

    fun onButtonClick(view: View) {
        when(view.id) {
            R.id.buttonDate -> {
                DateFragment().show(supportFragmentManager, "date")
            }

            R.id.buttonTime -> {
                TimeFragment().show(supportFragmentManager, "time")
            }

            R.id.buttonDialog -> {
                AlertDialog.Builder(this)
                    .setTitle("Titre")
                    .setMessage("Message")
                    .setPositiveButton("Oui") { a, b -> }
                    .setNegativeButton("Non") { a, b -> }
                    .setNeutralButton("Later") { a, b -> }
                    .show()
            }
        }
    }
}

class DateFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val jour = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        val moi = Calendar.getInstance().get(Calendar.MONTH)
        val annees = Calendar.getInstance().get(Calendar.YEAR)

        return DatePickerDialog(activity, this, annees, moi, jour)
    }
}

class TimeFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        Toast.makeText(activity, "Time", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val heure = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val minute = Calendar.getInstance().get(Calendar.MINUTE)

        return TimePickerDialog(activity, this, heure, minute, true)
    }
}
