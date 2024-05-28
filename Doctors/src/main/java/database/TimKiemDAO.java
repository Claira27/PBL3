package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.TimKiem;

public class TimKiemDAO implements DAOInterface<TimKiem>{

	@Override
	public ArrayList<TimKiem> selectAll() {
		ArrayList<TimKiem> result = new ArrayList<TimKiem>();
		Connection c = DataBaseConnection.getConnection();
		String sql = "select* from timkiem";
		try {
			Statement st = c.createStatement();
			ResultSet resultSet = st.executeQuery(sql);
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				Date ngay = resultSet.getDate("ngay");
				int soluot = resultSet.getInt("soluot");
				TimKiem timkiem = new TimKiem(id, ngay, soluot);
				result.add(timkiem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}

	@Override
	public TimKiem selectById(int id) {
		TimKiem result = null;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "select * from timkiem where id = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
				Date ngay = resultSet.getDate("ngay");
				int soluot = resultSet.getInt("soluot");
				result = new TimKiem(id, ngay, soluot);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}

	@Override
	public int insert(TimKiem item) {
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "insert into timkiem(ngay, soluot) values "
					+ "(?,?);";
			PreparedStatement st = c.prepareStatement(sql);
			st.setDate(1, item.getNgay());
			st.setInt(1, item.getSoluot());
			
			rowAffected = st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return rowAffected;
	}

	@Override
	public int insertAll(ArrayList<TimKiem> ds) {
		int rowsAffected = 0;
		Connection c = DataBaseConnection.getConnection();
	    try {
	    	String sql = "insert into timkiem(ngay, soluot) values "
					+ "(?,?);";
			PreparedStatement st = c.prepareStatement(sql);
			for(TimKiem item : ds) {
				st.setDate(1, item.getNgay());
				st.setInt(1, item.getSoluot());
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
			String sql = "delete from timkiem where id = ?";
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
	public int deleteAll(ArrayList<TimKiem> ds) {
		int rowsAffected = 0;
		Connection c = DataBaseConnection.getConnection();
	    try {
	    	String sql = "delete from timkiem where id = ?";
	    	PreparedStatement st = c.prepareStatement(sql);
	    	for(TimKiem item : ds) {
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
	public int update(TimKiem item) {
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "UPDATE timkiem SET ngay = ?, soluot = ? WHERE id = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setDate(1, item.getNgay());
			st.setInt(2, item.getSoluot());
			st.setInt(3, item.getId());
			rowAffected = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return rowAffected;
	}
	
	public int TangSoLuongTrongHomNay() {
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String updateSql = "UPDATE timkiem SET soluot = soluot + 1 "
					+ "WHERE ngay = CURRENT_DATE" ;
			String insertSql = "INSERT INTO timkiem (ngay, soluot) VALUES (CURRENT_DATE, 1)";
			PreparedStatement updateStmt = c.prepareStatement(updateSql);
			rowAffected = updateStmt.executeUpdate();
			if(rowAffected == 0) {
				PreparedStatement insertStmt = c.prepareStatement(insertSql);
				rowAffected = insertStmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return rowAffected;
	}
	
	public int TongHomNay() {
		int result = 0;
		Connection c = DataBaseConnection.getConnection();
	    String sql = "SELECT * FROM timkiem "
	    		+ "WHERE ngay = CURRENT_DATE" ;
	    try {
	        PreparedStatement st = c.prepareStatement(sql);
	        ResultSet resultSet = st.executeQuery();
	        while(resultSet.next()) {
	            result = resultSet.getInt("soluot");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    DataBaseConnection.closeConnection(c);
	    return result;
	}
}
