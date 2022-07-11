package com.example.zemhabit.fragment

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.zemhabit.R
import com.example.zemhabit.activity.AddHabitActivity
import com.example.zemhabit.adapter.HabitAdapter
import com.example.zemhabit.adapter.HabitRecyclerAdapter
import com.example.zemhabit.data.HabitData
import com.example.zemhabit.data.HabitDataTable
import com.example.zemhabit.databinding.ActivityHabitBinding
import com.example.zemhabit.databinding.FragmentHabitBinding

class HabitFragment : Fragment() {
    private lateinit var binding: FragmentHabitBinding
    public lateinit var habitAdapter: HabitRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHabitBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val state = arguments?.getString("state")!!
        val list = requireArguments().getSerializable("list") as ArrayList<HabitData>
        Log.e("HabitFragment", list.toString())

        if (list.isEmpty()) {
            binding.llEmpty.visibility = VISIBLE
        } else {
            binding.llEmpty.visibility = GONE
        }
        binding.rvHabit.layoutManager = GridLayoutManager(requireContext(), 2)
        habitAdapter = HabitRecyclerAdapter(list , requireContext())
        binding.rvHabit.adapter = habitAdapter
    }
}