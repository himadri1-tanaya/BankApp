package com.dxc.dao;
import java.sql.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dxc.pojos.User;
import com.mysql.cj.protocol.Resultset;


public class AdminDaoImpl 
{
private static Connection conn;
	
	
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("driver loaded...");
			
		 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Himadri","root", "PASS");
			System.out.println("connected to database....");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public boolean loginCheck(String adminId,int adminPwd)
	{
		boolean res=false;
		try {
			PreparedStatement pstmt=conn.prepareStatement("select * from admin");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				if(adminId.equals(rs.getString(1)) && adminPwd==(rs.getInt(2)))
					res=true;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	  return res;
	}
	 public void addCust(int accno, String name,double balance,int password) 
	 {
		 try {
			PreparedStatement pstmt=conn.prepareStatement("insert into bankuser values(?,?,?,?)");
			pstmt.setInt(1,accno);
			pstmt.setString(2,name);
			pstmt.setDouble(3,balance);
			pstmt.setInt(4, password);
			pstmt.execute();
			
		   } 
		 catch (SQLException e) 
		 {
			e.printStackTrace();
		}
		 
	 }
	 public List<User> getAllUser()
	 {
		 List<User> list=new ArrayList<>();
		 try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from bankuser");
			while(rs.next())
			{
				list.add(new User(rs.getInt(1),rs.getString(2),rs.getDouble(3), rs.getInt(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return list;
		 
	 }
	 public void updateUser(User u1)
	 {
		 System.out.println("dao - update user");
		 try {
			PreparedStatement pstmt=conn.prepareStatement("update bankuser set name=? where accno=?");
			System.out.println(u1.getAccno()+""+u1.getName());
			pstmt.setString(1, u1.getName());
			pstmt.setInt(2,u1.getAccno());
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	 public double getCustBalance(User u1)
	 { 
		 int accno=u1.getAccno();
		 double balance=0;
		 Statement stmt;
		 try {
			stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from bankuser");
			while(rs.next())
			{
				if(accno==rs.getInt(1))
				{
					balance=rs.getDouble(3);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println(balance);
		 
		 return balance;
	 }
	 public boolean removeUser(int accno)
	 {
		 boolean status=true;
		 try {
			PreparedStatement pstmt=conn.prepareStatement("delete from bankuser where accno=?");
			pstmt.setInt(1, accno);
			status=pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return ! status;
	 }
	 public boolean searchUser(int accno)
	 {
		 try {
				Statement stmt=conn.createStatement();
				ResultSet rs=stmt.executeQuery("select * from bankuser");
				while(rs.next())
				{
					if(accno==rs.getInt(1))
					{
						return true;
					}
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return false;
		 
	 }
	 public List<User> getUser(int accnoId){
		 
		 List<User> list=new ArrayList<>();
		 try {

				PreparedStatement pstmt = conn.prepareStatement("select * from bankuser where accno=?");
				pstmt.setInt(1, accnoId);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					list.add(new User(rs.getInt(1),rs.getString(2),rs.getDouble(3), rs.getInt(4)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
//		 try {
//			Statement stmt=conn.createStatement();
//			ResultSet rs=stmt.executeQuery("select * from bankuser where accno=?");
//			while(rs.next())
//			{
//				list.add(new User(rs.getInt(1),rs.getString(2),rs.getDouble(3), rs.getInt(4)));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		 
		 return list;
	 }
    	   
}
