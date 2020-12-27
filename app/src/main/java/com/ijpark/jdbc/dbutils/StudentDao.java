package com.ijpark.jdbc.dbutils;

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

  private DbUtils dbUtils = null;

  public StudentDao() {
    ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:db/jdbc.db");
    this.dbUtils = new DbUtils(ds);
  }

  public List<Student> listStudents() {
    // Lambda expression 
    return dbUtils.list(LIST_STUDENTS, (rs)->{
          Student student = new Student();
          student.setId(rs.getString("id"));
          student.setName(rs.getString("name"));
          student.setDept(rs.getString("dept"));
          return student;
        });
  }

  public Student getStudent(String id) {
    // Anonymous class
    RowMapper<Student> mapper = new RowMapper<Student>() {
      @Override
        public Student mapRow(ResultSet rs) throws SQLException {
          Student student = new Student();
          student.setId(rs.getString("id"));
          student.setName(rs.getString("name"));
          student.setDept(rs.getString("dept"));
          return student;
        }
    };
    return dbUtils.get(GET_STUDENT, mapper, id);
  }

  public int addStudent(Student student) {
    return dbUtils.update(ADD_STUDENT,student.getId(),student.getName(),
        student.getDept());
  }

  public int updateStudent(Student student) {
    return dbUtils.update(UPDATE_STUDENT,student.getName(),
        student.getDept(),student.getId());
  }

  public int deleteStudent(String id) {
    return dbUtils.update(DELETE_STUDENT,id);
  }
}
