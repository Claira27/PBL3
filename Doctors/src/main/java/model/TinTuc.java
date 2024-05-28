package model;

import java.sql.Date;
import java.util.Objects;

public class TinTuc {
	private int id;
	private String tieuDe;
	private String hinhAnh;
	private Date ngayDang;
	private int loai;
	
	private static int ID = 0;
	public static int sinhID() {
		return ++ID;
	}
	
	public TinTuc() {
		// TODO Auto-generated constructor stub
	}

	public TinTuc(int id, String tieuDe, String hinhAnh, Date ngayDang, int loai) {
		super();
		this.id = id;
		this.tieuDe = tieuDe;
		this.hinhAnh = hinhAnh;
		this.ngayDang = ngayDang;
		this.loai = loai;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTieuDe() {
		return tieuDe;
	}

	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public Date getNgayDang() {
		return ngayDang;
	}

	public void setNgayDang(Date ngayDang) {
		this.ngayDang = ngayDang;
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
		TinTuc other = (TinTuc) obj;
		return id == other.id;
	}
}
