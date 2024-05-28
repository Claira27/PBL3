package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Admin;
import model.NguoiDung;

public class AdminDAO implements DAOInterface<Admin>{

	@Override
	public ArrayList<Admin> selectAll() {
		ArrayList<Admin> result = new ArrayList<Admin>();
		Connection c = DataBaseConnection.getConnection();
		String sql = "select* from admin";
		try {
			Statement st = c.createStatement();
			ResultSet resultSet = st.executeQuery(sql);
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String ten = resultSet.getString("name");
				String email = resultSet.getString("email");
				String matkhau = resultSet.getString("password");
				Admin admin = new Admin(id, ten, email, matkhau);
				result.add(admin);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}

	@Override
	public Admin selectById(int id) {
		Admin result = null;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "select * from admin where id = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
				String ten = resultSet.getString("name");
				String email = resultSet.getString("email");
				String matkhau = resultSet.getString("password");
				result = new Admin(id, ten, email, matkhau);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}
	
	public Admin selectByEmailAndPassword(String email, String password) {
		Admin result = null;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "select * from admin where email = ? and password = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, email);
			st.setString(2, password);
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
				//ten, email, matkhau, loai, diachi, sdt, ngaysinh, gioitinh
				int id = resultSet.getInt("id");
				String ten = resultSet.getString("name");
				result = new Admin(id, ten, email, password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}
	public boolean kiemTraEmail(String email) {
		// TODO Auto-generated method stub
		boolean result = false;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "select * from admin where email = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, email);
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}

	@Override
	public int insert(Admin item) {
		// TODO Auto-generated method stub
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "insert into admin(name, email, password) values "
					+ "(?,?,?);";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, item.getName());
			st.setString(2, item.getEmail());
			st.setString(3, item.getPassword()); 	
			rowAffected = st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return rowAffected;
	}

	@Override
	public int insertAll(ArrayList<Admin> ds) {
		int rowsAffected = 0;
		Connection c = DataBaseConnection.getConnection();
	    try {
	    	String sql = "insert into admin(name, email, password) values "
					+ "(?,?,?);";
			PreparedStatement st = c.prepareStatement(sql);
			for(Admin item : ds) {
				st.setString(1, item.getName());
				st.setString(2, item.getEmail());
				st.setString(3, item.getPassword()); 	
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
			String sql = "delete from admin where id = ?";
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
	public int deleteAll(ArrayList<Admin> ds) {
		int rowsAffected = 0;
		Connection c = DataBaseConnection.getConnection();
	    try {
	    	String sql = "delete from admin where id = ?";
	    	PreparedStatement st = c.prepareStatement(sql);
	    	for(Admin item : ds) {
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
	public int update(Admin item) {
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "UPDATE admin SET name = ?, email = ?, password = ? id = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, item.getName());
			st.setString(2, item.getEmail());
			st.setString(3, item.getPassword());
			st.setInt(4, item.getId());
			rowAffected = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return rowAffected;
	}

}
