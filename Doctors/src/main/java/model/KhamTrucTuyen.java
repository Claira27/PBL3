package model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class KhamTrucTuyen extends DatLich{
	private double phi;
	private int danhgia;
	
	public KhamTrucTuyen() {
		// TODO Auto-generated constructor stub
	}

	public KhamTrucTuyen(int id, int idBacSy, int idBenhNhan, Date ngay, int idTimePeriod, double phi, int danhgia) {
		super(id, idBacSy, idBenhNhan, ngay, idTimePeriod);
		this.phi = phi;
		this.danhgia = danhgia;
	}

	public double getPhi() {
		return phi;
	}

	public void setPhi(double phi) {
		this.phi = phi;
	}

	public int getDanhgia() {
		return danhgia;
	}

	public void setDanhgia(int danhgia) {
		this.danhgia = danhgia;
	}

	@Override
	public String toString() {
		return "KhamTrucTuyen [phi=" + phi + ", danhgia=" + danhgia + "]";
	}
}
