package com.ijpark.jdbc.raw;

import com.ijpark.jdbc.Student;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.sqlite.SQLiteDataSource;

public class JdbcGet {

  public Student getStudent(String id) {
    
    Student student = null;
    SQLiteDataSource ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:db/jdbc.db");
    try(Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(
          "select id, name, dept from student where id=?")) {
      ps.setString(1,id);
      ResultSet rs = ps.executeQuery();

      if(rs.next()) {
        student = new Student();
        student.setId(rs.getString("id"));
        student.setName(rs.getString("name"));
        student.setDept(rs.getString("dept"));
      }
      rs.close();
    }catch(SQLException e){
      e.printStackTrace();
    }
    return student;
  }

  public static void main(String[] args) {
    Student student = new JdbcGet().getStudent("4");
    System.out.println(student);
  }
}
