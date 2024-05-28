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

import datlichkham.DatLichHandler;
import datlichkham.ServiceInfo;
import model.BacSy;
import model.NguoiDung;

/**
 * Servlet implementation class notrated
 */
@WebServlet("/notrated")
public class notrated extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public notrated() {
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

		int patientid = 0;
		HttpSession session = request.getSession(false);
    	if(session != null) {
    		//lay ra benh nhan trong session
    		NguoiDung benhnhan = (NguoiDung)session.getAttribute("nguoiDung");
    		if(benhnhan != null) {
    			patientid = benhnhan.getId();
    		}
    	}
    	ArrayList<ServiceInfo> bookingInfo = new ArrayList<ServiceInfo>();
    	
    	if(patientid != 0) {
    		bookingInfo = new DatLichHandler().NotRated(patientid);
    	}
    	
    	Gson gson = new Gson();
        String jsonData = gson.toJson(bookingInfo);        
                
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
