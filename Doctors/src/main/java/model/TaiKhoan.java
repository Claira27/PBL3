package model;

import java.util.Objects;

public class TaiKhoan {
	protected int id;
	protected String ten;
	protected String email;
	protected String password;
	protected int loai;
	
	private static int ID = 0;
	public static int sinhID() {
		return ++ID;
	}
	
	public TaiKhoan() {
		// TODO Auto-generated constructor stub
	}

	public TaiKhoan(int id, String ten, String email, String password, int loai) {
		this.id = id;
		this.ten = ten;
		this.email = email;
		this.password = password;
		this.loai = loai;
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

	public void setName(String ten) {
		this.ten = ten;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLoai() {
		return loai;
	}

	public void setLoai(int loai) {
		this.loai = loai;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaiKhoan other = (TaiKhoan) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "TaiKhoan [id=" + id + ", ten=" + ten + ", email=" + email + ", password=" + password + ", loai=" + loai
				+ "]";
	}
	
	
}
