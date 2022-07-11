package com.example.zemhabit.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.*
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.fragment.app.Fragment
import com.example.zemhabit.R
import com.example.zemhabit.Room.RoomAccess
import com.example.zemhabit.activity.AddHabitActivity
import com.example.zemhabit.activity.HabitActivity
import com.example.zemhabit.data.DayPickerData
import com.example.zemhabit.data.HabitData
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random


class AddHabitFragment3 : Fragment() {
    private var src: Int = 0
    private var calendar: Calendar = Calendar.getInstance()
    private val df: DateFormat = SimpleDateFormat("yyyy.MM.dd")
    private var mYear: Int = 0
    private var mMonth: Int = 0
    private var mDate: Int = 0
    private var dayArray = arrayListOf<DayPickerData>()
    private var dayStr = ""
    private var readDay = ""
    private var checkArray = Array(7) { i -> false }
    private val AMPM = arrayOf("오전", "오후")
    private var zems = 1
    private var duration = 13L
    private val list = ArrayList<HabitData>()
    private lateinit var content: String
    private lateinit var category: String
    private lateinit var startDate: AppCompatTextView
    private lateinit var endDate: AppCompatTextView
    private lateinit var radio1: RadioButton
    private lateinit var radio2: RadioButton
    private lateinit var radio3: RadioButton
    private lateinit var radio4: RadioButton
    private lateinit var alldayToggle: AppCompatToggleButton
    private lateinit var dayPicker: AppCompatTextView
    private lateinit var editName: AppCompatEditText
    private lateinit var datepicker: RadioGroup
    private lateinit var alarmSwitch: SwitchCompat
    private lateinit var alarmText: AppCompatTextView
    private lateinit var zemconQtt: AppCompatTextView
    private lateinit var zemconPlus: ImageFilterView
    private lateinit var zemconMinus: ImageFilterView
    private lateinit var zemconText: AppCompatTextView
    private lateinit var cancelBtn: AppCompatButton
    private lateinit var addBtn: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.example.zemhabit.R.layout.fragment_add_habit3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initBundle()
        setTitle()
        initView(view)

        startDate.setOnClickListener {
            (datePicker(it, startDate, "시작일"))
        }

        endDate.setOnClickListener {
            (datePicker(it, endDate, "종료일"))
        }


        datepicker.setOnCheckedChangeListener { group, checkedId ->
            radioPicker(checkedId)
        }

        dayPicker.setOnClickListener {
            dayPicker(it)
        }

//        alarmSwitch.setOnClickListener {
//            setAlarm(it)
//        }

        alarmSwitch.setOnCheckedChangeListener { it, isChecked ->
            if (isChecked) {
                setAlarm(it)
            } else {
                alarmText.text = "없음"
            }
        }

        zemconPlus.setOnClickListener {
            zemPlusMinus(++zems)
        }

        zemconMinus.setOnClickListener {
            zemPlusMinus(--zems)
        }

        cancelBtn.setOnClickListener {
            cancelHabit(it)
        }

        addBtn.setOnClickListener{
            val layout =
                layoutInflater.inflate(com.example.zemhabit.R.layout.dialog_add_habit, null)
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
                val num = RoomAccess(requireContext()).habitGetCount()+1
                val data = HabitData(num,category, content, src, startDate.text.toString(),endDate.text.toString(), readDay, alarmText.text.toString(), zems*5, "등록 대기")
//                val db = AppDatabase.getInstance(view.context)
//                CoroutineScope(Dispatchers.IO).launch { // 다른애 한테 일 시키기
//                    db.HabitDataInterface().insert(data)
//                }
                RoomAccess(requireContext()).habitInsert(data)
                Log.e("Inserted Data", data.toString())
                dialog.cancel()
                (activity as AddHabitActivity).finish()

//                (activity as HabitActivity).refresh()

//                HabitFragment().habitAdapter.notifyDataSetChanged()
//                HabitActivity().binding.vpHabit.adapter!!.notifyDataSetChanged()
            }
        }
    }



    private fun initBundle() {
        content = arguments?.getString("content")!!
        category = arguments?.getString("category")!!
        src = arguments?.getInt("src")!!
    }

    private fun setTitle() {
        val appCompatActivity = context as AddHabitActivity
        appCompatActivity.findViewById<AppCompatTextView>(com.example.zemhabit.R.id.tv_add_habit_title).text =
            category
        appCompatActivity.findViewById<ImageFilterView>(com.example.zemhabit.R.id.img_add_habit_icon)
            .setImageResource(src)
        appCompatActivity.findViewById<ImageFilterView>(com.example.zemhabit.R.id.img_add_habit_icon).visibility =
            View.VISIBLE
    }

    private fun initView(view: View) {
        editName = view.findViewById(com.example.zemhabit.R.id.edit_habit_name)
        datepicker = view.findViewById(R.id.rg_datepicker)
        startDate = view.findViewById(com.example.zemhabit.R.id.tv_habit_start_date)
        endDate = view.findViewById(com.example.zemhabit.R.id.tv_habit_end_date)
        radio1 = view.findViewById(R.id.radio1)
        radio2 = view.findViewById(R.id.radio2)
        radio3 = view.findViewById(R.id.radio3)
        radio4 = view.findViewById(R.id.radio4)
        dayPicker = view.findViewById(R.id.edit_habit_day)
        alarmSwitch = view.findViewById(R.id.switch_habit_alarm)
        alarmText = view.findViewById(R.id.edit_habit_alarm)
        zemconQtt = view.findViewById(R.id.edit_add_habit_zemcon)
        zemconPlus = view.findViewById(R.id.btn_add_habit_zemplus)
        zemconMinus = view.findViewById(R.id.btn_add_habit_zemminus)
        zemconText = view.findViewById(R.id.tv_add_habit_zemcon_desc)
        addBtn = view.findViewById(R.id.btn_add_habit)
        cancelBtn = view.findViewById(R.id.btn_add_habit_cancel)

        editName.hint = content
        editName.setText(content)

        startDate.text = df.format(calendar.time)
        radio2.isChecked = true

        calendar.add(Calendar.DATE, 13)

        endDate.text = df.format(calendar.time)
        editZemconText()

        readDay = dayPicker.text.toString()
    }

    private fun getDaysInMonth(month: Int, year: Int): Int {
        return when (month - 1) {
            Calendar.JANUARY, Calendar.MARCH, Calendar.MAY, Calendar.JULY, Calendar.AUGUST, Calendar.OCTOBER, Calendar.DECEMBER -> 31
            Calendar.APRIL, Calendar.JUNE, Calendar.SEPTEMBER, Calendar.NOVEMBER -> 30
            Calendar.FEBRUARY -> if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) 29 else 28 // 윤년계산
            else -> throw IllegalArgumentException("Invalid Month")
        }
    }

    private fun datePicker(it: View, tv: AppCompatTextView, str: String) {
        val layout = layoutInflater.inflate(com.example.zemhabit.R.layout.dialog_datepicker, null)
        layout.findViewById<AppCompatTextView>(com.example.zemhabit.R.id.tv_datepicker).text = str
        val build = AlertDialog.Builder(it.context).apply {
            setView(layout)
        }
        val dialog = build.create()
        dialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.show()

        calendar.time = df.parse(tv.text as String)
        val np_year = layout.findViewById<NumberPicker>(com.example.zemhabit.R.id.np_year)
        val np_month = layout.findViewById<NumberPicker>(com.example.zemhabit.R.id.np_month)
        val np_date = layout.findViewById<NumberPicker>(com.example.zemhabit.R.id.np_date)
        val year = Calendar.getInstance().get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val date = calendar.get(Calendar.DATE)

        np_year.minValue = year
        np_year.maxValue = year + 2
        np_month.minValue = 1
        np_month.maxValue = 12
        np_month.value = month + 1
        np_date.minValue = 1
        np_date.maxValue = getDaysInMonth(month, year)
        np_date.value = date
        np_month.wrapSelectorWheel = false
        np_date.wrapSelectorWheel = false
        np_month.setFormatter(NumberPicker.Formatter { i -> String.format("%02d", i) })
        np_date.setFormatter(NumberPicker.Formatter { i -> String.format("%02d", i) })

        np_month.setOnValueChangedListener { numberPicker, oldVal, newVal ->
            np_date.maxValue = getDaysInMonth(newVal, year)
        }

        layout.findViewById<AppCompatButton>(com.example.zemhabit.R.id.btn_setdate)
            .setOnClickListener {
                mYear = np_year.value
                mMonth = np_month.value - 1
                mDate = np_date.value
                calendar.set(mYear, mMonth, mDate)
                val compareCalendar = df.parse(startDate.text as String)
                if (compareCalendar.compareTo(calendar.time) == 1) {
                    val layout_ =
                        layoutInflater.inflate(com.example.zemhabit.R.layout.dialog_alert, null)
                    val build_ = AlertDialog.Builder(it.context).apply {
                        setView(layout_)
                    }
                    val dialog_ = build_.create()
                    dialog_.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
                    dialog_.show()
                    layout_.findViewById<AppCompatButton>(R.id.btn_alert).setOnClickListener {
                        dialog_.cancel()
                    }
                } else {
                    tv.text = df.format(calendar.time)
                    dialog.cancel()
                    editZemconText()
                    when (duration) {
                        6L -> radio1.isChecked = true
                        13L -> radio2.isChecked = true
                        29L -> radio3.isChecked = true
                        99L -> radio4.isChecked = true
                        else -> {
                            radio1.isChecked = false
                            radio2.isChecked = false
                            radio3.isChecked = false
                            radio4.isChecked = false
                        }
                    }
                }
            }

        layout.findViewById<AppCompatButton>(com.example.zemhabit.R.id.btn_cancel)
            .setOnClickListener {
                dialog.cancel()
                editZemconText()
            }
    }

    private fun radioPicker(checkedId: Int) {
        val endCalendar: Calendar = Calendar.getInstance()

        when (checkedId) {
            R.id.radio1 -> duration = 6
            R.id.radio2 -> duration = 13
            R.id.radio3 -> duration = 29
            R.id.radio4 -> duration = 99
        }
        endCalendar.time = df.parse(startDate.text as String)!!
        endCalendar.add(Calendar.DATE, duration.toInt())
        val str = df.format(endCalendar.time)

        endDate.text = str
        editZemconText()
    }

    private fun toggleClicked(v: CompoundButton, i: Int, isChecked: Boolean, day: String) {
        dayArray[i].checked = isChecked
        alldayToggle.isChecked = false
        checkArray[i] = dayArray[i].checked
        if (checkAllTrue(checkArray)) {
            allDayChecked()
        }
    }

    private fun allDayChecked() {
        dayArray.forEach {
            it.checked = false
            it.toggle.isChecked = false
            checkArray[it.num] = false
        }
        alldayToggle.isChecked = true
//        dayCnt = 0
        dayStr = "매일"
    }

    private fun checkAllTrue(array: Array<Boolean>): Boolean {
        for (b in array) if (!b) return false
        return true
    }

    private fun dayPicker(it: View) {
        val layout =
            layoutInflater.inflate(com.example.zemhabit.R.layout.dialog_daypicker, null)
        val build = AlertDialog.Builder(it.context).apply {
            setView(layout)
        }
        val dialog = build.create()
        dialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.show()

        dayArray.add(DayPickerData(false, "월", layout.findViewById(R.id.day1), 0))
        dayArray.add(DayPickerData(false, "화", layout.findViewById(R.id.day2), 1))
        dayArray.add(DayPickerData(false, "수", layout.findViewById(R.id.day3), 2))
        dayArray.add(DayPickerData(false, "목", layout.findViewById(R.id.day4), 3))
        dayArray.add(DayPickerData(false, "금", layout.findViewById(R.id.day5), 4))
        dayArray.add(DayPickerData(false, "토", layout.findViewById(R.id.day6), 5))
        dayArray.add(DayPickerData(false, "일", layout.findViewById(R.id.day7), 6))
        alldayToggle = layout.findViewById(R.id.btn_allday)

        readDay = dayPicker.text.toString()
        Log.e("READDAY", readDay)
        if (readDay == "매일") {
            alldayToggle.isChecked = true
        }

//            dayCnt = 0
//            dayCnt_ = 0
        dayArray.forEach {
            it.toggle.setOnCheckedChangeListener { button, isChecked ->
                toggleClicked(
                    button,
                    it.num,
                    isChecked,
                    it.day
                )
            }
            if (readDay.contains(it.day) && readDay != "매일") {
                it.toggle.isChecked = true
//                    dayCnt_++
                Log.e("CONTAINS", it.day)
            }
        }

        alldayToggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                allDayChecked()
            }
        }

        layout.findViewById<AppCompatButton>(R.id.btn_setday).setOnClickListener {
            var i = 0
            if (alldayToggle.isChecked) {
                dayStr = "매일"
            } else
                dayArray.forEach {
                    if (it.checked) {
                        if (i == 0) {
                            dayStr = it.day
                            i++
                        } else {
                            dayStr += ", ${it.day}"
                        }
                    }
                    it.checked = false
                }
            Log.e("I", i.toString())
            dayPicker.text = dayStr
            dialog.cancel()
//                dayCnt = 0
        }

        layout.findViewById<AppCompatButton>(com.example.zemhabit.R.id.btn_cancel)
            .setOnClickListener {
                dialog.cancel()
            }
    }

    private fun setAlarm(it: View) {
        val layout =
            layoutInflater.inflate(com.example.zemhabit.R.layout.dialog_alarm, null)
        val build = AlertDialog.Builder(it.context).apply {
            setView(layout)
        }
        val dialog = build.create()
        dialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.show()

        val np_ampm = layout.findViewById<NumberPicker>(R.id.np_ampm)
        val np_hour = layout.findViewById<NumberPicker>(R.id.np_hour)
        val np_minute = layout.findViewById<NumberPicker>(R.id.np_minute)

        np_ampm.minValue = 0
        np_ampm.maxValue = 1
        np_ampm.wrapSelectorWheel = false
        np_ampm.displayedValues = AMPM
        np_hour.minValue = 1
        np_hour.maxValue = 12
        np_minute.minValue = 0
        np_minute.maxValue = 59
        np_hour.setFormatter(NumberPicker.Formatter { i -> String.format("%02d", i) })
        np_minute.setFormatter(NumberPicker.Formatter { i -> String.format("%02d", i) })

        layout.findViewById<AppCompatButton>(R.id.btn_setalarm).setOnClickListener{
            alarmText.text = "${np_ampm.displayedValues[np_ampm.value]} " +
                    "${String.format("%02d", np_hour.value)}시 ${String.format("%02d", np_minute.value)}분"
            dialog.cancel()
        }

        layout.findViewById<AppCompatButton>(R.id.btn_cancel).setOnClickListener{
            alarmSwitch.isChecked = false
            dialog.cancel()
        }
    }

    private fun zemPlusMinus(zem: Int) {
        zems = if (zem > 10) {
            10
        }else if (zem < 0) {
            0
        }else {
            zem
        }
        zemconQtt.text = "${zems*5}"
        editZemconText()
    }

    private fun editZemconText() {
        duration =
            (df.parse(endDate.text as String)!!.time - df.parse(startDate.text as String)!!.time) / (24 * 60 * 60 * 1000)
        zemconText.text = "빠짐없이 한다면 ${duration+1}회 x ${zems*5}잼콘 = 최대 ${(duration+1)*(zems*5)}잼콘"
    }

    public fun cancelHabit(it: View) {
        val layout =
            layoutInflater.inflate(com.example.zemhabit.R.layout.dialog_cancel, null)
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
            dialog.cancel()
            requireActivity().onBackPressed()
        }
    }
}