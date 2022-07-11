package com.example.zemhabit.data

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.serialization.Serializable
import java.util.*

@Entity
data class HabitDataTable(
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
    val state: String
)

@Dao
interface HabitDataInterface {

    @Query("SELECT * FROM HabitDataTable")
    fun getAll(): List<HabitDataTable>

    @Insert
    fun insert(ExamDataTable: HabitDataTable)

    @Query("DELETE FROM HabitDataTable")
    fun deleteAll()

    @Query("SELECT * FROM HabitDataTable WHERE state = :str")
    fun getHabitState(str: String): List<HabitDataTable>
}

@Database(
    version = 2,
    entities = [HabitDataTable::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "db")
                    .fallbackToDestructiveMigration()
                    .addMigrations(AppDatabase.MIGRATION_1_2)
//                    .addMigrations(AppDatabase.MIGRATION_2_3)
                    .build()
            }.also {
                instance = it
            }
        }

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE HabitDataTable ADD habit_no INTEGER")
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE HabitDataTable ADD PRIMARY KEY, ADD PRIMARY KEY (habit_no)")
            }
        }
//
//        val MIGRATION_3_4 = object : Migration(3, 4) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE HabitDataTable ADD CONSTRAINT habit primary key (habit_no)")
//            }
//        }
    }

    abstract fun HabitDataInterface(): HabitDataInterface
}

class HabitDataRoom {

}