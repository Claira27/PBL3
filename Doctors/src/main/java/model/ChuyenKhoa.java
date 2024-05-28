package model;

public class ChuyenKhoa {
	private int id;
	private String ten;
	
	public ChuyenKhoa() {
		// TODO Auto-generated constructor stub
	}
	
	
	public ChuyenKhoa(int id, String ten) {
		super();
		this.id = id;
		this.ten = ten;
	}

	@Override
	public String toString() {
		return "ChuyenKhoa [id=" + id + ", ten=" + ten + "]";
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
	
}
