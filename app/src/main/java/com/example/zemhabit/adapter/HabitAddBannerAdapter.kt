package com.example.zemhabit.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.recyclerview.widget.RecyclerView
import com.example.zemhabit.R
import com.example.zemhabit.activity.AddHabitActivity
import com.example.zemhabit.data.HabitBannerData
import com.example.zemhabit.fragment.AddHabitFragment2
import com.example.zemhabit.fragment.HabitFragment

class HabitAddBannerAdapter(private val list: ArrayList<HabitBannerData>, val context: Context) :
    RecyclerView.Adapter<HabitAddBannerAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val view = v

        fun bind(item: HabitBannerData, context: Context) {
            view.findViewById<AppCompatTextView>(R.id.tv_habit_description).text = item.description
            view.findViewById<AppCompatTextView>(R.id.tv_habit_title).text = item.title
            view.findViewById<ImageFilterView>(R.id.img_habit_icon).setImageResource(item.src)

            view.setOnClickListener{
                val appCompatActivity = context as AddHabitActivity
                appCompatActivity.supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_add_habit, AddHabitFragment2().apply {
                        arguments = Bundle().apply {
                            putString("title", "${item.description} ${item.title}")
                            putInt("src", item.src)
                        }
                    })
                    .addToBackStack(null)
                    .commit()
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HabitAddBannerAdapter.ViewHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_add_habit, parent, false)

        return HabitAddBannerAdapter.ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: HabitAddBannerAdapter.ViewHolder, position: Int) {
        holder.bind(list[position], context)
    }

    override fun getItemCount(): Int = list.size

}