package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.BacSy;
import model.BacSyTichCuc;
import model.DoctorInfo;
import model.NguoiDung;
import model.NguoiDungTichCuc;

public class NguoiDungDAO implements DAOInterface<NguoiDung>{

	@Override
	public ArrayList<NguoiDung> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<NguoiDung> result = new ArrayList<NguoiDung>();
		Connection c = DataBaseConnection.getConnection();
		String sql = "select* from nguoidung";
		try {
			Statement st = c.createStatement();
			ResultSet resultSet = st.executeQuery(sql);
			while(resultSet.next()) {
				//ten, email, matkhau, loai, diachi, sdt, ngaysinh, gioitinh
				int id = resultSet.getInt("id");
				String ten = resultSet.getString("ten");
				String email = resultSet.getString("email");
				String matkhau = resultSet.getString("matkhau");
				int loai = resultSet.getInt("loai");
				String diachi = resultSet.getString("diachi");
				String sdt = resultSet.getString("sdt");
				Date ngaysinh = resultSet.getDate("ngaysinh");
				String gioitinh = resultSet.getString("gioitinh");
				NguoiDung nguoiDung = new NguoiDung(id, ten, email, matkhau, loai, diachi, sdt, ngaysinh, gioitinh);
				result.add(nguoiDung);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}

	@Override
	public NguoiDung selectById(int id) {
		// TODO Auto-generated method stub
		NguoiDung result = null;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "select * from nguoidung where id = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
				//ten, email, matkhau, loai, diachi, sdt, ngaysinh, gioitinh
				String ten = resultSet.getString("ten");
				String email = resultSet.getString("email");
				String matkhau = resultSet.getString("matkhau");
				int loai = resultSet.getInt("loai");
				String diachi = resultSet.getString("diachi");
				String sdt = resultSet.getString("sdt");
				Date ngaysinh = resultSet.getDate("ngaysinh");
				String gioitinh = resultSet.getString("gioitinh");
				result = new NguoiDung(id, ten, email, matkhau, loai, diachi, sdt, ngaysinh, gioitinh);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}
	
	public NguoiDung selectByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		NguoiDung result = null;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "select * from nguoidung where email = ? and matkhau = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, email);
			st.setString(2, password);
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
				//ten, email, matkhau, loai, diachi, sdt, ngaysinh, gioitinh
				int id = resultSet.getInt("id");
				String ten = resultSet.getString("ten");
				int loai = resultSet.getInt("loai");
				String diachi = resultSet.getString("diachi");
				String sdt = resultSet.getString("sdt");
				Date ngaysinh = resultSet.getDate("ngaysinh");
				String gioitinh = resultSet.getString("gioitinh");
				result = new NguoiDung(id, ten, email, password, loai, diachi, sdt, ngaysinh, gioitinh);
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
			String sql = "select * from nguoidung where email = ?";
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
	public int insert(NguoiDung item) {
		// TODO Auto-generated method stub
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "insert into nguoidung(ten, email, matkhau, loai, diachi, sdt, ngaysinh, gioitinh) values "
					+ "(?,?,?,?,?,?,?,?);";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, item.getTen());
			st.setString(2, item.getEmail());
			st.setString(3, item.getPassword()); 	
			st.setInt(4, item.getLoai());
			st.setString(5, item.getDiaChi());
			st.setString(6, item.getSdt());
			st.setDate(7, item.getNgaySinh());
			st.setString(8, item.getGioiTinh());
			
			rowAffected = st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return rowAffected;
	}

	@Override
	public int insertAll(ArrayList<NguoiDung> ds) {
		// TODO Auto-generated method stub
		int rowsAffected = 0;
		Connection c = DataBaseConnection.getConnection();
	    try {
	    	String sql = "insert into nguoidung(ten, email, matkhau, loai, diachi, sdt, ngaysinh, gioitinh) values "
					+ "(?,?,?,?,?,?,?,?);";
			PreparedStatement st = c.prepareStatement(sql);
			for(NguoiDung item : ds) {
				st.setString(1, item.getTen());
				st.setString(2, item.getEmail());
				st.setString(3, item.getPassword()); 	
				st.setInt(4, item.getLoai());
				st.setString(5, item.getDiaChi());
				st.setString(6, item.getSdt());
				st.setDate(7, item.getNgaySinh());
				st.setString(8, item.getGioiTinh());
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
			String sql = "delete from nguoidung where id = ?";
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
	public int deleteAll(ArrayList<NguoiDung> ds) {
		// TODO Auto-generated method stub
		int rowsAffected = 0;
		Connection c = DataBaseConnection.getConnection();
	    try {
	    	String sql = "delete from nguoidung where id = ?";
	    	PreparedStatement st = c.prepareStatement(sql);
	    	for(NguoiDung item : ds) {
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
	public int update(NguoiDung item) {
		// TODO Auto-generated method stub
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "UPDATE nguoidung SET ten = ?, email = ?, matkhau = ?, loai = ?, diachi = ?, sdt = ?, ngaysinh = ?, gioitinh = ? WHERE id = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, item.getTen());
			st.setString(2, item.getEmail());
			st.setString(3, item.getPassword());
			st.setInt(4, item.getLoai());
			st.setString(5, item.getDiaChi());
			st.setString(6, item.getSdt());
			st.setDate(7, item.getNgaySinh());
			st.setString(8, item.getGioiTinh());
			st.setInt(9, item.getId());
			rowAffected = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return rowAffected;
	}
	public ArrayList<NguoiDungTichCuc> NguoiDungNhieuNhat(){
		ArrayList<NguoiDungTichCuc> result = new ArrayList<>();
        Map<Integer, Integer> userCountMap = new HashMap<>();
        
        Connection c = DataBaseConnection.getConnection();
        try {
            // Query to get counts from datlich table
            String sql1 = "SELECT id_benhnhan, COUNT(id_benhnhan) as count " +
                          "FROM datlich " +
                          "GROUP BY id_benhnhan";
            PreparedStatement st1 = c.prepareStatement(sql1);
            ResultSet rs1 = st1.executeQuery();
            while (rs1.next()) {
                int id = rs1.getInt("id_benhnhan");
                int count = rs1.getInt("count");
                userCountMap.put(id, userCountMap.getOrDefault(id, 0) + count);
            }
            rs1.close();
            st1.close();

            // Query to get counts from khamtructuyen table
            String sql2 = "SELECT id_benhnhan, COUNT(id_benhnhan) as count " +
                          "FROM khamtructuyen " +
                          "GROUP BY id_benhnhan";
            PreparedStatement st2 = c.prepareStatement(sql2);
            ResultSet rs2 = st2.executeQuery();
            while (rs2.next()) {
                int id = rs2.getInt("id_benhnhan");
                int count = rs2.getInt("count");
                userCountMap.put(id, userCountMap.getOrDefault(id, 0) + count);
            }
            rs2.close();
            st2.close();
        } catch (SQLException e) {
            e.printStackTrace();  // Handle exceptions appropriately
        } finally {
            DataBaseConnection.closeConnection(c);
        }

        // Find the top 5 IDs with the highest counts
        userCountMap.entrySet()
                    .stream()
                    .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))  // Sort by count descending
                    .limit(5)
                    .forEach(entry -> {
                        int id = entry.getKey();
                        NguoiDung nd = new NguoiDungDAO().selectById(id);
                        NguoiDungTichCuc nguoidung = new NguoiDungTichCuc(nd.getId(), nd.getTen(), nd.getDiaChi(), nd.getNgaySinh(), entry.getValue());
                        result.add(nguoidung);
                    });

        return result;
	}
	
	public ArrayList<NguoiDungTichCuc> selectByTen(String chuoi_ten) {
	    ArrayList<NguoiDungTichCuc> result = new ArrayList<NguoiDungTichCuc>();
	    ArrayList<NguoiDung> list = new ArrayList<NguoiDung>();
	    Connection c = DataBaseConnection.getConnection();
	    // Chuyển chuỗi tìm kiếm thành chữ thường
	    chuoi_ten = chuoi_ten.toLowerCase();
	    String sql = "SELECT * FROM nguoidung WHERE LOWER(ten) LIKE ?";
	    try {
	        PreparedStatement st = c.prepareStatement(sql);
	        // Thêm dấu % vào chuỗi tìm kiếm
	        st.setString(1, "%" + chuoi_ten + "%");
	        ResultSet resultSet = st.executeQuery();
	        while(resultSet.next()) {
	        	int id = resultSet.getInt("id");
				String ten = resultSet.getString("ten");
				String email = resultSet.getString("email");
				String matkhau = resultSet.getString("matkhau");
				int loai = resultSet.getInt("loai");
				String diachi = resultSet.getString("diachi");
				String sdt = resultSet.getString("sdt");
				Date ngaysinh = resultSet.getDate("ngaysinh");
				String gioitinh = resultSet.getString("gioitinh");
				NguoiDung nguoiDung = new NguoiDung(id, ten, email, matkhau, loai, diachi, sdt, ngaysinh, gioitinh);
				list.add(nguoiDung);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    for (NguoiDung item : list) {
	    	int totalDatLich = getTotalDatLich(item.getId());
            int totalKhamTrucTuyen = getTotalKhamTrucTuyen(item.getId());
            NguoiDungTichCuc nguoidung = new NguoiDungTichCuc(item.getId(), item.getTen(), item.getDiaChi(), item.getNgaySinh(), totalDatLich+totalKhamTrucTuyen); 
            result.add(nguoidung);
        }
	    DataBaseConnection.closeConnection(c);
	    return result;
	}
	private int getTotalDatLich(int userId) {
		int totalCount = 0;
        Connection c = DataBaseConnection.getConnection();
        String sql = "SELECT COUNT(*) AS total FROM datlich WHERE id_benhnhan = ?";
        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                totalCount = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DataBaseConnection.closeConnection(c);
        return totalCount;
    }
	private int getTotalKhamTrucTuyen(int userId) {
		int totalCount = 0;
        Connection c = DataBaseConnection.getConnection();
        String sql = "SELECT COUNT(*) AS total FROM khamtructuyen WHERE id_benhnhan = ?";
        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                totalCount = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DataBaseConnection.closeConnection(c);
        return totalCount;
    }
	public int TongNguoiDung() {
		int result = 0;
		Connection c = DataBaseConnection.getConnection();
	    String sql = "SELECT COUNT(*) as num from nguoidung ";
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
}
