package model;

public class PhanChuyenMon {
	private int id;
	private int id_bacsy;
	private int id_chuyenkhoa;
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
	public int getId_chuyenkhoa() {
		return id_chuyenkhoa;
	}
	public void setId_chuyenkhoa(int id_chuyenkhoa) {
		this.id_chuyenkhoa = id_chuyenkhoa;
	}
	public PhanChuyenMon(int id, int id_bacsy, int id_chuyenkhoa) {
		super();
		this.id = id;
		this.id_bacsy = id_bacsy;
		this.id_chuyenkhoa = id_chuyenkhoa;
	}
	
	public PhanChuyenMon() {
		// TODO Auto-generated constructor stub
	}
}
