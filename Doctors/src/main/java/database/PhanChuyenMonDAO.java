package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.PreparableStatement;

import model.DatLich;
import model.PhanChuyenMon;

public class PhanChuyenMonDAO implements DAOInterface<PhanChuyenMon>{

	@Override
	public ArrayList<PhanChuyenMon> selectAll() {
		ArrayList<PhanChuyenMon> result = new ArrayList<PhanChuyenMon>();
		Connection c = DataBaseConnection.getConnection();
		String sql = "select* from phanchuyenmon";
		try {
			Statement st = c.createStatement();
			ResultSet resultSet = st.executeQuery(sql);
			while(resultSet.next()) {
				//ten, email, matkhau, loai, diachi, sdt, ngaysinh, gioitinh
				int id = resultSet.getInt("id");
				int id_bacsy = resultSet.getInt("id_bacsy");
				int id_chuyenkhoa = resultSet.getInt("id_chuyenkhoa");
				PhanChuyenMon phanChuyenMon = new PhanChuyenMon(id, id_bacsy, id_chuyenkhoa);
				result.add(phanChuyenMon);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}

	@Override
	public PhanChuyenMon selectById(int id) {
		PhanChuyenMon result = null;
		Connection c = DataBaseConnection.getConnection();
		
		try {
			String sql = "select* from phanchuyenmon where id = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
				//ten, email, matkhau, loai, diachi, sdt, ngaysinh, gioitinh
				int id_bacsy = resultSet.getInt("id_bacsy");
				int id_chuyenkhoa = resultSet.getInt("id_chuyenkhoa");
				result = new PhanChuyenMon(id, id_bacsy, id_chuyenkhoa);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}
	
	public ArrayList<Integer> selectByIdBacsy(int id) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Connection c = DataBaseConnection.getConnection();
		
		try {
			String sql = "select* from phanchuyenmon where id_bacsy = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
				//ten, email, matkhau, loai, diachi, sdt, ngaysinh, gioitinh
				int id_chuyenkhoa = resultSet.getInt("id_chuyenkhoa");
				result.add(id_chuyenkhoa);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}
	public ArrayList<Integer> selectByIdChuyenKhoa(int id) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Connection c = DataBaseConnection.getConnection();
		
		try {
			String sql = "select* from phanchuyenmon where id_chuyenkhoa = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
				//ten, email, matkhau, loai, diachi, sdt, ngaysinh, gioitinh
				int id_bacsy = resultSet.getInt("id_bacsy");
				result.add(id_bacsy);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}
	@Override
	public int insert(PhanChuyenMon item) {
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "insert into phanchuyenmon(id_bacsy, id_chuyenkhoa) values "
					+ "(?,?);";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, item.getId_bacsy());
			st.setInt(2, item.getId_chuyenkhoa());
			
			rowAffected = st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return rowAffected;
	}

	@Override
	public int insertAll(ArrayList<PhanChuyenMon> ds) {
		int rowsAffected = 0;
		Connection c = DataBaseConnection.getConnection();
	    try {
	    	String sql = "insert into phanchuyenmon(id_bacsy, id_chuyenkhoa) values "
					+ "(?,?);";
			PreparedStatement st = c.prepareStatement(sql);
			for(PhanChuyenMon item : ds) {
				st.setInt(1, item.getId_bacsy());
				st.setInt(2, item.getId_chuyenkhoa());
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
			String sql = "delete from phanchuyenmon where id = ?";
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
	public int deleteAll(ArrayList<PhanChuyenMon> ds) {
		int rowsAffected = 0;
		Connection c = DataBaseConnection.getConnection();
	    try {
	    	String sql = "delete from phanchuyenmon where id = ?";
	    	PreparedStatement st = c.prepareStatement(sql);
	    	for(PhanChuyenMon item : ds) {
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
	public int update(PhanChuyenMon item) {
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "UPDATE phanchuyenmon SET id_bacsy = ?, id_chuyenkhoa = ? WHERE id = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, item.getId_bacsy());
			st.setInt(2, item.getId_chuyenkhoa());
			st.setInt(3, item.getId());
			rowAffected = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return rowAffected;
	}
	

}
