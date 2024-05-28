package model;

import java.util.Objects;

public class BenhVien {
	private int id;
	private String ten;
	private int id_tinhThanh;
	
	private static int ID = 0;
	public static int sinhID() {
		return ++ID;
	}
	
	public BenhVien() {
		// TODO Auto-generated constructor stub
	}

	public BenhVien(int id, String ten, int id_tinhThanh) {
		super();
		this.id = id;
		this.ten = ten;
		this.id_tinhThanh = id_tinhThanh;
	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	
	public int getId_tinhThanh() {
		return id_tinhThanh;
	}

	public void setId_tinhThanh(int id_tinhThanh) {
		this.id_tinhThanh = id_tinhThanh;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, ten);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BenhVien other = (BenhVien) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "BenhVien [id=" + id + ", ten=" + ten + ", id_tinhThanh=" + id_tinhThanh + "]";
	}
	
	
}
