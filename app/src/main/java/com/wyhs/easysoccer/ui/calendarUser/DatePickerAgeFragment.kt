package com.wyhs.easysoccer.ui.calendarUser

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerAgeFragment(val listener: (day: Int, month: Int, year: Int) -> Unit) :
    DialogFragment(), DatePickerDialog.OnDateSetListener {


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        listener(dayOfMonth, month, year)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        // Establecer la fecha mínima en enero de 2008
        c.set(2008, 0, 1)

        val picker = DatePickerDialog(activity as Context, this, year, month, day)

        // Establecer la fecha máxima en la fecha actual
        picker.datePicker.maxDate = c.timeInMillis
        return picker

    }



}