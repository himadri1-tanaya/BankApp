package com.dxc.pojos;

public class User 
{
  private int accno;
  private String name;
  private double balance;
  private int password;
  
  
  public User()
  {
	  
  }


public User(int accno, String name, double balance, int password) {
	super();
	this.accno = accno;
	this.name = name;
	this.balance = balance;
	this.password = password;
}


public int getAccno() {
	return accno;
}


public void setAccno(int accno) {
	this.accno = accno;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public double getBalance() {
	return balance;
}


public void setBalance(double balance) {
	this.balance = balance;
}


public int getPassword() {
	return password;
}


public void setPassword(int password) {
	this.password = password;
}


@Override
public String toString() {
	return "User [accno=" + accno + ", name=" + name + ", balance=" + balance + ", password=" + password + "]";
}


  
  
}
