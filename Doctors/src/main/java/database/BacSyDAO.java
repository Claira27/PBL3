package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.BacSy;
import model.BacSyTichCuc;
import model.DoctorInfo;
import model.NguoiDung;
import model.NguoiDungTichCuc;

public class BacSyDAO implements DAOInterface<BacSy>{
	
	@Override
	public ArrayList<BacSy> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<BacSy> result = new ArrayList<BacSy>();
		Connection c = DataBaseConnection.getConnection();
		String sql = "select* from bacsy";
		try {
			Statement st = c.createStatement();
			ResultSet resultSet = st.executeQuery(sql);
			while(resultSet.next()) {
				//ten, email, matkhau, loai, id_benhvien, id_chuyenmon, bangcap, namkinhnghiem, luotdanhgia, danhgia
				int id = resultSet.getInt("id");
				String ten = resultSet.getString("ten");
				String email = resultSet.getString("email");
				String matkhau = resultSet.getString("matkhau");
				int loai = resultSet.getInt("loai");
				int id_benhvien = resultSet.getInt("id_benhvien");
				String bangcap = resultSet.getString("bangcap");
				int namkinhnghiem = resultSet.getInt("namkinhnghiem");
				int luotdanhgia = resultSet.getInt("luotdanhgia");
				double danhgia = resultSet.getDouble("danhgia");
				String hinhanh = resultSet.getString("hinhanh");
				int dichvu = resultSet.getInt("dichvu");
				double giadichvu = resultSet.getDouble("giadichvu");
				BacSy bacSy = new BacSy(id, ten, email, matkhau, loai, id_benhvien, bangcap, namkinhnghiem, luotdanhgia, danhgia, hinhanh, dichvu, giadichvu );
				result.add(bacSy);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}
	
	public ArrayList<BacSy> selectByMultipleIds(ArrayList<Integer> idList) {
	    ArrayList<BacSy> result = new ArrayList<BacSy>();
	    Connection c = DataBaseConnection.getConnection();
	    
	    // Xây dựng câu lệnh SQL sử dụng IN để chọn các bác sỹ có id nằm trong danh sách idList
	    StringBuilder sql = new StringBuilder("SELECT * FROM bacsy WHERE id IN (");
	    for (int i = 0; i < idList.size(); i++) {
	        sql.append(idList.get(i));
	        if (i < idList.size() - 1) {
	            sql.append(",");
	        }
	    }
	    sql.append(")");
	    
	    try {
	        Statement st = c.createStatement();
	        ResultSet resultSet = st.executeQuery(sql.toString());
	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String ten = resultSet.getString("ten");
	            String email = resultSet.getString("email");
	            String matkhau = resultSet.getString("matkhau");
	            int loai = resultSet.getInt("loai");
	            int id_benhvien = resultSet.getInt("id_benhvien");
	            String bangcap = resultSet.getString("bangcap");
	            int namkinhnghiem = resultSet.getInt("namkinhnghiem");
	            int luotdanhgia = resultSet.getInt("luotdanhgia");
	            double danhgia = resultSet.getDouble("danhgia");
	            String hinhanh = resultSet.getString("hinhanh");
	            int dichvu = resultSet.getInt("dichvu");
	            double giadichvu = resultSet.getDouble("giadichvu");
	            BacSy bacSy = new BacSy(id, ten, email, matkhau, loai, id_benhvien, bangcap, namkinhnghiem, luotdanhgia, danhgia, hinhanh, dichvu,giadichvu);
	            result.add(bacSy);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    DataBaseConnection.closeConnection(c);
	    return result;
	}

	public ArrayList<BacSy> selectByMultipleIdBenhvien(ArrayList<Integer> idList) {
	    ArrayList<BacSy> result = new ArrayList<BacSy>();
	    Connection c = DataBaseConnection.getConnection();
	    
	    // Xây dựng câu lệnh SQL sử dụng IN để chọn các bác sỹ có id nằm trong danh sách idList
	    StringBuilder sql = new StringBuilder("SELECT * FROM bacsy WHERE id_benhvien IN (");
	    for (int i = 0; i < idList.size(); i++) {
	        sql.append(idList.get(i));
	        if (i < idList.size() - 1) {
	            sql.append(",");
	        }
	    }
	    sql.append(")");
	    
	    try {
	        Statement st = c.createStatement();
	        ResultSet resultSet = st.executeQuery(sql.toString());
	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String ten = resultSet.getString("ten");
	            String email = resultSet.getString("email");
	            String matkhau = resultSet.getString("matkhau");
	            int loai = resultSet.getInt("loai");
	            int id_benhvien = resultSet.getInt("id_benhvien");
	            String bangcap = resultSet.getString("bangcap");
	            int namkinhnghiem = resultSet.getInt("namkinhnghiem");
	            int luotdanhgia = resultSet.getInt("luotdanhgia");
	            double danhgia = resultSet.getDouble("danhgia");
	            String hinhanh = resultSet.getString("hinhanh");
	            int dichvu = resultSet.getInt("dichvu");
	            double giadichvu = resultSet.getDouble("giadichvu");
	            BacSy bacSy = new BacSy(id, ten, email, matkhau, loai, id_benhvien, bangcap, namkinhnghiem, luotdanhgia, danhgia, hinhanh, dichvu,giadichvu);
	            result.add(bacSy);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    DataBaseConnection.closeConnection(c);
	    return result;
	}
	
	@Override
	public BacSy selectById(int id) {
		BacSy result = null;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "select * from bacsy where id = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, id);
			
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
				//ten, email, matkhau, loai, id_benhvien, id_chuyenmon, bangcap, namkinhnghiem, luotdanhgia, danhgia
				String ten = resultSet.getString("ten");
				String email = resultSet.getString("email");
				String matkhau = resultSet.getString("matkhau");
				int loai = resultSet.getInt("loai");
				int id_benhvien = resultSet.getInt("id_benhvien");
				String bangcap = resultSet.getString("bangcap");
				int namkinhnghiem = resultSet.getInt("namkinhnghiem");
				int luotdanhgia = resultSet.getInt("luotdanhgia");
				double danhgia = resultSet.getDouble("danhgia");
				String hinhanh = resultSet.getString("hinhanh");
				int dichvu = resultSet.getInt("dichvu");
				double giadichvu = resultSet.getDouble("giadichvu");
				result = new BacSy(id, ten, email, matkhau, loai, id_benhvien, bangcap, namkinhnghiem, luotdanhgia, danhgia, hinhanh, dichvu,giadichvu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}
	
	public BacSy selectByEmailAndPassword(String email, String password) {
		BacSy result = null;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "select * from bacsy where email = ? and matkhau = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, email);
			st.setString(2, password);
			
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
				//ten, email, matkhau, loai, id_benhvien, id_chuyenmon, bangcap, namkinhnghiem, luotdanhgia, danhgia
				int id = resultSet.getInt("id");
				String ten = resultSet.getString("ten");
				int loai = resultSet.getInt("loai");
				int id_benhvien = resultSet.getInt("id_benhvien");
				String bangcap = resultSet.getString("bangcap");
				int namkinhnghiem = resultSet.getInt("namkinhnghiem");
				int luotdanhgia = resultSet.getInt("luotdanhgia");
				double danhgia = resultSet.getDouble("danhgia");
				String hinhanh = resultSet.getString("hinhanh");
				int dichvu = resultSet.getInt("dichvu");
				double giadichvu = resultSet.getDouble("giadichvu");
				result = new BacSy(id, ten, email, password, loai, id_benhvien, bangcap, namkinhnghiem, luotdanhgia, danhgia, hinhanh, dichvu,giadichvu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}
	
	public ArrayList<BacSy> selectByIdBenhvien(int id_benhvien) {
		ArrayList<BacSy> result = new ArrayList<BacSy>();
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "select * from bacsy where id_benhvien = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, id_benhvien);
			
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next()) {
				//ten, email, matkhau, loai, id_benhvien, id_chuyenmon, bangcap, namkinhnghiem, luotdanhgia, danhgia
				int id = resultSet.getInt("id");
				String ten = resultSet.getString("ten");
				String email = resultSet.getString("email");
				String matkhau = resultSet.getString("matkhau");
				int loai = resultSet.getInt("loai");
				String bangcap = resultSet.getString("bangcap");
				int namkinhnghiem = resultSet.getInt("namkinhnghiem");
				int luotdanhgia = resultSet.getInt("luotdanhgia");
				double danhgia = resultSet.getDouble("danhgia");
				String hinhanh = resultSet.getString("hinhanh");
				int dichvu = resultSet.getInt("dichvu");
				double giadichvu = resultSet.getDouble("giadichvu");
				BacSy bacsy = new BacSy(id, ten, email, matkhau, loai, id_benhvien, bangcap, namkinhnghiem, luotdanhgia, danhgia, hinhanh, dichvu,giadichvu);
				result.add(bacsy);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return result;
	}

	@Override
	public int insert(BacSy item) {
		// TODO Auto-generated method stub
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "insert into bacsy(ten, email, matkhau, loai, id_benhvien, bangcap, namkinhnghiem, luotdanhgia, danhgia, hinhanh, dichvu, giadichvu) values "
					+ "(?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, item.getTen());
			st.setString(2, item.getEmail());
			st.setString(3, item.getPassword());
			st.setInt(4, item.getLoai());
			st.setInt(5, item.getId_benhVien());
			st.setString(6, item.getBangCap());
			st.setInt(7, item.getNamKinhNghiem());
			st.setInt(8, item.getLuotDanhGia());
			st.setDouble(9, item.getDanhGia());
			st.setString(10, item.getHinhanh());
			st.setInt(11, item.getDichvu());
			st.setDouble(12, item.getGiadichvu());
			
			rowAffected = st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return rowAffected;
	}

	@Override
	public int insertAll(ArrayList<BacSy> ds) {
		int rowsAffected = 0;
		Connection c = DataBaseConnection.getConnection();
	    try {
	    	String sql = "insert into bacsy(ten, email, matkhau, loai, id_benhvien, bangcap, namkinhnghiem, luotdanhgia, danhgia, hinhanh, dichvu, giadichvu) values "
					+ "(?,?,?,?,?,?,?,?,?,?,?);";
	    	PreparedStatement st = c.prepareStatement(sql);
	    	for(BacSy item : ds ) {
				st.setString(1, item.getTen());
				st.setString(2, item.getEmail());
				st.setString(3, item.getPassword());
				st.setInt(4, item.getLoai());
				st.setInt(5, item.getId_benhVien());
				st.setString(6, item.getBangCap());
				st.setInt(7, item.getNamKinhNghiem());
				st.setInt(8, item.getLuotDanhGia());
				st.setDouble(9, item.getDanhGia());
				st.setString(10, item.getHinhanh());
				st.setInt(11, item.getDichvu());
				st.setDouble(12, item.getGiadichvu());
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
			String sql = "delete from bacsy where id = ?";
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
	public int deleteAll(ArrayList<BacSy> ds) {
		// TODO Auto-generated method stub
		int rowsAffected = 0;
		Connection c = DataBaseConnection.getConnection();
	    try {
	    	String sql = "delete from bacsy where id = ?";
	    	PreparedStatement st = c.prepareStatement(sql);
	    	for(BacSy item : ds ) {
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
	public int update(BacSy item) {
		int rowAffected = 0;
		Connection c = DataBaseConnection.getConnection();
		try {
			String sql = "UPDATE bacsy SET ten = ?, email = ?, matkhau = ?, loai = ?, id_benhvien = ?, bangcap = ?, namkinhnghiem = ?, luotdanhgia = ?, danhgia = ?, hinhanh = ?, dichvu = ?, giadichvu = ? WHERE id = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, item.getTen());
			st.setString(2, item.getEmail());
			st.setString(3, item.getPassword());
			st.setInt(4, item.getLoai());
			st.setInt(5, item.getId_benhVien());
			st.setString(6, item.getBangCap());
			st.setInt(7, item.getNamKinhNghiem());
			st.setInt(8, item.getLuotDanhGia());
			st.setDouble(9, item.getDanhGia());
			st.setString(10, item.getHinhanh());
			st.setInt(11, item.getDichvu());
			st.setDouble(12, item.getGiadichvu());
			st.setInt(13, item.getId());
			rowAffected = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseConnection.closeConnection(c);
		return rowAffected;
	}

	
	public ArrayList<BacSyTichCuc> BacSyNhieuNhat(){
		ArrayList<BacSyTichCuc> result = new ArrayList<>();
        Map<Integer, Integer> userCountMap = new HashMap<>();
        
        Connection c = DataBaseConnection.getConnection();
        try {
            // Query to get counts from datlich table
            String sql1 = "SELECT id_bacsy, COUNT(id_bacsy) as count " +
                          "FROM datlich " +
                          "GROUP BY id_bacsy";
            PreparedStatement st1 = c.prepareStatement(sql1);
            ResultSet rs1 = st1.executeQuery();
            while (rs1.next()) {
                int id = rs1.getInt("id_bacsy");
                int count = rs1.getInt("count");
                userCountMap.put(id, userCountMap.getOrDefault(id, 0) + count);
            }
            rs1.close();
            st1.close();

            // Query to get counts from khamtructuyen table
            String sql2 = "SELECT id_bacsy, COUNT(id_bacsy) as count " +
                          "FROM khamtructuyen " +
                          "GROUP BY id_bacsy";
            PreparedStatement st2 = c.prepareStatement(sql2);
            ResultSet rs2 = st2.executeQuery();
            while (rs2.next()) {
                int id = rs2.getInt("id_bacsy");
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
                        DoctorInfo bs = new DoctorInfoDAO().selectById(id);
                        BacSyTichCuc bacsy = new BacSyTichCuc(bs.getId(), bs.getImageUrl(), bs.getName(), bs.getUnit(), bs.getRating().getStars(), entry.getValue());
                        result.add(bacsy);
                    });

        return result;
	}
	
	public ArrayList<BacSyTichCuc> selectByTen(String chuoi_ten) {
	    ArrayList<BacSyTichCuc> result = new ArrayList<BacSyTichCuc>();
	    ArrayList<BacSy> list = new ArrayList<BacSy>();
	    Connection c = DataBaseConnection.getConnection();
	    // Chuyển chuỗi tìm kiếm thành chữ thường
	    chuoi_ten = chuoi_ten.toLowerCase();
	    String sql = "SELECT * FROM bacsy WHERE LOWER(ten) LIKE ?";
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
				int id_benhvien = resultSet.getInt("id_benhvien");
				String bangcap = resultSet.getString("bangcap");
				int namkinhnghiem = resultSet.getInt("namkinhnghiem");
				int luotdanhgia = resultSet.getInt("luotdanhgia");
				double danhgia = resultSet.getDouble("danhgia");
				String hinhanh = resultSet.getString("hinhanh");
				int dichvu = resultSet.getInt("dichvu");
				double giadichvu = resultSet.getDouble("giadichvu");
				BacSy bacSy = new BacSy(id, ten, email, matkhau, loai, id_benhvien, bangcap, namkinhnghiem, luotdanhgia, danhgia, hinhanh, dichvu, giadichvu );
				list.add(bacSy);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    for (BacSy item : list) {
	    	DoctorInfo bs = new DoctorInfoDAO().selectById(item.getId());
	    	int totalDatLich = getTotalDatLich(item.getId());
            int totalKhamTrucTuyen = getTotalKhamTrucTuyen(item.getId());
            BacSyTichCuc bacsy = new BacSyTichCuc(bs.getId(), bs.getImageUrl(), bs.getName(), bs.getUnit(), bs.getRating().getStars(), totalDatLich+totalKhamTrucTuyen);
            
            result.add(bacsy);
        }
	    DataBaseConnection.closeConnection(c);
	    return result;
	}
	private int getTotalDatLich(int doctorId) {
		int totalCount = 0;
        Connection c = DataBaseConnection.getConnection();
        String sql = "SELECT COUNT(*) AS total FROM datlich WHERE id_bacsy = ?";
        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setInt(1, doctorId);
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
	private int getTotalKhamTrucTuyen(int doctorId) {
		int totalCount = 0;
        Connection c = DataBaseConnection.getConnection();
        String sql = "SELECT COUNT(*) AS total FROM khamtructuyen WHERE id_bacsy = ?";
        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setInt(1, doctorId);
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
	public int TongBacSy() {
		int result = 0;
		Connection c = DataBaseConnection.getConnection();
	    String sql = "SELECT COUNT(*) as num from bacsy ";
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
