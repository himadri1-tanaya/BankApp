package com.dxc.pojos;

public class User1 
{
  private String username;
  private int pwd;
  public User1()
  {
	  
  }
public User1(String username, int pwd) {
	super();
	this.username = username;
	this.pwd = pwd;
}
public String getUsername() 
{
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public int getPwd() {
	return pwd;
}
public void setPwd(int pwd) {
	this.pwd = pwd;
}

  
}
