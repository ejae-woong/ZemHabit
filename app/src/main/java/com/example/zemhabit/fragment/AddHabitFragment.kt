package com.example.zemhabit.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zemhabit.R
import com.example.zemhabit.adapter.HabitAddBannerAdapter
import com.example.zemhabit.adapter.HabitRecyclerAdapter
import com.example.zemhabit.data.HabitBannerData
import com.example.zemhabit.data.HabitData

class AddHabitFragment : Fragment() {
    val list = arrayListOf<HabitBannerData>()
    private lateinit var adapter: HabitAddBannerAdapter
    private lateinit var rv_add_habit_banner: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_habit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_add_habit_banner = view.findViewById(R.id.rv_add_habit_banner)

        addHabitBannerData()
        setRecyclerView()

    }

    private fun addHabitBannerData() {
        list.add(HabitBannerData("건강한", "몸", R.drawable.icon_heart))
        list.add(HabitBannerData("꾸준한", "공부", R.drawable.icon_study))
        list.add(HabitBannerData("긍정적인", "언어 사용", R.drawable.icon_language))
        list.add(HabitBannerData("스스로", "시간 관리", R.drawable.icon_time))
        list.add(HabitBannerData("올바른", "스마트폰 사용", R.drawable.icon_smartphone))
        list.add(HabitBannerData("부지런한", "집안 생활", R.drawable.icon_home))
        list.add(HabitBannerData("똑똑한", "학교 생활", R.drawable.icon_backpack))
        list.add(HabitBannerData("", "기타", R.drawable.icon_etc))
        Log.e("AddHabitFragment", "DataAdded")
    }

    private fun setRecyclerView() {
        rv_add_habit_banner.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = HabitAddBannerAdapter(list, requireContext())
        rv_add_habit_banner.adapter = adapter
        Log.e("AddHabitFragment", "RecyclerView Setted")
    }
}