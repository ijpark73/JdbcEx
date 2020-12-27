package com.ijpark.jdbc.dao;

import com.ijpark.jdbc.Student;
import java.util.List;

public class JdbcUpdateUsingDao {

  public static void main(String[] args) {
		StudentDao studentDao = new StudentDao();
    Student student = new Student("2", "Park Young Bin","Donghak Middle");
    int updatedRows = studentDao.updateStudent(student);

    if(updatedRows>0) {
      System.out.println("A student is updated. "+student);
      System.out.println(studentDao.getStudent("2"));
    }
    else
      System.out.println("A student is not found."+student);
  }
}
