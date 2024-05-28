package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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
 * Servlet implementation class taodatlich
 */
@WebServlet("/taodatlich")
public class taodatlich extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public taodatlich() {
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
		int dateid = 0;
		dateid=Integer.parseInt(request.getParameter("date"));
		int timeid = 0;
		timeid=Integer.parseInt(request.getParameter("time"));
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
		// Lấy ngày hôm nay
        Calendar calendar = Calendar.getInstance();
        // Xác định giờ, phút, giây, và mili giây thành 0 để chỉ lấy ngày tháng năm
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, dateid);
        
        Date date = new Date(calendar.getTimeInMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd/MM", new Locale("vi"));
        String datename = sdf.format(date);
        String time = "";
        String part = "";
        if (timeid <= 8) {
        	time = String.format("%02d:", 7 + (timeid - 1) / 2) + ((timeid % 2 == 0) ? "30" : "00");
        	part=" Sáng";
        }else {
        	time = String.format("%02d:", 13 + (timeid - 9) / 2) + ((timeid % 2 == 0) ? "00" : "30");
        	part=" Chiều";
        }
		Gson gson = new Gson();
		String jsonData = gson.toJson(new Data(doctor, dateid, timeid, datename, part, time));
		
		PrintWriter out = response.getWriter();
		out.print(jsonData);
		out.flush();
	}
	private class Data {
		DoctorInfo doctor;
		int dateId;
		int timeId;
		String datename;
		String part;
		String time;
		public DoctorInfo getDoctor() {
			return doctor;
		}
		public void setDoctor(DoctorInfo doctor) {
			this.doctor = doctor;
		}
		public int getDateId() {
			return dateId;
		}
		public void setDateId(int dateId) {
			this.dateId = dateId;
		}
		public int getTimeId() {
			return timeId;
		}
		public void setTimeId(int timeId) {
			this.timeId = timeId;
		}
		public String getDatename() {
			return datename;
		}
		public void setDatename(String datename) {
			this.datename = datename;
		}
		public String getPart() {
			return part;
		}
		public void setPart(String part) {
			this.part = part;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public Data(DoctorInfo doctor, int dateId, int timeId, String datename, String part, String time) {
			super();
			this.doctor = doctor;
			this.dateId = dateId;
			this.timeId = timeId;
			this.datename = datename;
			this.part = part;
			this.time = time;
		}
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
}
