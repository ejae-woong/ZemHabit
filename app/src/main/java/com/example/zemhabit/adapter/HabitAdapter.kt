package com.example.zemhabit.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.zemhabit.R
import com.example.zemhabit.data.HabitData
import com.example.zemhabit.data.HabitDataTable
import com.example.zemhabit.fragment.HabitFragment

class HabitAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, val list: ArrayList<HabitData>, val context: Context) :
    FragmentStateAdapter(fragmentManager, lifecycle){

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        var state = ""
        val bucketList = ArrayList<HabitData>()

        when (position) {
            0 -> state = "진행"
            1 -> state = "완료"
            2 -> state = "대기"
        }
        list.forEach {
            if (it.state.contains(state)) {
                bucketList.add(it)
            }
        }

        val habitFragment = HabitFragment().apply {
            arguments = Bundle().apply {
                putString("state", state)
                putSerializable("list", bucketList)
            }
        }
        return habitFragment
    }


}