package model;

public class BacSyTichCuc {
	private int id;
	private String hinhanh;
	private String ten;
	private String benhvien;
	private int danhGia;
	private int tong;
	
	public BacSyTichCuc() {
		// TODO Auto-generated constructor stub
	}

	public BacSyTichCuc(int id, String hinhanh, String ten, String benhvien, int danhGia, int tong) {
		super();
		this.id = id;
		this.hinhanh = hinhanh;
		this.ten = ten;
		this.benhvien = benhvien;
		this.danhGia = danhGia;
		this.tong = tong;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBenhvien() {
		return benhvien;
	}

	public void setBenhvien(String benhvien) {
		this.benhvien = benhvien;
	}

	public int getTong() {
		return tong;
	}

	public void setTong(int tong) {
		this.tong = tong;
	}

	public int getDanhGia() {
		return danhGia;
	}

	public void setDanhGia(int danhGia) {
		this.danhGia = danhGia;
	}
	
	public String getHinhanh() {
		return hinhanh;
	}

	public void setHinhanh(String hinhanh) {
		this.hinhanh = hinhanh;
	}

	@Override
	public String toString() {
		return "BacSyTichCuc [id=" + id + ", benhvien=" + benhvien + ", danhGia=" + danhGia + ", tong=" + tong + "]";
	}
	
}
