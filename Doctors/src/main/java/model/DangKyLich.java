package model;

public class DangKyLich {
	private int id;
	private int id_bacsy;
	private int ngay;//Thu Hai -> Thu Bay (2->7)
	private int id_time;
	
	public DangKyLich() {
		// TODO Auto-generated constructor stub
	}

	public DangKyLich(int id, int id_bacsy, int ngay, int id_time) {
		super();
		this.id = id;
		this.id_bacsy = id_bacsy;
		this.ngay = ngay;
		this.id_time = id_time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_bacsy() {
		return id_bacsy;
	}

	public void setId_bacsy(int id_bacsy) {
		this.id_bacsy = id_bacsy;
	}

	public int getNgay() {
		return ngay;
	}

	public void setNgay(int ngay) {
		this.ngay = ngay;
	}

	public int getId_time() {
		return id_time;
	}

	public void setId_time(int id_time) {
		this.id_time = id_time;
	}

	@Override
	public String toString() {
		return "DangKyLich [id=" + id + ", id_bacsy=" + id_bacsy + ", ngay=" + ngay + ", id_time=" + id_time + "]";
	}
	
}
