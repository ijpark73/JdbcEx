package com.ijpark.jdbc.raw;

import com.ijpark.jdbc.Student;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.sqlite.SQLiteDataSource;

public class JdbcUpdate {

  public int updateStudent(Student student) {
    
    int updatedRow = 0;
    SQLiteDataSource ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:db/jdbc.db");
    try(Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(
          "update student set name=?, dept=? where id=?")) {
      ps.setString(1,student.getName());
      ps.setString(2,student.getDept());
      ps.setString(3,student.getId());
      updatedRow = ps.executeUpdate();
    }catch(SQLException e){
      e.printStackTrace();
    }
    return updatedRow;
  }

  public static void main(String[] args) {
    JdbcUpdate ex = new JdbcUpdate();

    Student student = new Student("1","Johnadan","Piano");
    int updatedRow = ex.updateStudent(student);
    if (updatedRow > 0)
      System.out.println("A student is Updateed. "+student);
    else
      System.out.println("A student is not found. "+student);
  }
}
