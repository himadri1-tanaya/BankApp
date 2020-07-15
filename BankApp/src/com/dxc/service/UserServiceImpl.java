package com.dxc.service;

import java.util.List;

import com.dxc.dao.UserDaoImpl;
import com.dxc.pojos.Transactions;
import com.dxc.pojos.User;

public class UserServiceImpl {
	UserDaoImpl userDao = new UserDaoImpl();

	public boolean loginCheck(int username, int pwd) {
		return userDao.loginCheck(username, pwd);
	}

	public boolean addAmount(int accno, double amount) {
		System.out.println("service");
		return userDao.addAmount(accno, amount);
	}

	public boolean withdrawMoney(int accno1, double amount1) {
		System.out.println("service");
		return userDao.withdrawmoney(accno1, amount1);
	}

	public boolean changePassword(int accno, String password, String newpassword) {
		System.out.println("service");
		return userDao.changepassword(accno, password, newpassword);
	}

	public double checkBalance(User u1) {
		return userDao.checkBalance(u1);

	}

	public boolean transAmount(int accno, int baccno, double tamount) {
		return userDao.transAmount(accno, baccno, tamount);
	}

	public List<Transactions> miniStatement(int customerId) {
		System.out.println("111111111111111111111");
		return userDao.miniStatement(customerId);
	}

}
