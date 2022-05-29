package com.ktl1.roomstudentregister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ktl1.roomstudentregister.db.Student
import com.ktl1.roomstudentregister.db.StudentDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var saveBut: Button
    private lateinit var clearBut: Button

    private lateinit var viewModel: StudentViewModel
    private lateinit var studentRecyclerView: RecyclerView
    private lateinit var adapter: StudentRecyclerViewAdapter

    private lateinit var selectedStudent: Student
    private var isListItemClicked = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name = findViewById(R.id.studentName)
        email = findViewById(R.id.studentEmail)
        saveBut = findViewById(R.id.save)
        clearBut = findViewById(R.id.clear)
        studentRecyclerView = findViewById(R.id.recyclerView)

        val dao = StudentDatabase.getInstance(application).studentDao()
        val factory = StudentViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory).get(StudentViewModel::class.java)

        saveBut.setOnClickListener {
            if(isListItemClicked){
                updateStudentData()
                clearInput()
            }else{
            saveStudentData()
            clearInput()}
        }

        clearBut.setOnClickListener {
            if (isListItemClicked){
                deleteStudentData()
                clearInput()
            }else{
            clearInput()}
        }

        initRecyclerView()
    }

    private fun saveStudentData() {
//        val name = name.text.toString()
//        val email = email.text.toString()
//        val student = Student(0,name,email)
//        viewModel.insertStudent(student)

        viewModel.insertStudent(
            Student(
                0,
                name.text.toString(),
                email.text.toString()
            )
        )
    }

    private fun updateStudentData(){
        viewModel.updateStudent(
            Student(
                selectedStudent.id,
                name.text.toString(),
                email.text.toString()
            )
        )
        saveBut.text = "Save"
        clearBut.text = "Clear"
        isListItemClicked = false
    }
    private fun deleteStudentData(){
        viewModel.deleteStudent(
            Student(
                selectedStudent.id,
                name.text.toString(),
                email.text.toString()
            )
        )
        saveBut.text = "Save"
        clearBut.text = "Clear"
        isListItemClicked = false
    }

    private fun clearInput() {
        name.text.clear()
        email.text.clear()
    }

    private fun initRecyclerView() {
        studentRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentRecyclerViewAdapter { selectedItem: Student ->
            listClickEvent(selectedItem)
        }
        studentRecyclerView.adapter = adapter

        displayStudentList()

    }

    private fun displayStudentList() {
        viewModel.students.observe(this) {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun listClickEvent(student: Student) {
//        Toast.makeText(this,"Student name is ${student.name}",Toast.LENGTH_SHORT).show()

        selectedStudent = student
        saveBut.text = "Update"
        clearBut.text = "Delete"
        isListItemClicked = true

        name.setText(selectedStudent.name)
        email.setText(selectedStudent.email)

    }
}