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

import datlichkham.BookingInfo;
import datlichkham.DatLichHandler;
import datlichkham.TimeAndID;
import model.BacSy;
import model.NguoiDung;

/**
 * Servlet implementation class upcoming
 */
@WebServlet("/upcoming")
public class upcoming extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public upcoming() {
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
    	ArrayList<BookingInfo> bookingInfo = new ArrayList<BookingInfo>();
    	
    	if(doctorid != 0) {
    		bookingInfo = new DatLichHandler().Upcoming_BS(doctorid);
    	}else if(patientid != 0) {
    		bookingInfo = new DatLichHandler().Upcoming_BN(patientid);
    	}
    	
    	//14 next days
    	ArrayList<TimeAndID> dateCards = new DatLichHandler().getDates();
    	Gson gson = new Gson();
        String jsonData = gson.toJson(new Data(dateCards, bookingInfo));        
        
    	// Thiết lập header cho phản hồi là JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // Gửi dữ liệu JSON về cho trình duyệt
        PrintWriter out = response.getWriter();
        out.print(jsonData);
        out.flush();
	}
	
	private class Data {
		ArrayList<TimeAndID> dateCards = new ArrayList<TimeAndID>();
		ArrayList<BookingInfo> bookingInfo = new ArrayList<BookingInfo>();
		public Data(ArrayList<TimeAndID> dateCards, ArrayList<BookingInfo> bookingInfo) {
			super();
			this.dateCards = dateCards;
			this.bookingInfo = bookingInfo;
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
