package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import database.DangKyLichDAO;
import database.DatLichDAO;
import database.KhamTrucTuyenDAO;
import datlichkham.DatLichHandler;
import datlichkham.ServiceInfo;
import datlichkham.TimeAndID;
import model.BacSy;
import model.NguoiDung;

/**
 * Servlet implementation class history
 */
@WebServlet("/history")
public class history extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public history() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int doctorid = 0;
		int patientid = 0;
		HttpSession session = request.getSession(false);
    	if(session != null) {
    		//lay ra bac sy trong session
    		BacSy bacsy = (BacSy) session.getAttribute("bacsy");
    		if(bacsy != null) {
    			doctorid = bacsy.getId();
    		}
    		//lay ra benh nhan trong session
    		NguoiDung benhnhan = (NguoiDung)session.getAttribute("nguoiDung");
    		if(benhnhan != null) {
    			patientid = benhnhan.getId();
    		}
    	}
    	ArrayList<ServiceInfo> bookingInfo = new ArrayList<ServiceInfo>();
    	
    	if(doctorid != 0) {
    		bookingInfo = new DatLichHandler().History_BS(doctorid);
    	}else if(patientid != 0) {
    		bookingInfo = new DatLichHandler().History_BN(patientid);
    	}
    	
    	Gson gson = new Gson();
        String jsonData = gson.toJson(bookingInfo);        
        
    	// Thiết lập header cho phản hồi là JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
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
