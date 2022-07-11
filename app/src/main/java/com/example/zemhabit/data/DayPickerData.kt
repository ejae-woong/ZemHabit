package com.example.zemhabit.data

import androidx.appcompat.widget.AppCompatToggleButton

data class DayPickerData(
    var checked: Boolean,
    val day: String,
    val toggle: AppCompatToggleButton,
    val num: Int
)
