package kz.aknur.newchildcare.content.child.add

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_child_add.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.aknur.newchildcare.R
import kz.aknur.newchildcare.content.child.add.models.ChildAddRequest
import kz.aknur.newchildcare.content.child.childlist.ChildlistActivity
import org.jetbrains.anko.alert
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*


class ChildAddActivity : AppCompatActivity() {

    companion object {
        const val TAG = "ChildAddActivity"
    }





    private lateinit var viewModel: ChildAddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child_add)
        lets()
    }

    private fun lets() {
        viewModel = ViewModelProvider(this).get(ChildAddViewModel::class.java)
        observer()
        chDate.onClick {
            showDateTimeDialog(chDate)
        }
        sendChildInfo.onClick { viewModel.getId() }
    }

    @SuppressLint("SimpleDateFormat")
    private fun showDateTimeDialog(date_time_in: TextView) {
        val calendar = Calendar.getInstance()
        val dateSetListener =
            OnDateSetListener { view, year, month, dayOfMonth ->
                calendar[Calendar.YEAR] = year
                calendar[Calendar.MONTH] = month
                calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                val timeSetListener =
                    OnTimeSetListener { view, hourOfDay, minute ->
                        calendar[Calendar.HOUR_OF_DAY] = hourOfDay
                        calendar[Calendar.MINUTE] = minute

                        val format = SimpleDateFormat(
                            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US
                        )
                        format.timeZone = TimeZone.getTimeZone("UTC")

                        date_time_in.text = format.format(calendar.time)
                    }
                TimePickerDialog(
                    this,
                    timeSetListener,
                    calendar[Calendar.HOUR_OF_DAY],
                    calendar[Calendar.MINUTE],
                    false
                ).show()
            }
        DatePickerDialog(
            this,
            dateSetListener,
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        ).show()
    }


    private fun prepareChildInfo(userId: Int) {
        if (
            chMaleSpinner.selectedItemPosition!=0
            && childNameEt.text.isNotEmpty()
            && chDate.text.isNotEmpty()
            && chD.text.isNotEmpty()
        ) {
            setLoading(true)
            var gender = "FEMALE"
            if (chMaleSpinner.selectedItemPosition == 1) {
                gender = "MALE"
            }
            val child =
                ChildAddRequest(
                    chDate.text.toString(),
                    chD.text.toString(),
                    gender,
                    0,
                    0,
                    childNameEt.text.toString(),
                    userId,
                    0
                )
            Log.d(TAG, child.toString())
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.addNewChild(child)
            }
        } else {
            Toast.makeText(this, "Необходимо заполнить все поля", Toast.LENGTH_LONG).show()
        }
    }


    @SuppressLint("LongLogTag")
    private fun observer() {
        viewModel.isError.observe(this, Observer {
            errorDialog(getString(R.string.error_unknown_body))
        })
        viewModel.isSuccess.observe(this, Observer {
            if (it) {
                Toast.makeText(this, "Добавлено!", Toast.LENGTH_SHORT).show()
                finish()
                startActivity(intentFor<ChildlistActivity>())
            }
        })

        viewModel.userId.observe(this, Observer {
            prepareChildInfo(it)
        })

    }

    private fun errorDialog(errorMsg: String) {
        alert {
            title = getString(R.string.error_unknown_title)
            message = errorMsg
            isCancelable = false
            positiveButton(getString(R.string.dialog_retry)) { dialog ->
                dialog.dismiss()
                lets()
            }
            negativeButton(getString(R.string.dialog_exit)) {
                finish()
            }
        }?.show()
    }


    private fun setLoading(b: Boolean) {
        if (b) childAddLv.visibility = View.VISIBLE else childAddLv.visibility = View.GONE
    }


}