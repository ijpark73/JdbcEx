package com.ijpark.jdbc.raw;

import com.ijpark.jdbc.Student;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.sqlite.SQLiteDataSource;

public class JdbcDelete {

  public int deleteStudent(String id) {
    
    int updatedRow = 0;
    SQLiteDataSource ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:db/jdbc.db");
    try(Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(
          "delete from student where id=?")) {
      ps.setString(1,id);
      updatedRow = ps.executeUpdate();
    }catch(SQLException e){
      e.printStackTrace();
    }
    return updatedRow;
  }

  public static void main(String[] args) {
    JdbcDelete ex = new JdbcDelete();

    String deleteId="1";
    int updatedRow = ex.deleteStudent(deleteId);
    if (updatedRow > 0)
      System.out.println("A student is deleted. "+deleteId);
    else
      System.out.println("A student is not found. "+deleteId);
  }
}
