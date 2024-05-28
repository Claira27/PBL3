package model;

import java.sql.Date;
import java.util.Objects;

public class NguoiDungTichCuc {
	private int id;
	private String ten; 
	private String diaChi;
	private Date ngaySinh;
	private int tong;
	
	public NguoiDungTichCuc(int id, String ten, String diaChi, Date ngaySinh, int tong) {
		super();
		this.id = id;
		this.ten = ten;
		this.diaChi = diaChi;
		this.ngaySinh = ngaySinh;
		this.tong = tong;
	}

	public NguoiDungTichCuc() {
		// TODO Auto-generated constructor stub
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public int getTong() {
		return tong;
	}

	public void setTong(int tong) {
		this.tong = tong;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}
}
