package com.example.zemhabit.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zemhabit.R
import com.example.zemhabit.activity.AddHabitActivity
import com.example.zemhabit.data.HabitListData
import com.example.zemhabit.fragment.AddHabitFragment2
import com.example.zemhabit.fragment.AddHabitFragment3

class HabitAddListAdapter(private val list: ArrayList<HabitListData>, val context: Context, val src: Int) :
    RecyclerView.Adapter<HabitAddListAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun bind(item: HabitListData, context: Context, src: Int) {
            view.findViewById<AppCompatTextView>(R.id.tv_add_habit_list).text = item.content

            view.setOnClickListener{
                val appCompatActivity = context as AddHabitActivity
                appCompatActivity.supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_add_habit, AddHabitFragment3().apply {
                        arguments = Bundle().apply {
                            putString("category", item.category)
                            putString("content", item.content)
                            putInt("src", src)
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
    ): HabitAddListAdapter.ViewHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_add_habit_list, parent, false)

        return HabitAddListAdapter.ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: HabitAddListAdapter.ViewHolder, position: Int) {
        holder.bind(list[position], context, src)
    }

    override fun getItemCount(): Int = list.size

}