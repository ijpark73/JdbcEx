package com.ijpark.jdbc.dao;

import com.ijpark.jdbc.Student;
import java.util.List;

public class JdbcDeleteUsingDao {

  public static void main(String[] args) {
		StudentDao studentDao = new StudentDao();
    Student student = new Student();
    String deleteId = "2";
    int updatedRows = studentDao.deleteStudent(deleteId);

    if(updatedRows>0) {
      System.out.println("A student is updated. id="+deleteId);
    }
    else
      System.out.println("A student is not found. id="+deleteId);

    List<Student> studentList = studentDao.listStudents();
    for(Student st:studentList)
      System.out.println(st);
  }
}
