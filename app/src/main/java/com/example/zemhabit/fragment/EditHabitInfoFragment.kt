package com.example.zemhabit.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.example.zemhabit.R
import com.example.zemhabit.Room.RoomAccess
import com.example.zemhabit.activity.AddHabitActivity
import com.example.zemhabit.activity.EditHabitActivity
import com.example.zemhabit.data.HabitData
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EditHabitInfoFragment : Fragment() {
    private lateinit var btn_submit: AppCompatButton
    private lateinit var btn_nono: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_habit_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val habit = arguments?.getSerializable("habit") as HabitData
        initView(view, habit)

        btn_submit.setOnClickListener {
            val layout =
                layoutInflater.inflate(com.example.zemhabit.R.layout.dialog_submit_habit, null)
            val build = AlertDialog.Builder(it.context).apply {
                setView(layout)
            }
            val dialog = build.create()
            dialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
            dialog.show()

            dialog.findViewById<AppCompatTextView>(R.id.tv_submit_habit)!!.text = "'${habit.state}' 습관을 승인할까요?"
            layout.findViewById<AppCompatButton>(R.id.btn_cancel).setOnClickListener {
                dialog.cancel()
            }

            layout.findViewById<AppCompatButton>(R.id.btn_admit).setOnClickListener {
                RoomAccess(view.context).updateState("진행중", habit.habit_no)
                dialog.cancel()
                (activity as EditHabitActivity).finish()
            }
        }

        btn_nono.setOnClickListener {
            val layout =
                layoutInflater.inflate(com.example.zemhabit.R.layout.dialog_habit_nono, null)
            val build = AlertDialog.Builder(it.context).apply {
                setView(layout)
            }
            val dialog = build.create()
            dialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
            dialog.show()
            layout.findViewById<AppCompatButton>(R.id.btn_cancel).setOnClickListener {
                dialog.cancel()
            }

            layout.findViewById<AppCompatButton>(R.id.btn_admit).setOnClickListener {
                RoomAccess(view.context).deleteById(habit.habit_no)
                dialog.cancel()
                (activity as EditHabitActivity).finish()
            }
        }
    }

    private fun initView(view: View, habit: HabitData) {
        view.findViewById<AppCompatTextView>(R.id.tv_edit_habit_info_title).text = habit.title
        view.findViewById<AppCompatTextView>(R.id.tv_edit_habit_info_duration).text =
            "${habit.startDate} ~ ${habit.endDate}"
        view.findViewById<AppCompatTextView>(R.id.tv_edit_habit_info_dayOfWeek).text =
            habit.dayOfWeek
        view.findViewById<AppCompatTextView>(R.id.tv_edit_habit_info_alarm).text = habit.alarm
        view.findViewById<AppCompatTextView>(R.id.tv_edit_habit_info_zemcon).text =
            "${habit.zemcon * 5}"
        btn_submit = view.findViewById(R.id.btn_submit_habit)
        btn_nono = view.findViewById(R.id.btn_submit_habit_nono)

        if (!habit.state.contains("진행")) {
            val appCompatActivity = context as EditHabitActivity
            appCompatActivity.findViewById<AppCompatButton>(R.id.btn_edit_habit).visibility = GONE
            appCompatActivity.findViewById<AppCompatButton>(R.id.btn_edit_habit_trash).visibility =
                GONE
        }
    }
}