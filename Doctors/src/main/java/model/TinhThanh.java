package model;

public class TinhThanh {
	private int id;
	private String ten;
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
	public TinhThanh(int id, String ten) {
		super();
		this.id = id;
		this.ten = ten;
	}
	
	public TinhThanh() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TinhThanh [id=" + id + ", ten=" + ten + "]";
	}
	
	
}
