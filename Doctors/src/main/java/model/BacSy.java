package model;

import java.util.Objects;

public class BacSy extends TaiKhoan implements Comparable<BacSy>{
	private int id_benhVien;
	private String bangCap;
	private int namKinhNghiem;
	private int luotDanhGia;
	private double danhGia;
	private String hinhanh;
	private int dichvu;
	private double giadichvu;
	
	public BacSy() {
		// TODO Auto-generated constructor stub
	}

	public BacSy(int id, String ten, String email, String password, int loai, int id_benhVien, String bangCap, int namKinhNghiem, int luotDanhGia, double danhGia, String hinhanh, int dichvu, double giadichvu) {
		super(id, ten, email, password, loai);
		this.id_benhVien = id_benhVien;
		this.bangCap = bangCap;
		this.namKinhNghiem = namKinhNghiem;
		this.luotDanhGia = luotDanhGia;
		this.danhGia = danhGia;
		this.hinhanh = hinhanh;
		this.dichvu = dichvu;
		this.giadichvu = giadichvu;
	}

	
	public double getGiadichvu() {
		return giadichvu;
	}

	public void setGiadichvu(double giadichvu) {
		this.giadichvu = giadichvu;
	}

	public int getId_benhVien() {
		return id_benhVien;
	}

	public void setId_benhVien(int id_benhVien) {
		this.id_benhVien = id_benhVien;
	}

	public String getBangCap() {
		return bangCap;
	}

	public void setBangCap(String bangCap) {
		this.bangCap = bangCap;
	}

	public int getNamKinhNghiem() {
		return namKinhNghiem;
	}

	public void setNamKinhNghiem(int namKinhNghiem) {
		this.namKinhNghiem = namKinhNghiem;
	}

	public int getLuotDanhGia() {
		return luotDanhGia;
	}

	public void setLuotDanhGia(int luotDanhGia) {
		this.luotDanhGia = luotDanhGia;
	}

	public double getDanhGia() {
		return danhGia;
	}

	public void setDanhGia(double danhGia) {
		this.danhGia = danhGia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(danhGia);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BacSy other = (BacSy) obj;
		return id == other.id;
	}

	public String getHinhanh() {
		return hinhanh;
	}

	public void setHinhanh(String hinhanh) {
		this.hinhanh = hinhanh;
	}

	public int getDichvu() {
		return dichvu;
	}

	public void setDichvu(int dichvu) {
		this.dichvu = dichvu;
	}

	@Override
	public String toString() {
		return "BacSy [id_benhVien=" + id_benhVien + ", bangCap=" + bangCap + ", namKinhNghiem=" + namKinhNghiem
				+ ", luotDanhGia=" + luotDanhGia + ", danhGia=" + danhGia + ", hinhanh=" + hinhanh + ", dichvu="
				+ dichvu + "]";
	}

	@Override
	public int compareTo(BacSy o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
