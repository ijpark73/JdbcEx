package com.ijpark.jdbc.dbutils;

import com.ijpark.jdbc.DataAccessException;

import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

public class DbUtils {

  DataSource ds = null;

  public DbUtils(DataSource ds) {
    this.ds = ds;
  }

  public <T> List<T> list(String sql, RowMapper<T> rowMapper, Object... params) throws DataAccessException {
    try(Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
      setParams(ps,params);
      ResultSet rs = ps.executeQuery();
      List<T> list = new ArrayList<>();
      while(rs.next()) {
        T t = rowMapper.mapRow(rs);
        list.add(t);
      }
      return list;
    } catch(SQLException e) {
      e.printStackTrace();
      throw new DataAccessException(e);
    }
    
  }

  public <T> T get(String sql, RowMapper<T> rowMapper, Object... params) throws DataAccessException {
    try(Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
      setParams(ps,params);
      ResultSet rs = ps.executeQuery();
      T t = null;
      if(rs.next()) {
        t = rowMapper.mapRow(rs);
      }
      return t;
    } catch(SQLException e) {
      e.printStackTrace();
      throw new DataAccessException(e);
    }
    
  }

  public int update(String sql, Object... params) throws DataAccessException {
    try(Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
      setParams(ps,params);
      return ps.executeUpdate();
    } catch(SQLException e) {
      e.printStackTrace();
      throw new DataAccessException(e);
    }
  }

  private void setParams(PreparedStatement ps, Object... params) throws SQLException {

    if(params!=null) {
      for(int i=0;i<params.length;i++)
        ps.setObject(i+1,params[i]);
    }
  }
}
