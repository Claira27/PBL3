package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import database.DatLichDAO;
import database.KhamTrucTuyenDAO;
import model.DatLich;
import model.KhamTrucTuyen;
import model.NguoiDung;

/**
 * Servlet implementation class trunglichhen
 */
@WebServlet("/trunglichhen")
public class trunglichhen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public trunglichhen() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;
		int id_nguoidung = 0;
		int id_date = 0;
		int id_time = 0;
		
		NguoiDung nguoidung = null;
		
		HttpSession session = request.getSession();
		if(session!=null) {
			nguoidung = (NguoiDung)session.getAttribute("nguoiDung");
		}
		if(nguoidung!=null) {
			id_nguoidung = nguoidung.getId();
		}
		
		String date_pa = request.getParameter("date");
		String time_pa = request.getParameter("time");
		
		if(date_pa!=null) {
			id_date = Integer.parseInt(date_pa);
		}
		if(time_pa!=null) {
			id_time = Integer.parseInt(time_pa);
		}
		
		// Lấy ngày hôm nay
        Calendar calendar = Calendar.getInstance();
        // Xác định giờ, phút, giây, và mili giây thành 0 để chỉ lấy ngày tháng năm
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, id_date);
        
        Date date = new Date(calendar.getTimeInMillis());
        
        KhamTrucTuyen ktt = new KhamTrucTuyenDAO().selectByNguoiDung(id_nguoidung, date, id_time);
        DatLich dl = new DatLichDAO().selectByNguoiDung(id_nguoidung, date, id_time);
        if(ktt!=null || dl!=null) {
        	result = 1;
        }
        
        response.setContentType("application/json;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		Gson gson = new Gson();
		String jsonData = gson.toJson(result);
		
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
