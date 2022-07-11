package com.example.zemhabit.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zemhabit.R
import com.example.zemhabit.adapter.HabitAddListAdapter
import com.example.zemhabit.adapter.HabitRecyclerAdapter
import com.example.zemhabit.data.HabitListData

class AddHabitFragment2 : Fragment() {
    private lateinit var adapter: HabitAddListAdapter
    private lateinit var recyclerview: RecyclerView
    private var list = arrayListOf<HabitListData>()
    private var filtered_list = arrayListOf<HabitListData>()
    private lateinit var title: String
    private var src: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_habit2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title = arguments?.getString("title")!!
        src = arguments?.getInt("src")!!
        view.findViewById<AppCompatTextView>(R.id.tv_add_habit_title).text = title
        view.findViewById<ImageFilterView>(R.id.img_add_habit_icon).setImageResource(src)
        recyclerview = view.findViewById(R.id.rv_add_habit_list)

        addData()
        filterList()
        setRecyclerView()

    }

    private fun filterList() {
        for (i in 0 until list.size) {
            if (list[i].category == title) {
                filtered_list.add(list[i])
            }
        }
    }

    private fun setRecyclerView() {
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        adapter = HabitAddListAdapter(filtered_list, requireContext(), src)
        recyclerview.adapter = adapter
    }

    private fun addData() {
        list.add(HabitListData("건강한 몸", "줄넘기 100번 하기"))
        list.add(HabitListData("건강한 몸", "계단 오르기"))
        list.add(HabitListData("건강한 몸", "홈트레이닝 하기"))
        list.add(HabitListData("건강한 몸", "철봉 매달리기"))
        list.add(HabitListData("건강한 몸", "우유 3컵 마시기"))
        list.add(HabitListData("건강한 몸", "편식 안하기"))
        list.add(HabitListData("건강한 몸", "간식 조절하기"))
        list.add(HabitListData("건강한 몸", "치실 하기"))
        list.add(HabitListData("건강한 몸", "기타"))
        list.add(HabitListData("꾸준한 공부", "한글책 읽기"))
        list.add(HabitListData("꾸준한 공부", "영어책 읽기"))
        list.add(HabitListData("꾸준한 공부", "영어 단어 외우기"))
        list.add(HabitListData("꾸준한 공부", "수학 연산 풀기"))
        list.add(HabitListData("꾸준한 공부", "학원 숙제 하기"))
        list.add(HabitListData("꾸준한 공부", "공부 시간 지키기"))
        list.add(HabitListData("꾸준한 공부", "기타"))
        list.add(HabitListData("긍정적인 언어 사용", "주변 어른에게 인사 잘하기"))
        list.add(HabitListData("긍정적인 언어 사용", "친구와 고운말 쓰기"))
        list.add(HabitListData("긍정적인 언어 사용", "소리지르지 않기"))
        list.add(HabitListData("긍정적인 언어 사용", "식사 인사하기"))
        list.add(HabitListData("긍정적인 언어 사용", "문안 인사하기"))
        list.add(HabitListData("긍정적인 언어 사용", "기타"))
        list.add(HabitListData("스스로 시간 관리", "일찍 자고 일찍 일어나기"))
        list.add(HabitListData("스스로 시간 관리", "스스로 등교 준비하기"))
        list.add(HabitListData("스스로 시간 관리", "학교 5분 전에 도착하기"))
        list.add(HabitListData("스스로 시간 관리", "학원 5분 전에 도착하기"))
        list.add(HabitListData("스스로 시간 관리", "쉬는 시간 지키기"))
        list.add(HabitListData("스스로 시간 관리", "기타"))
        list.add(HabitListData("올바른 스마트폰 사용", "게임 시간 지키기"))
        list.add(HabitListData("올바른 스마트폰 사용", "동영상 시청 시간 지키기"))
        list.add(HabitListData("올바른 스마트폰 사용", "정해진 시간에 SNS 하기"))
        list.add(HabitListData("올바른 스마트폰 사용", "전화 예절 지키기"))
        list.add(HabitListData("올바른 스마트폰 사용", "가족끼리 전화 끊을때 사랑해 말하기"))
        list.add(HabitListData("올바른 스마트폰 사용", "기타"))
        list.add(HabitListData("부지런한 집안 생활", "나만의 집안일 정하기"))
        list.add(HabitListData("부지런한 집안 생활", "식사준비 돕기"))
        list.add(HabitListData("부지런한 집안 생활", "다먹은 그릇 정리하기"))
        list.add(HabitListData("부지런한 집안 생활", "책상 정리하기"))
        list.add(HabitListData("부지런한 집안 생활", "이불 정돈하기"))
        list.add(HabitListData("부지런한 집안 생활", "빨랫감 제자리 두기"))
        list.add(HabitListData("부지런한 집안 생활", "외출복 걸어 두기"))
        list.add(HabitListData("부지런한 집안 생활", "애완동물 산책시키기"))
        list.add(HabitListData("부지런한 집안 생활", "기타"))
        list.add(HabitListData("똑똑한 학교 생활", "알림장 준비물 챙기기"))
        list.add(HabitListData("똑똑한 학교 생활", "필통 챙기기"))
        list.add(HabitListData("똑똑한 학교 생활", "일기 쓰기"))
        list.add(HabitListData("똑똑한 학교 생활", "예쁘게 글씨쓰기"))
        list.add(HabitListData("똑똑한 학교 생활", "기타"))
    }
}