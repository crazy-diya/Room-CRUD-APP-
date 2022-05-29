package com.ktl1.roomstudentregister.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao { // create Queries
    @Insert
    suspend fun insertStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("Select * from student_data_table")
    fun getAllStudent():LiveData<List<Student>>
}