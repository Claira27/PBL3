package model;

import java.sql.Date;

public class DatLich {
	private int id;
	private int idBacSy;
	private int idBenhNhan;
	private Date ngay;
	private int idTimePeriod;
	
	public DatLich() {
		// TODO Auto-generated constructor stub
	}

	public DatLich(int id, int idBacSy, int idBenhNhan, Date ngay, int idTimePeriod) {
		super();
		this.id = id;
		this.idBacSy = idBacSy;
		this.idBenhNhan = idBenhNhan;
		this.ngay = ngay;
		this.idTimePeriod = idTimePeriod;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdBacSy() {
		return idBacSy;
	}

	public void setIdBacSy(int idBacSy) {
		this.idBacSy = idBacSy;
	}

	public int getIdBenhNhan() {
		return idBenhNhan;
	}

	public void setIdBenhNhan(int idBenhNhan) {
		this.idBenhNhan = idBenhNhan;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}

	public int getIdTimePeriod() {
		return idTimePeriod;
	}

	public void setIdTimePeriod(int idTimePeriod) {
		this.idTimePeriod = idTimePeriod;
	}

	@Override
	public String toString() {
		return "DatLich [id=" + id + ", idBacSy=" + idBacSy + ", idBenhNhan=" + idBenhNhan + ", ngay=" + ngay
				+ ", idTimePeriod=" + idTimePeriod + "]";
	}	
}
