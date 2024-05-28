package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import database.DoctorInfoDAO;
import model.BenhVien;
import model.ChuyenKhoa;
import model.DoctorInfo;
import model.TinhThanh;

/**
 * Servlet implementation class xacnhandatlich
 */
@WebServlet("/xacnhandatlich")
public class xacnhandatlich extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public xacnhandatlich() {
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
		DoctorInfo doctor = null;
		if(doctorID!=null && service != null) {
			int id = Integer.parseInt(doctorID);
			selectService = Integer.parseInt(service);
			doctor = new DoctorInfoDAO().selectById(id);
		}
		if(doctor!=null) {
			HttpSession session = request.getSession();
	        session.setAttribute("doctor", doctor);
	        session.setAttribute("selectService", selectService);
		}
		/*Gson gson = new Gson();
		String jsonData = gson.toJson();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		out.print(jsonData);
		out.flush();*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	private class Data {
        DoctorInfo doctor;
        Date date;
        int userID;
		public Data(DoctorInfo doctor, Date date, int userID) {
			super();
			this.doctor = doctor;
			this.date = date;
			this.userID = userID;
		}
		public DoctorInfo getDoctor() {
			return doctor;
		}
		public void setDoctor(DoctorInfo doctor) {
			this.doctor = doctor;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public int getUserID() {
			return userID;
		}
		public void setUserID(int userID) {
			this.userID = userID;
		}
        
        
	}
}
