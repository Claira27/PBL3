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

import database.BacSyDAO;
import database.DatLichDAO;
import database.KhamTrucTuyenDAO;
import model.BacSy;

/**
 * Servlet implementation class taibacsythongke
 */
@WebServlet("/taibacsythongke")
public class taibacsythongke extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public taibacsythongke() {
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
	    
	    int[] datlich = new DatLichDAO().ThongKeTheoThang();
        int[] khamtructuyen = new KhamTrucTuyenDAO().ThongKeTheoThang();
        BacSy bacsy = new BacSyDAO().selectById(id_bacsy);
        double danhgia = bacsy.getDanhGia();
        int tong = bacsy.getLuotDanhGia();
		// Chuyển đổi danh sách thành JSON
		Gson gson = new Gson();
		String jsonData = gson.toJson(new Data(datlich, khamtructuyen,danhgia, tong));
		
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
	private class Data {
        int[] datlich;
        int[] khamtructuyen;
        double danhgia;
        int tong;
        
        public Data(int[] datlich, int[] khamtructuyen, double danhgia,int tong) {
            this.datlich = datlich;
            this.khamtructuyen = khamtructuyen;
            this.danhgia = danhgia;
            this.tong = tong;
        }
	}
}
