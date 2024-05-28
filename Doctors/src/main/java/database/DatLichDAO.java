package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.DatLich;

public class DatLichDAO implements DAOInterface<DatLich>{

	@Override
	public ArrayList<DatLich> selectAll() {
		ArrayList<DatLich> result = new ArrayList<DatLich>();
		Connection c = DataBaseConnection.getConnection();
		String sql = "select* from datlich";
		try {
			Statement st = c.createStatement();
			ResultSet resultSet = st.executeQuery(sql);
			while(resultSet.next()) {
				//ten, email, matkhau, loai, diachi, sdt, ngaysinh, gioitinh
				int id = resultSet.getInt("id");
				int id_bacsy = resultSet.getInt("id_bacsy");
				int id_benhnhan = resultSet.getInt("id_benhnhan");
				Date ngay = resultSet.getDate("ngay");
				int id_timeperiod = resultSet.getInt("id_timeperiod");
				DatLich datLich = new DatLich(id, id_bacsy, id_benhnhan, ngay, id_timeperiod);
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
	public DatLich selectById(int id) {
		DatLich result = null;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "select * from datlich where id = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
				//ten, email, matkhau, loai, diachi, sdt, ngaysinh, gioitinh
				int id_bacsy = resultSet.getInt("id_bacsy");
				int id_benhnhan = resultSet.getInt("id_benhnhan");
				Date ngay = resultSet.getDate("ngay");
				int id_timeperiod = resultSet.getInt("id_timeperiod");
				result = new DatLich(id, id_bacsy, id_benhnhan, ngay, id_timeperiod);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}
	
	@Override
	public int insert(DatLich item) {
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "insert into datlich(id_bacsy, id_benhnhan, ngay, id_timeperiod) values "
					+ "(?,?,?,?);";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, item.getIdBacSy());
			st.setInt(2, item.getIdBenhNhan());
			st.setDate(3, item.getNgay()); 	
			st.setInt(4, item.getIdTimePeriod());
			
			rowAffected = st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return rowAffected;
	}

	@Override
	public int insertAll(ArrayList<DatLich> ds) {
		int rowsAffected = 0;
		Connection c = DataBaseConnection.getConnection();
	    try {
	    	String sql = "insert into datlich(id_bacsy, id_benhnhan, ngay, id_timeperiod) values "
					+ "(?,?,?,?);";
			PreparedStatement st = c.prepareStatement(sql);
			for(DatLich item : ds) {
				st.setInt(1, item.getIdBacSy());
				st.setInt(2, item.getIdBenhNhan());
				st.setDate(3, item.getNgay()); 	
				st.setInt(4, item.getIdTimePeriod());
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
			String sql = "delete from datlich where id = ?";
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
	public int deleteAll(ArrayList<DatLich> ds) {
		int rowsAffected = 0;
		Connection c = DataBaseConnection.getConnection();
	    try {
	    	String sql = "delete from datlich where id = ?";
	    	PreparedStatement st = c.prepareStatement(sql);
	    	for(DatLich item : ds) {
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
	public int update(DatLich item) {
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "UPDATE datlich SET id_bacsy = ?, id_benhnhan = ?, ngay = ?, id_timeperiod = ? WHERE id = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, item.getIdBacSy());
			st.setInt(2, item.getIdBenhNhan());
			st.setDate(3, item.getNgay()); 	
			st.setInt(4, item.getIdTimePeriod());
			st.setInt(5, item.getId());
			rowAffected = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return rowAffected;
	}

	//chon ra lich dat sap toi cho bac sy
	public ArrayList<DatLich> selectNextByDoctorID(int id_bacsy){
		ArrayList<DatLich> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement st = null;
        ResultSet resultSet = null;

        try {
            c = DataBaseConnection.getConnection();
            String sql = "SELECT * FROM datlich WHERE id_bacsy = ? AND ngay >= CURDATE()";
            st = c.prepareStatement(sql);
            st.setInt(1, id_bacsy);
            resultSet = st.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int id_nguoidung = resultSet.getInt("id_benhnhan");
                Date ngay = resultSet.getDate("ngay");
                int id_timeperiod = resultSet.getInt("id_timeperiod");
                DatLich datlich = new DatLich(id, id_bacsy, id_nguoidung ,ngay, id_timeperiod);
                result.add(datlich);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseConnection.closeConnection(c);
        }
        return result;
	}
	
	//chon ra lich dat sap toi cua benh nha
	public ArrayList<DatLich> selectNextByPatientID(int id_nguoidung) {
		ArrayList<DatLich> result = new ArrayList<DatLich>();
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "select * from datlich where id_benhnhan = ? AND ngay >= CURDATE()";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, id_nguoidung);
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
				//ten, email, matkhau, loai, diachi, sdt, ngaysinh, gioitinh
				int id = resultSet.getInt("id");
				int id_bacsy = resultSet.getInt("id_bacsy");
				Date ngay = resultSet.getDate("ngay");
				int id_timeperiod = resultSet.getInt("id_timeperiod");
				DatLich datLich = new DatLich(id, id_bacsy, id_nguoidung, ngay, id_timeperiod);
				result.add(datLich);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}
	public DatLich selectByNguoiDung(int id_benhnhan, Date date, int timeid) {
		// TODO Auto-generated method stub
		DatLich result = null;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "select* from datlich where id_benhnhan = ? and ngay = ? and id_timeperiod = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, id_benhnhan);
			st.setDate(2, date);
			st.setInt(3, timeid);
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
				//id_bacsy, id_nguoidung, thoigian, danhgia
				int id = resultSet.getInt("id");
				int id_bacsy = resultSet.getInt("id_bacsy");
				result = new DatLich(id, id_bacsy, id_benhnhan, date, timeid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}
	public int TongTrongThang() {
		int result = 0;
		Connection c = DataBaseConnection.getConnection();
	    String sql = "SELECT COUNT(*) as num from datlich "
	    		+ "WHERE YEAR(ngay) = YEAR(CURRENT_DATE) "
	    		+ "AND MONTH(ngay) = MONTH(CURRENT_DATE)" ;
	    try {
	        PreparedStatement st = c.prepareStatement(sql);
	        ResultSet resultSet = st.executeQuery();
	        while(resultSet.next()) {
	            result = resultSet.getInt("num");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    DataBaseConnection.closeConnection(c);
	    return result;
	}
	public int[] ThongKeTheoThang() {
		int[] result = new int[12];
		
		Connection c = DataBaseConnection.getConnection();
	    String sql = "SELECT MONTH(ngay) AS month, COUNT(*) AS total " +
                "FROM datlich " +
                "GROUP BY MONTH(ngay)" ;
	    try {
	        PreparedStatement st = c.prepareStatement(sql);
	        ResultSet rs = st.executeQuery();
	        while (rs.next()) {
                int month = rs.getInt("month");
                int total = rs.getInt("total");
                result[month - 1] = total;  // Month is 1-indexed (Jan = 1, Dec = 12), array is 0-indexed
            }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    DataBaseConnection.closeConnection(c);
	    return result;
	}
}
