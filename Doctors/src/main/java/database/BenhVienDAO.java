package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.BenhVien;

public class BenhVienDAO implements DAOInterface<BenhVien>{

	@Override
	public ArrayList<BenhVien> selectAll() {
		ArrayList<BenhVien> result = new ArrayList<BenhVien>();
		Connection c = DataBaseConnection.getConnection();
		String sql = "select* from benhvien";
		try {
			Statement st = c.createStatement();
			ResultSet resultSet = st.executeQuery(sql);
			while(resultSet.next()) {
				//ten, id_tinhthanh
				int id = resultSet.getInt("id");
				String ten = resultSet.getString("ten");
				int id_tinhthanh = resultSet.getInt("id_tinhthanh");
				BenhVien benhVien = new BenhVien(id, ten, id_tinhthanh);
				result.add(benhVien);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}
	public ArrayList<Integer> selectByIdtinhthanh(int id_tinhthanh) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Connection c = DataBaseConnection.getConnection();
		
		try {
			String sql = "select* from benhvien where id_tinhthanh = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, id_tinhthanh);
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
				//ten, id_tinhthanh
				int id = resultSet.getInt("id");
				result.add(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}
	public ArrayList<BenhVien> selectBenhVienByIdtinhthanh(int id_tinhthanh) {
		ArrayList<BenhVien> result = new ArrayList<BenhVien>();
		Connection c = DataBaseConnection.getConnection();
		
		try {
			String sql = "select* from benhvien where id_tinhthanh = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, id_tinhthanh);
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
				//ten, id_tinhthanh
				int id = resultSet.getInt("id");
				String ten = resultSet.getString("ten");
				BenhVien benhVien = new BenhVien(id, ten, id_tinhthanh);
				result.add(benhVien);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}

	@Override
	public BenhVien selectById(int id) {
		BenhVien result = null;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "select * from benhvien where id = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, id);
			
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
					//ten, id_tinhthanh
					String ten = resultSet.getString("ten");
					int id_tinhthanh = resultSet.getInt("id_tinhthanh");
					result = new BenhVien(id, ten, id_tinhthanh);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}

	@Override
	public int insert(BenhVien item) {
		// TODO Auto-generated method stub
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "insert into benhvien(ten, id_tinhthanh) values "
					+ "(?,?);";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, item.getTen());
			st.setInt(2, item.getId_tinhThanh());
			
			rowAffected = st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return rowAffected;
	}

	@Override
	public int insertAll(ArrayList<BenhVien> ds) {
		// TODO Auto-generated method stub
		int rowsAffected = 0;
		Connection c = DataBaseConnection.getConnection();
	    try {
	    	String sql = "insert into benhvien(ten, id_tinhthanh) values "
					+ "(?,?);";
	    	PreparedStatement st = c.prepareStatement(sql);
	    	for(BenhVien item : ds ) {
	    		st.setString(1, item.getTen());
				st.setInt(2, item.getId_tinhThanh());
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
		// TODO Auto-generated method stub
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "delete from benhvien where id = ?";
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
	public int deleteAll(ArrayList<BenhVien> ds) {
		// TODO Auto-generated method stub
		int rowsAffected = 0;
		Connection c = DataBaseConnection.getConnection();
	    try {
	    	String sql = "delete from benhvien where id = ?";
	    	PreparedStatement st = c.prepareStatement(sql);
	    	for(BenhVien item : ds ) {
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
	public int update(BenhVien item) {
		// TODO Auto-generated method stub
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "UPDATE benhvien SET ten = ?, id_tinhthanh = ? WHERE id = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, item.getTen());
			st.setInt(2, item.getId_tinhThanh());
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

