package com.example.zemhabit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.zemhabit.R
import com.example.zemhabit.data.HabitData
import com.example.zemhabit.databinding.ActivityEditHabitBinding
import com.example.zemhabit.databinding.ActivityHabitBinding
import com.example.zemhabit.fragment.AddHabitFragment
import com.example.zemhabit.fragment.AddHabitFragment3
import com.example.zemhabit.fragment.EditHabitInfoFragment

class EditHabitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditHabitBinding
    private lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditHabitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.e("EditHabitActivity", "onCreate")
        val habit: HabitData = intent.getSerializableExtra("item") as HabitData
        Log.e("EditHabitActivity", habit.toString())

        binding.tvEditHabitTitle.text = habit.category
        binding.imgEditHabitIcon.setImageResource(habit.image)
        binding.tvEditHabitState.text = habit.state
        when(habit.state) {
            "등록 대기" -> fragment = EditHabitInfoFragment()
            else -> fragment = EditHabitInfoFragment()
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_edit_habit, fragment.apply {
                arguments = Bundle().apply {
                    putSerializable("habit", habit)
                }
            })
            .commit()

        binding.btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }
}