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

import database.DoctorInfoDAO;
import datlichkham.DatLichHandler;
import datlichkham.TimeInfo;
import model.DoctorInfo;

/**
 * Servlet implementation class thoigiandatlich
 */
@WebServlet("/thoigiandatlich")
public class thoigiandatlich extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public thoigiandatlich() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		String doctorID = request.getParameter("doctorId");
		String service = request.getParameter("service");
		int selectService = 0;
		int id=0;
		DoctorInfo doctor = null;
		if(doctorID!=null && service != null) {
			id = Integer.parseInt(doctorID);
			selectService = Integer.parseInt(service);
			doctor = new DoctorInfoDAO().selectById(id);
		}
		if(doctor!=null) {
			HttpSession session = request.getSession();
	        session.setAttribute("doctor", doctor);
	        session.setAttribute("selectService", selectService);
		}
		
		ArrayList<TimeInfo> timeInfo = null;
		
		if(selectService == 1) {
			timeInfo = new DatLichHandler().selectFreeServiceSlotByDoctorID(id);
		}else {
			timeInfo = new DatLichHandler().selectFreeBookingSlotByDoctorID(id);
		}
		
		Data data = new Data(doctor, timeInfo, selectService);
		Gson gson = new Gson();
		String jsonData = gson.toJson(data);

		// Thiết lập header cho phản hồi là JSON
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		// Gửi dữ liệu JSON về cho trình duyệt
        PrintWriter out = response.getWriter();
        out.print(jsonData);
        out.flush();
	}
	private class Data {
		DoctorInfo doctor;
		ArrayList<TimeInfo> timeInfo;
		int service;
		
		public Data(DoctorInfo doctor, ArrayList<TimeInfo> timeInfo, int service) {
			super();
			this.doctor = doctor;
			this.timeInfo = timeInfo;
			this.service = service;
		}
		public Data() {
			// TODO Auto-generated constructor stub
		}
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
