package com.anshul.mehta.a24m.agecalculator

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)

        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view);
//            Toast.makeText(this, "button work 22 ", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDatePicker(view: View) {

        val myCalendar = Calendar.getInstance();
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
            view, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            val tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
            tvSelectedDate.setText(selectedDate)

            val sdf = SimpleDateFormat("dd/MM/yy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)

            val salectedDateInMin = theDate!!.time / 60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

            val currentDateToMIn = currentDate!!.time/60000

            val diffInMin   = currentDateToMIn - salectedDateInMin

            val tvAgeInMin = findViewById<TextView>(R.id.tvAgeInMin)
            tvAgeInMin.setText(diffInMin.toString())



        }
                , year, month, day)

        dpd.datePicker.setMaxDate(Date().time - 8640000)
        dpd.show()
    }

}

