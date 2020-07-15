package com.dxc.controllers;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;

import com.dxc.pojos.Admin;
import com.dxc.pojos.User;
import com.dxc.service.AdminServiceImpl;

@WebServlet("/bank")
public class AdminServlet extends HttpServlet 
{ 
	String Message="";
	String errorMessage="";
	AdminServiceImpl service=new AdminServiceImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{ 
		
		                  boolean res=false;
		                  HttpSession session=request.getSession();
				          String adminId=request.getParameter("id");
				          int adminPwd=Integer.parseInt(request.getParameter("password"));
				          System.out.println(adminPwd);
				          res=service.loginCheck(adminId,adminPwd);
				          if(res==true)
				          {
				        	  response.sendRedirect("adminmenu.jsp");
				          }
				          else
			                  {
				                errorMessage="invalid login";
				                 session.setAttribute("errorMessage", errorMessage);
				                   response.sendRedirect("error.jsp");
			                  }
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {	
    	   String action="";
    	   String temp=request.getParameter("adminbtn");
    	   HttpSession session=request.getSession();
    	   if(temp!=null)
    		   action=temp;
    	   if(action.equals("add_customer"))
    	   {
    		   int accno=Integer.parseInt(request.getParameter("accno")) ;
    		   String name=request.getParameter("name");
    		   double balance=Double.parseDouble(request.getParameter("balance"));
    		   int password=Integer.parseInt(request.getParameter("password"));
    		   User u1=new User();
    		   service.addCust(accno, name, balance,password);
    		   Message="Customer added successfully!!";
    		   session.setAttribute("Message", Message);
    		   response.sendRedirect("view.jsp");
    		   
    	   }
    	   else if(action.equals("search_customer"))
    	   {
    		   int accno=Integer.parseInt(request.getParameter("accno"));
    		   session.setAttribute("accno", accno);
    		   boolean searchStatus=service.searchUser(accno);
    		   User u1=new User();
    		   if(searchStatus==true)
    		   {
    			   int accnoId = (int) session.getAttribute("accno");
    			   List<User> list=service.getUser(accnoId);
    	   			session.setAttribute("list", list);
    	   			response.sendRedirect("showcust.jsp");
    		   }
    		   else
    		   {
    			   Message="User not found";
    			   session.setAttribute("Message", Message);
        		   response.sendRedirect("view.jsp");
    		   }
    		  
    		   
    	   }
    	   
    	   else if(action.equals("update_customer"))
    	   {
    		   System.out.println("servlet - update user");
    		   int accno=Integer.parseInt(request.getParameter("accno"));
    		   String name=request.getParameter("name");
    		   User u1=new User();
    		   u1.setAccno(accno);
    		   u1.setName(name);
    		   
    		   service.updateUser(u1);
    		   Message="User Updated!!";
    		   session.setAttribute("Message", Message);
    		   response.sendRedirect("view.jsp");
    		   
    		   
    	   }
    	   else if(action.equals("get_cust_bal"))
    	   {
    		   int accno=Integer.parseInt(request.getParameter("accno"));
    		   User u1=new User();
    		   u1.setAccno(accno);
    	
    		   double bal=service.getCustBalance(u1);
    		   Message="avail balance is-"+bal;
    		   u1.setBalance(bal);
    		   System.out.println(u1.getBalance());
    		   session.setAttribute("Message", Message);
    		   response.sendRedirect("view.jsp");
    	  }
    		  
    	   else if(action.equals("remove_customer"))
    	   {
    		   int accno=Integer.parseInt(request.getParameter("accno"));
    		   boolean removeStatus=service.removeUser(accno);
    		   if(removeStatus==true)
    		   {
    			   Message="user removed";
    		   }
    		   else
    		   {
    			   Message="user not found";
    		   }
    		   session.setAttribute("Message", Message);
    		   response.sendRedirect("view.jsp");
    	   }
    	   else
    	   {
    	   List<User> list=service.getAllUser();
   			session.setAttribute("list", list);
   			response.sendRedirect("showcust.jsp");
   			
    	   }
    }
	}

	
	


