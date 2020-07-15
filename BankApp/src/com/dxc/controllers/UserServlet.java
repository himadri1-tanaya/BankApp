package com.dxc.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dxc.pojos.Transactions;
import com.dxc.pojos.User;
import com.dxc.service.UserServiceImpl;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	String Message = "";
	String errorMessage = "";
	UserServiceImpl service = new UserServiceImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean res = false;
		HttpSession session = request.getSession();
		int username = Integer.parseInt(request.getParameter("user"));
		int pwd = Integer.parseInt(request.getParameter("pass"));
		res = service.loginCheck(username, pwd);
		if (res == true) {
			response.sendRedirect("usermenu.jsp");
		} else {
			errorMessage = "invalid login";
			session.setAttribute("errorMessage", errorMessage);
			response.sendRedirect("error.jsp");

		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = "";
		String temp = request.getParameter("userbtn");
		HttpSession session = request.getSession();
		if (temp != null)
			action = temp;
		if (action.equals("deposite")) {
			System.out.println("Entered deoosut servlet");
			int accno = Integer.parseInt(request.getParameter("accno"));
			double amount = Double.parseDouble(request.getParameter("amount"));
			User u1 = new User();
			boolean b = service.addAmount(accno, amount);
			if (b == true) {
				Message = "Amount added successfully!!";
				session.setAttribute("Message", Message);
				response.sendRedirect("view.jsp");
			} else {
				Message = "Invalid Account number";
				session.setAttribute("Message", Message);
				response.sendRedirect("view.jsp");
			}
		} else if (action.equals("withdraw")) {
			int accno1 = Integer.parseInt(request.getParameter("accno"));
			double amount1 = Double.parseDouble(request.getParameter("amount"));
			User u2 = new User();
			boolean b = service.withdrawMoney(accno1, amount1);
			System.out.println(b);
			if (b == true) {
				Message = "withdraw done!!";
				session.setAttribute("Message", Message);
				response.sendRedirect("view.jsp");
			} else {
				Message = "WITHDRAW UNSUCESSFUL";
				session.setAttribute("Message", Message);
				response.sendRedirect("view.jsp");
			}

		} else if (action.equals("changepassword")) {
			int accno = Integer.parseInt(request.getParameter("accno"));
			String password = request.getParameter("old");
			String newpassword = request.getParameter("new");
			User u3 = new User();
			boolean b = service.changePassword(accno, password, newpassword);
			System.out.println(b);
			if (b == true) {
				Message = "password changed succesfully!!";
				session.setAttribute("Message", Message);
				response.sendRedirect("view.jsp");
			} else {
				Message = "password cannot be changes";
				session.setAttribute("Message", Message);
				response.sendRedirect("view.jsp");
			}
		} else if (action.equals("check_balance")) {
			int accno = Integer.parseInt(request.getParameter("accno"));
			User u1 = new User();
			u1.setAccno(accno);

			double bal = service.checkBalance(u1);
			Message = "avail balance is-" + bal;
			u1.setBalance(bal);
			System.out.println(u1.getBalance());
			session.setAttribute("Message", Message);
			response.sendRedirect("view.jsp");
		} else if (action.equals("transfer_amount")) {
			int accno = Integer.parseInt(request.getParameter("accno"));
			int baccno = Integer.parseInt(request.getParameter("baccno"));
			double tamount = Double.parseDouble(request.getParameter("tamount"));
			boolean res = service.transAmount(accno, baccno, tamount);
			System.out.println(res);
			if (res == true) {
				Message = "amount transfered succesfully!!";
				session.setAttribute("Message", Message);
				response.sendRedirect("view.jsp");
			} else {
				Message = "error while transfering";
				session.setAttribute("Message", Message);
				response.sendRedirect("view.jsp");
			}
		} else if (action.equals("Mini_Statement")) {
			System.out.println("entered servlet");
			int customerId = Integer.parseInt(request.getParameter("accno"));
			List<Transactions> list = service.miniStatement(customerId);
			session.setAttribute("list", list);
			System.out.println(list);
			response.sendRedirect("MiniStatement.jsp");

		}

	}
}
