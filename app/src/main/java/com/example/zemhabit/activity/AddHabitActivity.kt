package com.example.zemhabit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.zemhabit.R
import com.example.zemhabit.databinding.ActivityAddHabitBinding
import com.example.zemhabit.databinding.ActivityHabitBinding
import com.example.zemhabit.fragment.AddHabitFragment
import com.example.zemhabit.fragment.AddHabitFragment3

class AddHabitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddHabitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("AddHabitActivity", "onCreate")
        super.onCreate(savedInstanceState)
        binding = ActivityAddHabitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(R.id.fl_add_habit, AddHabitFragment()).commit()

        binding.btnBack.setOnClickListener {
            if (supportFragmentManager.findFragmentById(R.id.fl_add_habit) == AddHabitFragment3()) {
                Log.e("asdasdadsad", "asdadsasdasd")
                AddHabitFragment3().cancelHabit(it)
            } else {
                onBackPressed()
            }
        }
    }

    override fun onBackPressed() {
        binding.tvAddHabitTitle.text = "기르고 싶은 습관을 선택해 주세요."
        binding.imgAddHabitIcon.visibility = View.GONE
        super.onBackPressed()
    }

    override fun finish() {
        super.finish()
//        HabitActivity().refresh()
    }
}