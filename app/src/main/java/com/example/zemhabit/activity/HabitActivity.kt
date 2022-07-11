package com.example.zemhabit.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.zemhabit.Room.RoomAccess
import com.example.zemhabit.adapter.HabitAdapter
import com.example.zemhabit.data.HabitData
import com.example.zemhabit.databinding.ActivityHabitBinding
import com.google.android.material.tabs.TabLayoutMediator
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class HabitActivity : AppCompatActivity() {
    public lateinit var binding: ActivityHabitBinding
    private val df: DateFormat = SimpleDateFormat("yyyy.MM.dd")
    private var calendar: Calendar = Calendar.getInstance()
    val habitData = ArrayList<HabitData>()
    private val tabHabitArray = arrayOf(
        "진행 중", "완료", "대기"
    )
    private val list = ArrayList<HabitData>()
    var context_main: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("HabitActivity", "onCreate")
        binding = ActivityHabitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context_main = this

//        val db = AppDatabase.getInstance(applicationContext)
//        CoroutineScope(Dispatchers.IO).launch { // 다른애 한테 일 시키기
//            val data = db.HabitDataInterface().getAll()
//            data.forEach {
//                list.add(it)
//            }
//        }

        getData()
        Log.e("HabitActivity, habitData", habitData.toString())

        binding.vpHabit.adapter = HabitAdapter(supportFragmentManager, lifecycle, habitData, this)
        TabLayoutMediator(binding.tlHabit, binding.vpHabit) { tab, position ->
            tab.text = tabHabitArray[position]
        }.attach()
//        for (i in 0..2) {
//            var state = ""
//            when (i) {
//                0 -> state = "진행"
//                1 -> state = "완료"
//                2 -> state = "대기"
//            }
//            binding.tlHabit.setNumber(i,
//                RoomAccess(applicationContext).habitGetCountByState(state).toString()
//            )
//        }
        binding.vpHabit.setUserInputEnabled(false)

        binding.btnHabitAdd.setOnClickListener{
            val intent = Intent(this, AddHabitActivity::class.java)
            startActivity(intent)
            Log.e("HabitFragment", "startIntent")
        }
    }

    public override fun onResume() {
        super.onResume()
        Log.e("HabitActivity", "onResume")
        getData()
        Log.e("HabitActivity, habitData", habitData.toString())
        binding.vpHabit.adapter = HabitAdapter(supportFragmentManager, lifecycle, habitData, this)
        for (i in 0..2) {
            var state = ""
            when (i) {
                0 -> state = "진행"
                1 -> state = "완료"
                2 -> state = "대기"
            }
            binding.tlHabit.setNumber(i,
                RoomAccess(applicationContext).habitGetCountByState(state).toString()
            )
        }
    }

    private fun getData() {
        val data = RoomAccess(applicationContext).habitGetAll()
        habitData.apply {
            for (i in 0 until data.size) {
                if (!habitData.contains(data[i])) {
                    add(data[i])
                }
            }
        }
    }
}