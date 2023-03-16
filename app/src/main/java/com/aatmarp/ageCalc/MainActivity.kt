package com.aatmarp.ageCalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var selectedDate: TextView? = null
    private var resultMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        selectedDate = findViewById(R.id.displayDate)
        resultMinutes = findViewById(R.id.resultText)
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }
    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
                view, selectedYear, selectedMonth, selectedDay->
            Toast.makeText(this,"Year $year chosen!",Toast.LENGTH_LONG).show()
            //for display purposes
            val Date = "$selectedDay/$selectedMonth/$selectedYear"
            selectedDate?.text = Date
            //for calculation purpose
            val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val DateOBJ = sdf.parse(Date)
            val sdInMinutes = DateOBJ.time / 60000
            val currDateOBJ = sdf.parse(sdf.format(System.currentTimeMillis()))
            val cdInMinutes = currDateOBJ.time/60000
            val diffMinutes = cdInMinutes - sdInMinutes
            resultMinutes?.text = diffMinutes.toString()
        },
        year,
        month,
        day
        ).show()
    }
}

