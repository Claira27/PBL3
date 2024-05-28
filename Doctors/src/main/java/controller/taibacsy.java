package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import database.DoctorInfoDAO;
import model.BacSy;
import model.BenhVien;
import model.ChuyenKhoa;
import model.DoctorInfo;
import model.TinhThanh;

/**
 * Servlet implementation class taibacsy
 */
@WebServlet("/taibacsy")
public class taibacsy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public taibacsy() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    	
        List<DoctorInfo> doctors = new ArrayList<DoctorInfo>();
        
        DoctorInfoDAO bsDAO = new DoctorInfoDAO();
        doctors.addAll(bsDAO.selectAll());
        
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
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
