package com.example.zemhabit.Room

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.zemhabit.data.HabitData

class RoomAccess(context: Context) {
//    constructor(context: RecyclerView.Adapter<FoodAdapter.ViewHolder>) : this(IntroActivity())

    val habitDB = Room.databaseBuilder(context, AppDatabase::class.java, "habitDB").build()

    fun habitInsert(data: HabitData) {
        val insertTread = Thread(Runnable {
            habitDB.HabitDataDao().insertAll(data)
        })
        insertTread.start()
        habitDB.close()
    }

    fun habitGetAll(): ArrayList<HabitData> {
        val habitDatas = ArrayList<HabitData>()
        var data : List<HabitData>
        val getAllThread = Thread(Runnable {
            data = habitDB.HabitDataDao().getAll()
            data.forEach {
                habitDatas.add(it)
                Log.e("HabitGetAll ADDED", it.toString())
            }
            Log.e("habitGetAll", habitDatas.toString())
        })
        getAllThread.start()
        getAllThread.join()
        habitDB.close()
        return habitDatas
    }

    fun habitGetCount(): Int {
        var count: Int = 0
        val countThread = Thread(Runnable {
            count = habitDB.HabitDataDao().getCount()
        })
        countThread.start()
        countThread.join()
        habitDB.close()
        return count
    }

    fun habitGetCountByState(state: String): Int{
        var count: Int = 0
        val countThread = Thread(Runnable {
            count = habitDB.HabitDataDao().getCountByState(state)
        })
        countThread.start()
        countThread.join()
        habitDB.close()
        return count
    }

    fun habitGetById(id: Int): HabitData {
        var data: HabitData? = null
        val getThread = Thread(Runnable {
            data = habitDB.HabitDataDao().getById(id)
        })
        getThread.start()
        getThread.join()
        habitDB.close()
        return data!!
    }

    fun habitGetAllById(items: ArrayList<Int>): ArrayList<HabitData> {
        var habitData: ArrayList<HabitData>? = null
        val getAllThread = Thread(Runnable {
            habitData = habitDB.HabitDataDao().getAllById(items)as ArrayList<HabitData>
        })
        getAllThread.start()
        getAllThread.join()
        habitDB.close()
        return habitData!!
    }

    fun getByState(state: String): ArrayList<HabitData> {
        var habitData: ArrayList<HabitData>? = null
        val getByTypeThread = Thread(Runnable {
            habitData = habitDB.HabitDataDao().getByState(state) as ArrayList<HabitData>
        })
        getByTypeThread.start()
        getByTypeThread.join()
        habitDB.close()
        return habitData!!
    }

    fun deleteAll() {
        val deleteAllThread = Thread(Runnable {
            habitDB.HabitDataDao().deleteAll()
        })
        deleteAllThread.start()
        deleteAllThread.join()
        habitDB.close()
    }

    fun deleteById(item_no: Int) {
        val deleteByIdThread = Thread(Runnable {
            habitDB.HabitDataDao().deleteById(item_no)
        })
        deleteByIdThread.start()
        deleteByIdThread.join()
        habitDB.close()
    }

    fun updateTypeToProceedById(item_no: Int) {
        val updateTypeToProceedByIdThread = Thread(Runnable {
            habitDB.HabitDataDao().updateTypeToProceedById(item_no)
        })
        updateTypeToProceedByIdThread.start()
        updateTypeToProceedByIdThread.join()
        habitDB.close()
    }

    fun updateState(state: String, item_no: Int) {
        val updateStateThread = Thread(Runnable {
            habitDB.HabitDataDao().updateState(state, item_no)
        })
        updateStateThread.start()
        updateStateThread.join()
        habitDB.close()
    }

    fun updateById(item_no: Int, start_date: String, end_date: String, doy: String, alarm: String, zemcon_count: String) {
        val updateByIdThread = Thread(Runnable {
            habitDB.HabitDataDao().updateById(item_no, start_date, end_date, doy, alarm, zemcon_count)
        })
        updateByIdThread.start()
        updateByIdThread.join()
        habitDB.close()
    }
}
