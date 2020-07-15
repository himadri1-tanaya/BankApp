package com.dxc.service;

import java.util.List;

import com.dxc.dao.AdminDaoImpl;
import com.dxc.pojos.User;

public class AdminServiceImpl 
{
	AdminDaoImpl adminDao=new AdminDaoImpl();
   public boolean loginCheck(String adminId,int adminPwd)
   {
	   return adminDao.loginCheck(adminId,adminPwd);
   }
   public void addCust(int accno, String name,double balance,int password)
   {
	   adminDao.addCust(accno,name,balance,password);
	   
   }
   public List<User> getAllUser()
	{
		return adminDao.getAllUser();
	}
   public void updateUser(User u1)
	{
	   System.out.println("service - update user");
		adminDao.updateUser(u1);
	}
   public double getCustBalance(User u1)
   {
	  return adminDao.getCustBalance(u1);
	   
   }
   public boolean removeUser(int accno)
	{
		return adminDao.removeUser(accno);
	}
   public boolean searchUser(int accno)
   {
	   return adminDao.searchUser(accno);
   }
   public List<User> getUser( int accnoId)
   {
	   return adminDao.getUser(accnoId);
   }
}
