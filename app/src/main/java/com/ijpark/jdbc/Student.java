package com.ijpark.jdbc;

public class Student {
  private String id;
  private String name;
  private String dept;

  public Student() {}

  public Student(String id, String name, String dept){
    this.id = id;
    this.name = name;
    this.dept = dept;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getId(){
    return this.id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName(){
    return this.name;
  }

  public void setDept(String dept) {
    this.dept = dept;
  }

  public String getDept(){
    return this.dept;
  }

  @Override
  public String toString(){
    return "Student [id="+id+", name="+name+", dept="+dept+"]";
  }
}
