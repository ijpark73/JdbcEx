package com.ijpark.jdbc.dao;

import com.ijpark.jdbc.Student;
import java.util.List;

public class JdbcGetUsingDao {

  public static void main(String[] args) {
		StudentDao studentDao = new StudentDao();
    Student student = studentDao.getStudent("4");
	  System.out.println(student);
  }
}
