package model;

import java.sql.Date;

public class TimKiem {
	private int id;
	private Date ngay;
	private int soluot;
	
	public TimKiem() {
		// TODO Auto-generated constructor stub
	}

	public TimKiem(int id, Date ngay, int soluot) {
		super();
		this.id = id;
		this.ngay = ngay;
		this.soluot = soluot;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}

	public int getSoluot() {
		return soluot;
	}

	public void setSoluot(int soluot) {
		this.soluot = soluot;
	}

	@Override
	public String toString() {
		return "TimKiem [id=" + id + ", ngay=" + ngay + ", soluot=" + soluot + "]";
	}
}	
