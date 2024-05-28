package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import datlichkham.DangKyLichInfo;
import datlichkham.TimeAndID;
import datlichkham.TimePeriod;
import model.DangKyLich;

public class DangKyLichDAO implements DAOInterface<DangKyLich>{

	@Override
	public ArrayList<DangKyLich> selectAll() {
		ArrayList<DangKyLich> result = new ArrayList<DangKyLich>();
		Connection c = DataBaseConnection.getConnection();
		String sql = "select* from dangkylich";
		try {
			Statement st = c.createStatement();
			ResultSet resultSet = st.executeQuery(sql);
			while(resultSet.next()) {
				//ten, email, matkhau, loai, diachi, sdt, ngaysinh, gioitinh
				int id = resultSet.getInt("id");
				int id_bacsy = resultSet.getInt("id_bacsy");
				int ngay = resultSet.getInt("ngay");
				int id_timeperiod = resultSet.getInt("id_time");
				DangKyLich datLich = new DangKyLich(id, id_bacsy, ngay, id_timeperiod);
				result.add(datLich);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}

	@Override
	public DangKyLich selectById(int id) {
		DangKyLich result = null;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "select * from dangkylich where id = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
				//ten, email, matkhau, loai, diachi, sdt, ngaysinh, gioitinh
				int id_bacsy = resultSet.getInt("id_bacsy");
				int ngay = resultSet.getInt("ngay");
				int id_timeperiod = resultSet.getInt("id_time");
				result = new DangKyLich(id, id_bacsy, ngay, id_timeperiod);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}
	
	@Override
	public int insert(DangKyLich item) {
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "insert into dangkylich(id_bacsy, ngay, id_time) values "
					+ "(?,?,?);";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, item.getId_bacsy());
			st.setInt(2, item.getNgay());
			st.setInt(3, item.getId_time());
			
			rowAffected = st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return rowAffected;
	}

	@Override
	public int insertAll(ArrayList<DangKyLich> ds) {
		int rowsAffected = 0;
		Connection c = DataBaseConnection.getConnection();
	    try {
	    	String sql = "insert into dangkylich(id_bacsy, ngay, id_time) values "
					+ "(?,?,?);";
			PreparedStatement st = c.prepareStatement(sql);
			for(DangKyLich item : ds) {
				st.setInt(1, item.getId_bacsy());
				st.setInt(2, item.getNgay());
				st.setInt(3, item.getId_time());
		        st.addBatch();
	    	}
	        
	        // Thực hiện các câu lệnh INSERT trong batch
	        int[] batchResult = st.executeBatch();
	        
	        // Tính tổng số dòng bị ảnh hưởng
	        for (int result : batchResult) {
	            rowsAffected += result;
	        }
	    } 
	    catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    DataBaseConnection.closeConnection(c);
	    return rowsAffected;
	}

	@Override
	public int deleteByID(int id) {
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "delete from dangkylich where id = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, id);
			
			rowAffected = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return rowAffected;
	}

	@Override
	public int deleteAll(ArrayList<DangKyLich> ds) {
		int rowsAffected = 0;
		Connection c = DataBaseConnection.getConnection();
	    try {
	    	String sql = "delete from dangkylich where id = ?";
	    	PreparedStatement st = c.prepareStatement(sql);
	    	for(DangKyLich item : ds) {
	    		st.setInt(1, item.getId());
	    		st.addBatch();
	    	}
	        
	        // Thực hiện các câu lệnh INSERT trong batch
	        int[] batchResult = st.executeBatch();
	        
	        // Tính tổng số dòng bị ảnh hưởng
	        for (int result : batchResult) {
	            rowsAffected += result;
	        }
	    } 
	    catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    DataBaseConnection.closeConnection(c);
	    return rowsAffected;
	}

	@Override
	public int update(DangKyLich item) {
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "UPDATE dangkylich SET id_bacsy = ?, ngay = ?, id_time = ? WHERE id = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, item.getId_bacsy());
			st.setInt(2, item.getNgay()); 	
			st.setInt(3, item.getId_time());
			st.setInt(4, item.getId());
			rowAffected = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return rowAffected;
	}

	//select dang ky theo thu trong tuan
	public ArrayList<Integer> selectTimeByDayByDoctoID(int id_bacsy, int dateid) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "select * from dangkylich where id_bacsy = ? AND ngay = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, id_bacsy);
			st.setInt(2, dateid);
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
				int id_timeperiod = resultSet.getInt("id_time");
				result.add(id_timeperiod);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}
	
	public ArrayList<DangKyLich> selectTimeByDoctorId(int id_bacsy) {
		ArrayList<DangKyLich> result = new ArrayList<DangKyLich>();
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "select * from dangkylich where id_bacsy = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, id_bacsy);
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				int ngay = resultSet.getInt("ngay");
				int id_time = resultSet.getInt("id_time");
				result.add(new DangKyLich(id, id_bacsy, ngay, id_time));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}
	
	//Thu hai den chu nhat
	public ArrayList<TimeAndID> getDaysOfWeek(){
		ArrayList<TimeAndID> result = new ArrayList<TimeAndID>();
		TimeAndID thu2 = new TimeAndID(2, "Thứ Hai");
		TimeAndID thu3 = new TimeAndID(3, "Thứ Ba");
		TimeAndID thu4 = new TimeAndID(4, "Thứ Tư");
		TimeAndID thu5 = new TimeAndID(5, "Thứ Năm");
		TimeAndID thu6 = new TimeAndID(6, "Thứ Sáu");
		TimeAndID thu7 = new TimeAndID(7, "Thứ Bảy");
		TimeAndID cn = new TimeAndID(8, "Chủ Nhật");
		result.add(thu2);
		result.add(thu3);
		result.add(thu4);
		result.add(thu5);
		result.add(thu6);
		result.add(thu7);
		result.add(cn);
		return result;
	}
	
	//Lich dat theo bac sy
	public DangKyLichInfo DangKyLichByDoctorId(int doctor_id) {
		ArrayList<TimeAndID> thu = getDaysOfWeek();
		ArrayList<TimePeriod> gio = new TimePeriodDAO().selectAll();
		ArrayList<TimeAndID> slots = new ArrayList<TimeAndID>();
		for(TimePeriod item : gio ) {
			TimeAndID slot = new TimeAndID(item.getId(), item.getStartTime().toString());
			slots.add(slot);
		}
		ArrayList<DangKyLich> dangkylich = new DangKyLichDAO().selectTimeByDoctorId(doctor_id);
		DangKyLichInfo result = new DangKyLichInfo(thu, slots, dangkylich);
		return result;
	}
	
	public int deleteByDoctorId(int doctorid) {
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "delete from dangkylich where id_bacsy = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, doctorid);
			
			rowAffected = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return rowAffected;
	}
}
