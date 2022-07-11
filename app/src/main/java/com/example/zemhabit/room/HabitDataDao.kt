package com.example.zemhabit.Room

import androidx.room.*
import com.example.zemhabit.data.HabitData
import kotlin.collections.ArrayList

@Dao
interface HabitDataDao {

    @Query("SELECT * FROM HabitData ORDER BY habit_no")
    fun getAll(): List<HabitData>

    @Query("SELECT * FROM HabitData WHERE habit_no = (:item_no)")
    fun getById(item_no: Int): HabitData

    @Query("SELECT * FROM HabitData WHERE habit_no IN (:items)")
    fun getAllById(items: List<Int>): List<HabitData>

    @Query("SELECT COUNT(*) FROM HabitData")
    fun getCount(): Int

    @Query("SELECT * FROM HabitData WHERE state LIKE '%' || :state || '%'")
    fun getByState(state: String): List<HabitData>

    @Query("SELECT COUNT(*) FROM HabitData WHERE state LIKE '%' || :state || '%'")
    fun getCountByState(state: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: HabitData)

    @Delete
    fun delete(user: HabitData)

    @Query("DELETE FROM HabitData")
    fun deleteAll()

    @Query("DELETE FROM HabitData WHERE habit_no = (:item_no)")
    fun deleteById(item_no: Int)

    @Query("UPDATE HabitData SET state = '진행중' WHERE habit_no = (:item_no)")
    fun updateTypeToProceedById(item_no: Int)

    @Query("UPDATE HabitData SET state = :state WHERE habit_no = (:item_no)")
    fun updateState(state: String, item_no: Int)

    @Query("UPDATE HabitData SET zemcon = (:zemcon_count), start_date = (:start_date), end_date = (:end_date), day_of_week = (:doy), alarm = (:alarm), state = '수정대기' WHERE habit_no = (:item_no)")
    fun updateById(item_no: Int, start_date: String, end_date: String, doy: String, alarm: String, zemcon_count: String)
}