package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import database.DoctorInfoDAO;
import model.DoctorInfo;
import model.NguoiDung;

/**
 * Servlet implementation class testAPI
 */
@WebServlet("/testAPI")
public class testAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        //kiem tra dang nhap trong session
        int onSesson = 0;
		int id_benhnhan = 0;
		int success = 0;
		HttpSession session = request.getSession(false);
	    if (session == null) {
	        // Session ko tồn tại, trả về kết quả 0
	    	onSesson = 0;
	    } else {
	    	NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
	        if (nguoiDung == null) {
	        	onSesson = 0;
	        }
	        else {
	        	id_benhnhan = nguoiDung.getId();
	        	onSesson = 1;
	        }
	    }
	    
	    int doctorid = 0;
	    try {
	    	doctorid = Integer.parseInt(request.getParameter("doctorId"));
		} catch (NumberFormatException e) {
		}
		int typeid = 0;
		try {
			typeid = Integer.parseInt(request.getParameter("typeid"));
		} catch (NumberFormatException e) {
		}
		int dateid = 0;
		try {
		    dateid = Integer.parseInt(request.getParameter("date"));
		} catch (NumberFormatException e) {
		}
		int timeid = 0;
		try {
		    timeid = Integer.parseInt(request.getParameter("time"));
		} catch (NumberFormatException e) {
		}
		DoctorInfo doctor = null;
		if(doctorid!=0) {
			doctor = new DoctorInfoDAO().selectById(doctorid);
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
        //Data data = new Data(doctor, datename, part, time, typeid, success);
		String jsonData = gson.toJson(time);
		
		PrintWriter out = response.getWriter();
		out.print(jsonData);
		out.flush();
	}
	private class Data {
		DoctorInfo doctor;
		String datename;
		String part;
		String time;
		int service;
		int success;
		
		public int getSuccess() {
			return success;
		}
		public void setSuccess(int success) {
			this.success = success;
		}
		public int getService() {
			return service;
		}
		public void setService(int service) {
			this.service = service;
		}
		public DoctorInfo getDoctor() {
			return doctor;
		}
		public void setDoctor(DoctorInfo doctor) {
			this.doctor = doctor;
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
		public Data(DoctorInfo doctor, String datename, String part, String time, int service, int success) {
			super();
			this.doctor = doctor;
			this.datename = datename;
			this.part = part;
			this.time = time;
			this.service = service;
			this.success = success;
		}
	}
}
