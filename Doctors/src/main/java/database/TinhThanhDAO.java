package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.BenhVien;
import model.TinhThanh;

public class TinhThanhDAO implements DAOInterface<TinhThanh>{

	@Override
	public ArrayList<TinhThanh> selectAll() {
		ArrayList<TinhThanh> result = new ArrayList<TinhThanh>();
		Connection c = DataBaseConnection.getConnection();
		String sql = "select* from tinhthanh";
		try {
			Statement st = c.createStatement();
			ResultSet resultSet = st.executeQuery(sql);
			while(resultSet.next()) {
				//ten, id_tinhthanh
				int id = resultSet.getInt("id");
				String ten = resultSet.getString("ten");
				TinhThanh tinhthanh = new TinhThanh(id, ten);
				result.add(tinhthanh);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}

	@Override
	public TinhThanh selectById(int id) {
		TinhThanh result = null;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "select * from tinhthanh where id = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, id);
			
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
					//ten, id_tinhthanh
					String ten = resultSet.getString("ten");
					result = new TinhThanh(id, ten);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}

	@Override
	public int insert(TinhThanh item) {
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "insert into tinhthanh(ten) values "
					+ "(?);";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, item.getTen());
			
			rowAffected = st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return rowAffected;
	}

	@Override
	public int insertAll(ArrayList<TinhThanh> ds) {
		int rowsAffected = 0;
		Connection c = DataBaseConnection.getConnection();
	    try {
	    	String sql = "insert into tinhthanh(ten) values "
					+ "(?);";
	    	PreparedStatement st = c.prepareStatement(sql);
	    	for(TinhThanh item : ds ) {
	    		st.setString(1, item.getTen());
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
			String sql = "delete from tinhthanh where id = ?";
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
	public int deleteAll(ArrayList<TinhThanh> ds) {
		int rowsAffected = 0;
		Connection c = DataBaseConnection.getConnection();
	    try {
	    	String sql = "delete from tinhthanh where id = ?";
	    	PreparedStatement st = c.prepareStatement(sql);
	    	for(TinhThanh item : ds ) {
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
	public int update(TinhThanh item) {
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "UPDATE tinhthanh SET ten = ? WHERE id = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, item.getTen());
			st.setInt(2, item.getId());
			rowAffected = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return rowAffected;
	}

}
