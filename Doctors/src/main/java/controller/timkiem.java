package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import database.BacSyDAO;
import database.BenhVienDAO;
import database.DoctorInfoDAO;
import database.PhanChuyenMonDAO;
import database.TimKiemDAO;
import model.BacSy;
import model.DoctorInfo;

/**
 * Servlet implementation class timkiem
 */
@WebServlet("/timkiem")
public class timkiem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public timkiem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//cap nhat luot tim kiem trong ngay
		TimKiemDAO tkDAO = new TimKiemDAO();
		tkDAO.TangSoLuongTrongHomNay();
		
		//kiem tra tai khoan dang dung co phai bac sy
		int isdoctor = 0;
    	HttpSession session = request.getSession(false);
    	if(session == null) {
    		isdoctor = 0;
    	} else {
    		BacSy bacsy = (BacSy) session.getAttribute("bacsy");
    		if(bacsy == null) {
    			isdoctor = 0;
    		}else {
    			isdoctor = 1;
    		}
    	}
		BufferedReader reader = request.getReader();
        JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
        String id_area = jsonObject.get("area").getAsString();
        String id_hospital = jsonObject.get("hospital").getAsString();
        String id_specialty = jsonObject.get("specialty").getAsString();
        String doctorname = jsonObject.get("doctorname").getAsString();
		
		ArrayList<BacSy> doctors1 = new ArrayList<BacSy>();
		ArrayList<BacSy> doctors2 = new ArrayList<BacSy>();
		ArrayList<BacSy> doctors3 = new ArrayList<BacSy>();
		ArrayList<Integer> doctorIds = new ArrayList<Integer>();
		
		int idArea = 0;
		int idHospital = 0;
		int idSpecialty = 0;

		// Chuyển đổi từ String sang int nếu không null
		if (id_area != null && !id_area.isEmpty()) {
		    idArea = Integer.parseInt(id_area);
		}
		if (id_hospital != null && !id_hospital.isEmpty()) {
		    idHospital = Integer.parseInt(id_hospital);
		}
		if (id_specialty != null && !id_specialty.isEmpty()) {
		    idSpecialty = Integer.parseInt(id_specialty);
		}
		
		//search theo id_specialty
		
		if(idSpecialty!=0) {
			ArrayList<Integer> idList = new PhanChuyenMonDAO().selectByIdChuyenKhoa(idSpecialty);
			doctors1 = new BacSyDAO().selectByMultipleIds(idList);
		}else {
			doctors1.addAll(new BacSyDAO().selectAll());
		}
		
		//search theo id_hospital
		if(idHospital != 0) {
			doctors2 = new BacSyDAO().selectByIdBenhvien(idHospital);
		}else if(idArea != 0) {
			ArrayList<Integer> idList = new BenhVienDAO().selectByIdtinhthanh(idArea);
			doctors2.addAll(new BacSyDAO().selectByMultipleIdBenhvien(idList));
		}else {
			doctors2.addAll(new BacSyDAO().selectAll());
		}
		
		for(BacSy bacsy : doctors1) {
			if(doctors2.contains(bacsy)) {
				doctors3.add(bacsy);
			}
		}
		
		for(BacSy bacsy : doctors3) {
			if(doctorname.trim().length()==0 || bacsy.getTen().contains(doctorname.trim())) {
				doctorIds.add(bacsy.getId());
			}
		}
		//chuyen doi thanh doctoInfo
		List<DoctorInfo> doctors = new ArrayList<DoctorInfo>();
		DoctorInfoDAO dfDAO = new DoctorInfoDAO();
		for(int id : doctorIds) {
			DoctorInfo item = dfDAO.selectById(id);
			if(item!=null) {
				doctors.add(item);
			}
		}

		int page = 1; // Giá trị mặc định cho trường hợp không có tham số page
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            page = Integer.parseInt(pageParam);
        }

        int pageSize = 10; // Số lượng bac sy trên mỗi trang
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, doctors.size());
        List<DoctorInfo> doctorsPerPage = doctors.subList(startIndex, endIndex);
        
        int totalPage = (int) Math.ceil((double)doctors.size()/ pageSize);
        // Chuyển đổi danh sách thành JSON
        Gson gson = new Gson();
        String jsonData = gson.toJson(new Data(page, totalPage, isdoctor, doctorsPerPage));
        
        // Thiết lập header cho phản hồi là JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // Gửi dữ liệu JSON về cho trình duyệt
        PrintWriter out = response.getWriter();
        out.print(jsonData);
        out.flush();
	}

	private class Data {
        int currentPage;
        int totalPage;
        int isdoctor;
        List<DoctorInfo> doctors;
        
        public Data(int currentPage, int totalPage, int isdoctor, List<DoctorInfo> doctors) {
            this.currentPage = currentPage;
            this.totalPage = totalPage;
            this.isdoctor = isdoctor;
            this.doctors = doctors;
        }
	}
}
