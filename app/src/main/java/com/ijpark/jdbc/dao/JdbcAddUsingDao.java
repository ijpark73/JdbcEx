package com.ijpark.jdbc.dao;

import com.ijpark.jdbc.Student;
import java.util.List;

public class JdbcAddUsingDao {

  public static void main(String[] args) {
		StudentDao studentDao = new StudentDao();
    Student student = new Student(null, "Park Young Bin","Donghak Middle");
    studentDao.addStudent(student);
    System.out.println("A student is added."+student);
    
		List<Student> studentList = studentDao.listStudents();
		for(Student st:studentList)
			System.out.println(st);
  }
}
