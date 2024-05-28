package database;

import java.util.ArrayList;

import model.TaiKhoan;

public class TaiKhoanDAO {
private ArrayList<TaiKhoan> data = new ArrayList<TaiKhoan>();
	
	private ArrayList<TaiKhoan> selectAll(){
		return data;
	}
	
	private TaiKhoan selectById(int id) {
		TaiKhoan result = null;
		TaiKhoan tem = new TaiKhoan();
		tem.setId(id);
		for(TaiKhoan item : data) {
			if(item.equals(tem)) {
				result = item;
			}
		}
		return result;
	}
	
	private int insert(TaiKhoan item) {
		TaiKhoan tem = selectById(item.getId());
		if(tem !=null) {
			data.add(item);
			return 1;
		}
		return 0;
	}
	
	private int insertAll(ArrayList<TaiKhoan> ds) {
		int dem = 0;
		for(TaiKhoan item : ds) {
			TaiKhoan tem = selectById(item.getId());
			if(tem !=null) {
				data.add(item);
				dem++;
			}
		}
		return dem;
	}
	
	private int delete(TaiKhoan item) {
		TaiKhoan tem = selectById(item.getId());
		if(tem !=null) {
			data.remove(item);
			return 1;
		}
		return 0;
	}
	
	private int deleteAll(ArrayList<TaiKhoan> ds) {
		int dem = 0;
		for(TaiKhoan item : ds) {
			dem+=this.delete(item);
		}
		return dem;
	}
	
	private int update(TaiKhoan item) {
		TaiKhoan tem = selectById(item.getId());
		if(tem !=null) {
			data.remove(item);
			data.add(item);
			return 1;
		}
		return 0;
	}
}
