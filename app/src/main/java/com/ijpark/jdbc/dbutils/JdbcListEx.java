package com.ijpark.jdbc.dbutils;

import com.ijpark.jdbc.Student;
import java.util.List;

public class JdbcListEx {

  public static void main(String[] args) {
		StudentDao studentDao = new StudentDao();
		List<Student> studentList = studentDao.listStudents();
		for(Student student:studentList)
			System.out.println(student);
  }
}
