package com.ijpark.jdbc.raw;

import com.ijpark.jdbc.Student;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.sqlite.SQLiteDataSource;

public class JdbcList {
  public List<Student> listStudents() {
    
    List<Student> studentList = new ArrayList<>();
    SQLiteDataSource ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:db/jdbc.db");
    try(Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(
          "select id, name, dept from student")) {
      ResultSet rs = ps.executeQuery();

      while(rs.next()) {
        Student student = new Student();
        student.setId(rs.getString("id"));
        student.setName(rs.getString("name"));
        student.setDept(rs.getString("dept"));
        studentList.add(student);
      }
      rs.close();
    }catch(SQLException e){
      e.printStackTrace();
    }
    return studentList;
  }

  public static void main(String[] args) {
    List<Student> studentList = new JdbcList().listStudents();
    for(Student student:studentList)
      System.out.println(student);
  }
}
