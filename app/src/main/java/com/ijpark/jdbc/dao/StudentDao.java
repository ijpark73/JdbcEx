package com.ijpark.jdbc.dao;

import com.ijpark.jdbc.Student;
import com.ijpark.jdbc.DataAccessException;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.sqlite.SQLiteDataSource;

public class StudentDao {

  private SQLiteDataSource ds = null;

  private final String LIST_STUDENTS = "select id,name,dept from student";
  private final String GET_STUDENT = "select id,name,dept from student where id=?";
  private final String ADD_STUDENT = "insert into student(name,dept) values(?,?)";
  private final String UPDATE_STUDENT = "update student set name=?,dept=? where id=?";
  private final String DELETE_STUDENT = "delete from student where id=?";

  public StudentDao() {
    ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:db/jdbc.db");
  }

  public List<Student> listStudents() throws DataAccessException {
    List<Student> students = new ArrayList<>();

    try(Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(LIST_STUDENTS)) {

      ResultSet rs = ps.executeQuery();
      while(rs.next()){
        Student student = new Student();
        student.setId(rs.getString("id"));
        student.setName(rs.getString("name"));
        student.setDept(rs.getString("dept"));
        students.add(student);
      }
      rs.close(); 
      return students;
    } catch(SQLException e) {
      e.printStackTrace();
      throw new DataAccessException(e);
    }
  }

  public Student getStudent(String id) throws DataAccessException {
    Student student = null;
    try(Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(GET_STUDENT)) {
      ps.setString(1,id);
      ResultSet rs = ps.executeQuery();

      if(rs.next()){
        student = new Student();
        student.setId(rs.getString("id"));
        student.setName(rs.getString("name"));
        student.setDept(rs.getString("dept"));
      }
      rs.close(); 
      return student;
    } catch(SQLException e) {
      e.printStackTrace();
      throw new DataAccessException(e);
    }
  }

  public int addStudent(Student student) throws DataAccessException {
    int updatedRow = 0;
    try(Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(ADD_STUDENT)) {
      ps.setString(1,student.getName());
      ps.setString(2,student.getDept());
      updatedRow = ps.executeUpdate();
      return updatedRow;
    } catch(SQLException e) {
      e.printStackTrace();
      throw new DataAccessException(e);
    }
  }

  public int updateStudent(Student student) throws DataAccessException {
    int updatedRow = 0;
    try(Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(UPDATE_STUDENT)) {
      ps.setString(1,student.getName());
      ps.setString(2,student.getDept());
      ps.setString(3,student.getId());
      updatedRow = ps.executeUpdate();
      return updatedRow;
    } catch(SQLException e) {
      e.printStackTrace();
      throw new DataAccessException(e);
    }
  }

  public int deleteStudent(String id) throws DataAccessException {
    int updatedRow = 0;
    try(Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(DELETE_STUDENT)) {
      ps.setString(1,id);
      updatedRow = ps.executeUpdate();
      return updatedRow;
    } catch(SQLException e) {
      e.printStackTrace();
      throw new DataAccessException(e);
    }
  }
}
