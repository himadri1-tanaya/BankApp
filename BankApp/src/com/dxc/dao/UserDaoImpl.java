package com.dxc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dxc.pojos.Transactions;
import com.dxc.pojos.User;

public class UserDaoImpl {
	private static Connection conn;

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("driver loaded...");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/himadri", "root", "PASS");
			System.out.println("connected to database....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean loginCheck(int username, int pwd) {
		try {
			PreparedStatement pstmt = conn
					.prepareStatement("select accno, password from bankuser where accno=? and password=?");
			pstmt.setInt(1, username);
			pstmt.setInt(2, pwd);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {

				return true;
			} else {
				return false;
			}
			// pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addAmount(int accno, double amount) {
		double balance = 0;
		// System.out.println("daoimpl 1");
		PreparedStatement pstmt;
		try {

			PreparedStatement pstmt2 = conn.prepareStatement("select * from bankuser where accno=?");
			pstmt2.setInt(1, accno);
			ResultSet rs = pstmt2.executeQuery();

			rs.next();
			int id = rs.getInt(1);
			balance = rs.getDouble(3);
			balance = balance + amount;

			if (id == accno) {
				pstmt = conn.prepareStatement("update bankuser set balance=? where accno=?");
				pstmt.setDouble(1, balance);
				pstmt.setInt(2, accno);
				pstmt.execute();

				PreparedStatement pstmt1 = conn.prepareStatement("select * from bankuser where accno=?");
				pstmt1.setInt(1, accno);
				pstmt1.execute();
				// System.out.println("daoimpl 2");
				return true;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return false;
	}

	public boolean withdrawmoney(int accno1, double amount1) {
		double balance = 0;
		System.out.println("daoimpl 1");
		PreparedStatement pstmt;
		try {

			PreparedStatement pstmt2 = conn.prepareStatement("select * from bankuser where accno=?");
			pstmt2.setInt(1, accno1);
			ResultSet rs = pstmt2.executeQuery();

			rs.next();
			int id = rs.getInt(1);
			balance = rs.getDouble(3);
			System.out.println(balance + "1");
			if (balance > amount1) {
				balance = balance - amount1;
			} else {
				System.out.println("esle");
				return false;
			}

			if (id == accno1) {
				pstmt = conn.prepareStatement("update bankuser set balance=? where accno=?");
				pstmt.setDouble(1, balance);
				pstmt.setInt(2, accno1);
				pstmt.executeUpdate();
				System.out.println("2");
				PreparedStatement pstmt1 = conn.prepareStatement("select * from bankuser where accno=?");
				pstmt1.setInt(1, accno1);
				pstmt1.execute();
				System.out.println("3");
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("out");
		return false;
	}

	public boolean changepassword(int accno, String password, String newpassword) {
		PreparedStatement pstmt;
		try {

			PreparedStatement pstmt4 = conn.prepareStatement("select * from bankuser where accno=?");
			pstmt4.setInt(1, accno);
			ResultSet rs = pstmt4.executeQuery();
			while (rs.next()) {
				if (password.equals(rs.getString(4))) {
					PreparedStatement pstmt1 = conn.prepareStatement("update bankuser set password=? where accno=?");
					pstmt1.setString(1, newpassword);
					pstmt1.setInt(2, accno);
					pstmt1.executeUpdate();
				}
			}
			PreparedStatement pstmt3 = conn.prepareStatement("select * from bankuser where accno=?");
			pstmt3.setInt(1, accno);
			pstmt3.execute();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public double checkBalance(User u1) {
		int accno = u1.getAccno();
		double balance = 0;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from bankuser");
			while (rs.next()) {
				if (accno == rs.getInt(1)) {
					balance = rs.getDouble(3);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(balance);

		return balance;
	}

	public boolean transAmount(int accno, int baccno, double tamount) {
		double balance = 0;
		try {

			PreparedStatement pstmt2 = conn.prepareStatement("select * from bankuser where accno=?");
			pstmt2.setInt(1, accno);
			ResultSet rs = pstmt2.executeQuery();

			rs.next();
			int id = rs.getInt(1);
			balance = rs.getDouble(3);
			balance = balance - tamount;

			PreparedStatement pstmt3 = conn.prepareStatement("update bankuser set balance=? where accno=?");
			pstmt3.setDouble(1, balance);
			pstmt3.setInt(2, accno);
			pstmt3.execute();

			PreparedStatement pstmt4 = conn.prepareStatement("select * from bankuser where accno=?");
			pstmt4.setInt(1, baccno);
			ResultSet rs1 = pstmt4.executeQuery();

			rs1.next();
			// int id = rs.getInt(1);
			double balance1 = rs1.getDouble(3);
			balance1 = balance1 + tamount;
			System.out.println(balance + "1");

			PreparedStatement pstmt5 = conn.prepareStatement("update bankuser set balance=? where accno=?");
			pstmt5.setDouble(1, balance1);
			pstmt5.setInt(2, baccno);
			pstmt5.execute();

			PreparedStatement pstmt = conn.prepareStatement("insert into transactiondetails values(?,?,?,?)");
			// ResultSet rs3 = pstmt.executeQuery();
			// String str ="";
			// int i = rs3.getInt(2);
			// String str = Integer.toString(i);
			pstmt.setInt(1, accno);
			pstmt.setInt(2, baccno);
			pstmt.setDouble(3, tamount);
			pstmt.setString(4, "transfered");

			pstmt.execute();

			PreparedStatement pstmt1 = conn.prepareStatement("insert into transactiondetails values(?,?,?,?)");
			pstmt1.setInt(1, baccno);
			pstmt1.setInt(2, accno);
			pstmt1.setDouble(3, tamount);
			pstmt1.setString(4, "credit");
			pstmt1.execute();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public List<Transactions> miniStatement(int customerId) {
		System.out.println("entered dao mini");
		List<Transactions> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = conn
					.prepareStatement("select * from transactiondetails where accno=? order by accno asc limit 5");
			pstmt.setInt(1, customerId);
			ResultSet rs = pstmt.executeQuery();
			System.out.println(rs);
			while (rs.next()) {
				list.add(new Transactions(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(4)));
			}
System.out.println("dao" +list);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
