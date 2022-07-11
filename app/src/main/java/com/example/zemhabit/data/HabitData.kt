package com.example.zemhabit.data

import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import androidx.core.content.ContextCompat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize
import com.example.zemhabit.R
import kotlinx.serialization.Serializable
import java.time.DayOfWeek
import java.util.*

@Entity
data class HabitData(
    @PrimaryKey
    val habit_no: Int,

    @ColumnInfo(name = "CATEGORY")
    val category: String,

    @ColumnInfo(name = "TITLE")
    val title: String,

    @ColumnInfo(name = "IMAGE")
    val image: Int,

    @ColumnInfo(name = "START_DATE")
    val startDate: String,

    @ColumnInfo(name = "END_DATE")
    val endDate: String,

    @ColumnInfo(name = "DAY_OF_WEEK")
    val dayOfWeek: String,

    @ColumnInfo(name = "ALARM")
    val alarm: String,

    @ColumnInfo(name = "ZEMCON")
    val zemcon: Int,

    @ColumnInfo(name = "STATE")
    val state: String,
) : java.io.Serializable
//{
//    constructor(parcel: Parcel) : this(
//        parcel.readInt(),
//        parcel.readString()!!,
//        parcel.readString()!!,
//        parcel.readInt(),
//        parcel.readString()!!,
//        parcel.readString()!!,
//        parcel.readString()!!,
//        parcel.readString()!!,
//        parcel.readInt(),
//        parcel.readString()!!
//    ) {
//    }
//
//    override fun describeContents(): Int {
//        TODO("Not yet implemented")
//    }
//
//    override fun writeToParcel(p0: Parcel?, p1: Int) {
//        p0!!.writeString(category)
//        p0.writeString(this.title)
//        p0.writeInt(image)
//        p0.writeSerializable(startDate)
//        p0.writeSerializable(endDate)
//        p0.writeString(dayOfWeek)
//        p0.writeString(alarm)
//        p0.writeInt(zemcon)
//        p0.writeString(state)
//    }
//
//    companion object CREATOR : Parcelable.Creator<HabitData> {
//        override fun createFromParcel(parcel: Parcel): HabitData {
//            return HabitData(parcel)
//        }
//
//        override fun newArray(size: Int): Array<HabitData?> {
//            return arrayOfNulls(size)
//        }
//    }
//}

//카테고리, 이미지, 습관이름, 기간, 요일, 알림시간, 잼콘, 상태
