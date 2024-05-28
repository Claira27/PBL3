package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.ChuyenKhoa;
import model.TinhThanh;

public class ChuyenKhoaDAO implements DAOInterface<ChuyenKhoa>{

	@Override
	public ArrayList<ChuyenKhoa> selectAll() {
		ArrayList<ChuyenKhoa> result = new ArrayList<ChuyenKhoa>();
		Connection c = DataBaseConnection.getConnection();
		String sql = "select* from chuyenkhoa";
		try {
			Statement st = c.createStatement();
			ResultSet resultSet = st.executeQuery(sql);
			while(resultSet.next()) {
				//ten, id_tinhthanh
				int id = resultSet.getInt("id");
				String ten = resultSet.getString("ten");
				ChuyenKhoa chuyenkhoa = new ChuyenKhoa(id, ten);
				result.add(chuyenkhoa);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}
	
	public ArrayList<ChuyenKhoa> selectAllByID(ArrayList<Integer> idList) {
	    ArrayList<ChuyenKhoa> result = new ArrayList<ChuyenKhoa>();
	    Connection c = DataBaseConnection.getConnection();
	    // Tạo chuỗi điều kiện "IN" từ danh sách các ID
	    StringBuilder conditionBuilder = new StringBuilder();
	    conditionBuilder.append("id IN (");
	    for (int i = 0; i < idList.size(); i++) {
	        conditionBuilder.append(idList.get(i));
	        if (i < idList.size() - 1) {
	            conditionBuilder.append(",");
	        }
	    }
	    conditionBuilder.append(")");
	    String condition = conditionBuilder.toString();
	    String sql = "SELECT * FROM chuyenkhoa WHERE " + condition;
	    try {
	        Statement st = c.createStatement();
	        ResultSet resultSet = st.executeQuery(sql);
	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String ten = resultSet.getString("ten");
	            ChuyenKhoa chuyenkhoa = new ChuyenKhoa(id, ten);
	            result.add(chuyenkhoa);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DataBaseConnection.closeConnection(c);
	    }
	    return result;
	}


	
	public ArrayList<ChuyenKhoa> selectByIdBenhvien(int id_benhvien) {
		ArrayList<ChuyenKhoa> result = new ArrayList<ChuyenKhoa>();
		ArrayList<Integer> idList = new ArrayList<Integer>();
		Connection c = DataBaseConnection.getConnection();
		
		try {
			String sql = "select* from khoathuocbenhvien where id_benhvien = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, id_benhvien);
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
				//ten, id_tinhthanh
				int id_chuyenkhoa = resultSet.getInt("id_chuyenkhoa");
				idList.add(id_chuyenkhoa);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		
		result.addAll(selectAllByID(idList));
		return result;
	}

	@Override
	public ChuyenKhoa selectById(int id) {
		ChuyenKhoa result = null;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "select * from chuyenkhoa where id = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, id);
			
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
					//ten, id_tinhthanh
					String ten = resultSet.getString("ten");
					result = new ChuyenKhoa(id, ten);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}

	@Override
	public int insert(ChuyenKhoa item) {
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "insert into chuyenkhoa(ten) values "
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
	public int insertAll(ArrayList<ChuyenKhoa> ds) {
		int rowsAffected = 0;
		Connection c = DataBaseConnection.getConnection();
	    try {
	    	String sql = "insert into chuyenkhoa(ten) values "
					+ "(?);";
	    	PreparedStatement st = c.prepareStatement(sql);
	    	for(ChuyenKhoa item : ds ) {
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
			String sql = "delete from chuyenkhoa where id = ?";
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
	public int deleteAll(ArrayList<ChuyenKhoa> ds) {
		int rowsAffected = 0;
		Connection c = DataBaseConnection.getConnection();
	    try {
	    	String sql = "delete from chuyenkhoa where id = ?";
	    	PreparedStatement st = c.prepareStatement(sql);
	    	for(ChuyenKhoa item : ds ) {
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
	public int update(ChuyenKhoa item) {
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "UPDATE chuyenkhoa SET ten = ? WHERE id = ?";
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
