package com.dxc.pojos;

public class Admin 
{
     private int userid;
     private int password;
     public Admin()
     {
    	 
     }
	public Admin(int userid, int password) {
		super();
		this.userid = userid;
		this.password = password;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Admin [userid=" + userid + ", password=" + password + "]";
	}
     
}
