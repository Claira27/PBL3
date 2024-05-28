package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NguoiDung extends TaiKhoan {
	private String diaChi;
	private String sdt;
	private Date ngaySinh;
	private String gioiTinh;
	
	public NguoiDung() {
		// TODO Auto-generated constructor stub
		super();
	}

	public NguoiDung(int id, String ten, String email, String password, int loai, String diaChi, String sdt, Date ngaySinh, String gioiTinh) {
		super(id, ten, email, password, loai);
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(sdt);
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
		NguoiDung other = (NguoiDung) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "NguoiDung [diaChi=" + diaChi + ", sdt=" + sdt + ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh
				 + ", id=" + id + ", ten="
				+ ten + ", email=" + email + ", password=" + password + ", loai=" + loai + "]";
	}
}
