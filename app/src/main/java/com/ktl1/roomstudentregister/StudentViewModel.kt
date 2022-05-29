package com.ktl1.roomstudentregister

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ktl1.roomstudentregister.db.Student
import com.ktl1.roomstudentregister.db.StudentDao
import kotlinx.coroutines.launch

class StudentViewModel(private val dao: StudentDao) : ViewModel(){
    val students = dao.getAllStudent()

    fun insertStudent(student: Student)=viewModelScope.launch {
        dao.insertStudent(student)
    }

    fun updateStudent(student: Student)=viewModelScope.launch {
        dao.updateStudent(student)
    }

    fun deleteStudent(student: Student)=viewModelScope.launch {
        dao.deleteStudent(student)
    }


}