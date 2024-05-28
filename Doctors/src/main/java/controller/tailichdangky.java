package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import database.DangKyLichDAO;
import datlichkham.DangKyLichInfo;
import model.BacSy;
import model.NguoiDung;

/**
 * Servlet implementation class tailichdangky
 */
@WebServlet("/tailichdangky")
public class tailichdangky extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tailichdangky() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Thiết lập header cho phản hồi là JSON
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		int id_bacsy = 0;
		HttpSession session = request.getSession(false);
	    if (session != null) {
	    	BacSy bacsy = (BacSy) session.getAttribute("bacsy");
	        if (bacsy != null) {
	        	id_bacsy = bacsy.getId();
	        }
	    }
	    
		DangKyLichInfo dkl = new DangKyLichDAO().DangKyLichByDoctorId(id_bacsy);
		
		// Chuyển đổi danh sách thành JSON
		Gson gson = new Gson();
		String jsonData = gson.toJson(dkl);
		
		// Gửi dữ liệu JSON về cho trình duyệt
        PrintWriter out = response.getWriter();
        out.print(jsonData);
        out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
