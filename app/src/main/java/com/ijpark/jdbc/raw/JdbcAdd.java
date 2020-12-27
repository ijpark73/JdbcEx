package com.ijpark.jdbc.raw;

import com.ijpark.jdbc.Student;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.sqlite.SQLiteDataSource;

public class JdbcAdd {

  public int addStudent(Student student) {
    
    int updatedRow = 0;
    SQLiteDataSource ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:db/jdbc.db");
    try(Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(
          "insert into student(name,dept) values(?,?)")) {
      ps.setString(1,student.getName());
      ps.setString(2,student.getDept());
      updatedRow = ps.executeUpdate();
    }catch(SQLException e){
      e.printStackTrace();
    }
    return updatedRow;
  }

  public static void main(String[] args) {
    JdbcAdd ex = new JdbcAdd();
    Student student = new Student(null,"Polly","AutoMobile");
    ex.addStudent(student);
    System.out.println("A student is added. "+student);
  }
}
