package com.dxc.pojos;

public class Transactions {

	private int accno;
	private int baccno;
	private double tamount;
	private String credit;
	
	public Transactions() {
		
	}

	public Transactions(int accno, int baccno, double tamount, String credit) {
		super();
		this.accno = accno;
		this.baccno = baccno;
		this.tamount = tamount;
		this.credit = credit;
	}

	public int getAccno() {
		return accno;
	}

	public void setAccno(int accno) {
		this.accno = accno;
	}

	public int getBaccno() {
		return baccno;
	}

	public void setBaccno(int baccno) {
		this.baccno = baccno;
	}

	public double getTamount() {
		return tamount;
	}

	public void setTamount(double tamount) {
		this.tamount = tamount;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	@Override
	public String toString() {
		return "Transactions [accno=" + accno + ", baccno=" + baccno + ", tamount=" + tamount + ", credit=" + credit
				+ "]";
	}
	
	
}
