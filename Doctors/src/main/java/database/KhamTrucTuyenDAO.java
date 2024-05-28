package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.KhamTrucTuyen;

public class KhamTrucTuyenDAO implements DAOInterface<KhamTrucTuyen>{

	@Override
	public ArrayList<KhamTrucTuyen> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<KhamTrucTuyen> result = new ArrayList<KhamTrucTuyen>();
		Connection c = DataBaseConnection.getConnection();
		String sql = "select* from khamtructuyen";
		try {
			Statement st = c.createStatement();
			ResultSet resultSet = st.executeQuery(sql);
			while(resultSet.next()) {
				//id_bacsy, id_nguoidung, thoigian, danhgia
				int id = resultSet.getInt("id");
				int id_bacsy = resultSet.getInt("id_bacsy");
				int id_benhnhan = resultSet.getInt("id_benhnhan");
				Date ngay = resultSet.getDate("ngay");
				int id_timeperiod = resultSet.getInt("id_timeperiod");
				double phi = resultSet.getDouble("phi");
				int danhgia = resultSet.getInt("danhgia");
				
				KhamTrucTuyen khamtructuyen = new KhamTrucTuyen(id, id_bacsy, id_benhnhan, ngay, id_timeperiod, phi, danhgia);
				result.add(khamtructuyen);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}

	@Override
	public KhamTrucTuyen selectById(int id) {
		// TODO Auto-generated method stub
		KhamTrucTuyen result = null;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "select* from khamtructuyen where id = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
				//id_bacsy, id_nguoidung, thoigian, danhgia
				int id_bacsy = resultSet.getInt("id_bacsy");
				int id_benhnhan = resultSet.getInt("id_benhnhan");
				Date ngay = resultSet.getDate("ngay");
				int id_timeperiod = resultSet.getInt("id_timeperiod");
				double phi = resultSet.getDouble("phi");
				int danhgia = resultSet.getInt("danhgia");
				result = new KhamTrucTuyen(id, id_bacsy, id_benhnhan, ngay, id_timeperiod, phi, danhgia);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}

	@Override
	public int insert(KhamTrucTuyen item) {
	    int rowAffected = 0;
	    Connection c = DataBaseConnection.getConnection();
	    try {
	        // insert cuochen
	        String sql = "INSERT INTO khamtructuyen (id_bacsy, id_benhnhan, ngay, id_timeperiod, phi, danhgia) VALUES (?, ?, ?, ?, ?, ?)";
	        PreparedStatement st = c.prepareStatement(sql);
	        st.setInt(1, item.getIdBacSy());
	        st.setInt(2, item.getIdBenhNhan());
	        st.setDate(3, item.getNgay());
	        st.setInt(4, item.getIdTimePeriod());
	        st.setDouble(5, item.getPhi());
	        st.setInt(6,  item.getDanhgia());
	        rowAffected = st.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DataBaseConnection.closeConnection(c);
	    }
	    return rowAffected;
	}


	@Override
	public int insertAll(ArrayList<KhamTrucTuyen> ds) {
		int rowAffected = 0;
	    Connection c = DataBaseConnection.getConnection();
	    try {
	        // insert cuochen
	        String sql = "INSERT INTO khamtructuyen (id_bacsy, id_benhnhan, ngay, id_timeperiod, phi, danhgia) VALUES (?, ?, ?, ?, ?, ?)";
	        PreparedStatement st = c.prepareStatement(sql);
	        for(KhamTrucTuyen item : ds) {
	        	st.setInt(1, item.getIdBacSy());
		        st.setInt(2, item.getIdBenhNhan());
		        st.setDate(3, item.getNgay());
		        st.setInt(4, item.getIdTimePeriod());
		        st.setDouble(5, item.getPhi());
		        st.setInt(6,  item.getDanhgia());
		        st.addBatch();
	        }
	        int[] batchResult = st.executeBatch();
	        
	        for (int result : batchResult) {
	            rowAffected += result;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DataBaseConnection.closeConnection(c);
	    }
	    return rowAffected;
	}


	@Override
	public int deleteByID(int id) {
		// TODO Auto-generated method stub
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "delete from khamtructuyen where id = ?";
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
	public int deleteAll(ArrayList<KhamTrucTuyen> ds) {
		// TODO Auto-generated method stub
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "delete from tuvantructuyen where id = ?";
			PreparedStatement st = c.prepareStatement(sql);
			for(KhamTrucTuyen item : ds) {
				st.setInt(1, item.getId());
				st.addBatch();
			}
			int[] batchResult = st.executeBatch();
	        
	        for (int result : batchResult) {
	            rowAffected += result;
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return rowAffected;
	}

	@Override
	public int update(KhamTrucTuyen item) {
		// TODO Auto-generated method stub
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "UPDATE khamtructuyen SET id_bacsy = ?, id_benhnhan = ?, ngay = ?, id_timeperiod = ?, phi = ?, danhgia = ? WHERE id = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, item.getIdBacSy());
	        st.setInt(2, item.getIdBenhNhan());
	        st.setDate(3, item.getNgay());
	        st.setInt(4, item.getIdTimePeriod());
	        st.setDouble(5, item.getPhi());
	        st.setInt(6,  item.getDanhgia());
			st.setInt(7, item.getId());
			rowAffected = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return rowAffected;
	}
	
	//chon ra lich dat sap toi cho bac sy
	public ArrayList<KhamTrucTuyen> selectNextByDoctorID(int id_bacsy){
		ArrayList<KhamTrucTuyen> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement st = null;
        ResultSet resultSet = null;

        try {
            c = DataBaseConnection.getConnection();
            String sql = "SELECT * FROM khamtructuyen WHERE id_bacsy = ? AND ngay >= CURDATE()";
            st = c.prepareStatement(sql);
            st.setInt(1, id_bacsy);
            resultSet = st.executeQuery();
            while (resultSet.next()) {
            	int id = resultSet.getInt("id");
                int id_nguoidung = resultSet.getInt("id_benhnhan");
                Date ngay = resultSet.getDate("ngay");
                int id_timeperiod = resultSet.getInt("id_timeperiod");
                double phi = resultSet.getDouble("phi");
                int danhgia = resultSet.getInt("danhgia");
                KhamTrucTuyen khamtructuyen = new KhamTrucTuyen( id, id_bacsy, id_nguoidung, ngay, id_timeperiod, phi, danhgia);
                result.add(khamtructuyen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseConnection.closeConnection(c);
        }
        return result;
	}
	
	//chon ra lich dat sap toi cua benh nhan
	public ArrayList<KhamTrucTuyen> selectNextByPatientID(int id_nguoidung) {
		ArrayList<KhamTrucTuyen> result = new ArrayList<KhamTrucTuyen>();
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "select * from khamtructuyen where id_benhnhan = ? AND ngay >= CURDATE()";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, id_nguoidung);
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
                int id_bacsy = resultSet.getInt("id_bacsy");
                Date ngay = resultSet.getDate("ngay");
                int id_timeperiod = resultSet.getInt("id_timeperiod");
                double phi = resultSet.getDouble("phi");
                int danhgia = resultSet.getInt("danhgia");
                KhamTrucTuyen khamtructuyen = new KhamTrucTuyen( id, id_bacsy, id_nguoidung, ngay, id_timeperiod, phi, danhgia);
                result.add(khamtructuyen);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}
	
	//chon ra lich kham online da dat cho bac sy
	public ArrayList<KhamTrucTuyen> selectPrevByDoctorID(int id_bacsy){
		ArrayList<KhamTrucTuyen> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement st = null;
        ResultSet resultSet = null;

        try {
            c = DataBaseConnection.getConnection();
            String sql = "SELECT * FROM khamtructuyen WHERE id_bacsy = ? AND ngay <= CURDATE()";
            st = c.prepareStatement(sql);
            st.setInt(1, id_bacsy);
            resultSet = st.executeQuery();
            while (resultSet.next()) {
            	int id = resultSet.getInt("id");
                int id_nguoidung = resultSet.getInt("id_benhnhan");
                Date ngay = resultSet.getDate("ngay");
                int id_timeperiod = resultSet.getInt("id_timeperiod");
                double phi = resultSet.getDouble("phi");
                int danhgia = resultSet.getInt("danhgia");
                KhamTrucTuyen khamtructuyen = new KhamTrucTuyen( id, id_bacsy, id_nguoidung, ngay, id_timeperiod, phi, danhgia);
                result.add(khamtructuyen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseConnection.closeConnection(c);
        }
        return result;
	}
	
	//chon ra lich kham online da dat cua benh nhan
	public ArrayList<KhamTrucTuyen> selectPrevByPatientID(int id_nguoidung) {
		ArrayList<KhamTrucTuyen> result = new ArrayList<KhamTrucTuyen>();
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "select * from khamtructuyen where id_benhnhan = ? AND ngay <= CURDATE()";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, id_nguoidung);
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
                int id_bacsy = resultSet.getInt("id_bacsy");
                Date ngay = resultSet.getDate("ngay");
                int id_timeperiod = resultSet.getInt("id_timeperiod");
                double phi = resultSet.getDouble("phi");
                int danhgia = resultSet.getInt("danhgia");
                KhamTrucTuyen khamtructuyen = new KhamTrucTuyen( id, id_bacsy, id_nguoidung, ngay, id_timeperiod, phi, danhgia);
                result.add(khamtructuyen);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}
	
	public ArrayList<KhamTrucTuyen> selectNotRated(int id_nguoidung) {
        ArrayList<KhamTrucTuyen> result = new ArrayList<KhamTrucTuyen>();
        Connection c = DataBaseConnection.getConnection();
        try {
            String sql = "select * from khamtructuyen where id_benhnhan = ? AND danhgia = 0 AND ngay BETWEEN DATE_SUB(CURDATE(), INTERVAL 7 DAY) AND CURDATE()";
            PreparedStatement st = c.prepareStatement(sql);
            st.setInt(1, id_nguoidung);
            ResultSet resultSet = st.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                int id_bacsy = resultSet.getInt("id_bacsy");
                Date ngay = resultSet.getDate("ngay");
                int id_timeperiod = resultSet.getInt("id_timeperiod");
                double phi = resultSet.getDouble("phi");
                KhamTrucTuyen khamtructuyen = new KhamTrucTuyen(id, id_bacsy, id_nguoidung, ngay, id_timeperiod, phi, 0);
                result.add(khamtructuyen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseConnection.closeConnection(c);
        }
        return result;
    }
	
	public KhamTrucTuyen selectByNguoiDung(int id_benhnhan, Date date, int timeid) {
		// TODO Auto-generated method stub
		KhamTrucTuyen result = null;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "select* from khamtructuyen where id_benhnhan = ? AND ngay = ? AND id_timeperiod = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, id_benhnhan);
			st.setDate(2, date);
			st.setInt(3, timeid);
			ResultSet resultSet = st.executeQuery(sql);
			while(resultSet.next()) {
				//id_bacsy, id_nguoidung, thoigian, danhgia
				int id = resultSet.getInt("id");
				int id_bacsy = resultSet.getInt("id_bacsy");
				double phi = resultSet.getDouble("phi");
				int danhgia = resultSet.getInt("danhgia");
				result = new KhamTrucTuyen(id, id_bacsy, id_benhnhan, date, timeid, phi, danhgia);
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
	    String sql = "SELECT COUNT(*) as num from khamtructuyen "
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
                "FROM khamtructuyen " +
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
