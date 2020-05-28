package com.example.kot_agecounter

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.net.DatagramPacket
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonDataPicker.setOnClickListener { view ->
            tapDataPicker(view)
            Toast.makeText(this, "Pick a date", Toast.LENGTH_SHORT).show()
        }
    }

    fun tapDataPicker(view: View) {
//paduoda current date..diena kai paspaudei ant mygtuko
//        siendienos diena yra paduodama i datepicker dialoga kur reikia pasirinkti diena
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this, "The chosen year is $selectedYear, month: $selectedMonth, day: $selectedDayOfMonth ", Toast.LENGTH_SHORT).show()

            val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
            userSelectedDate.setText(selectedDate)

            val formatePickedDate = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val displayedDate = formatePickedDate.parse(selectedDate)


            val selectedDateInMinutes = displayedDate!!.time / 60000
            val currentDate = formatePickedDate.parse(formatePickedDate.format(System.currentTimeMillis()))

            val currentDateToMinutes = currentDate!!.time / 60000
            val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes

            ageInMinutes.setText(differenceInMinutes.toString())

        },
                year, month, dayOfMonth)

        dpd.datePicker.maxDate(Date().time - 8640000)
        dpd.show()

    }
}

private operator fun Long.invoke(i: Long) {

}

