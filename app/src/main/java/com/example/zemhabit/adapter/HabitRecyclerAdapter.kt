package com.example.zemhabit.adapter

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.example.zemhabit.R
import com.example.zemhabit.activity.AddHabitActivity
import com.example.zemhabit.activity.EditHabitActivity
import com.example.zemhabit.data.HabitData
import com.example.zemhabit.data.HabitDataTable
import java.io.Serializable
import java.text.DateFormat
import java.text.SimpleDateFormat

class HabitRecyclerAdapter(private val list: ArrayList<HabitData>, val context: Context) :
    RecyclerView.Adapter<HabitRecyclerAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        private val df: DateFormat = SimpleDateFormat("yyyy.MM.dd")

        fun bind(item: HabitData, context: Context) {
            view.findViewById<AppCompatTextView>(R.id.tv_habit_title).text = item.title
            view.findViewById<AppCompatTextView>(R.id.tv_habit_dayofweek).text = item.dayOfWeek
            view.findViewById<AppCompatTextView>(R.id.tv_habit_term).text = item.startDate
            view.findViewById<ImageFilterView>(R.id.img_habit_icon).setImageResource(item.image)
            view.findViewById<AppCompatTextView>(R.id.tv_habit_state).text = item.state
//            if (item.state.equals("완료 요청")) {
//                view.findViewById<ConstraintLayout>(R.id.row_habit_background).background = R.drawable.background_habit_selected.toDrawable()
//            }

            view.setOnClickListener{
                val intent = Intent(context, EditHabitActivity::class.java)
                intent.putExtra("item", item)
//                intent.apply {
//                    putExtra("habit_no", item.habit_no)
//                    putExtra("category", item.category)
//                    putExtra("title", item.title)
//                    putExtra("image", item.image)
//                    putExtra("startDate", item.startDate)
//                    putExtra("endDate", item.endDate)
//                    putExtra("dayOfWeek", item.dayOfWeek)
//                    putExtra("alarm", item.alarm)
//                    putExtra("zemcon", item.zemcon)
//                    putExtra("state", item.state)
//                }
                startActivity(context, intent, null)
                Log.e("HabitRecycler", "Start Activity")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitRecyclerAdapter.ViewHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_habit, parent, false)

        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: HabitRecyclerAdapter.ViewHolder, position: Int) {
        holder.bind(list[position], context)
    }

    override fun getItemCount(): Int = list.size


}