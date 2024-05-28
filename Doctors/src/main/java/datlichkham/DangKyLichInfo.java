package datlichkham;

import java.util.ArrayList;

import model.DangKyLich;

public class DangKyLichInfo {
	private ArrayList<TimeAndID> thu;
	private ArrayList<TimeAndID> slot;
	private ArrayList<DangKyLich> dangkylich;
	
	public DangKyLichInfo() {
		// TODO Auto-generated constructor stub
	}

	public DangKyLichInfo(ArrayList<TimeAndID> thu, ArrayList<TimeAndID> slot, ArrayList<DangKyLich> dangkylich) {
		super();
		this.thu = thu;
		this.slot = slot;
		this.dangkylich = dangkylich;
	}

	public ArrayList<TimeAndID> getThu() {
		return thu;
	}

	public void setThu(ArrayList<TimeAndID> thu) {
		this.thu = thu;
	}

	public ArrayList<TimeAndID> getSlot() {
		return slot;
	}

	public void setSlot(ArrayList<TimeAndID> slot) {
		this.slot = slot;
	}

	public ArrayList<DangKyLich> getDangkylich() {
		return dangkylich;
	}

	public void setDangkylich(ArrayList<DangKyLich> dangkylich) {
		this.dangkylich = dangkylich;
	}

	@Override
	public String toString() {
		return "DatLichInfo [thu=" + thu + ", slot=" + slot + ", dangkylich=" + dangkylich + "]";
	}
}
