package com.ktl1.roomstudentregister

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ktl1.roomstudentregister.db.StudentDao
import java.lang.IllegalArgumentException

class StudentViewModelFactory(
    private val dao: StudentDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
            return StudentViewModel(dao) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel Class")
    }
}