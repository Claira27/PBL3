package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.BacSyDAO;
import database.KhamTrucTuyenDAO;
import model.BacSy;
import model.KhamTrucTuyen;

/**
 * Servlet implementation class danhgia
 */
@WebServlet("/danhgia")
public class danhgia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public danhgia() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
		int id = 0;
		int star = 0;

		try {
			id = Integer.parseInt(request.getParameter("id"));
			star = Integer.parseInt(request.getParameter("star"));
		}catch(Exception e) {
			
		}
		int result = 0;
		if(star != 0) {
			KhamTrucTuyen ktt = new KhamTrucTuyenDAO().selectById(id);
			//update luot danh gia cua bac sy
			int bacsy_id = ktt.getIdBacSy();
			BacSy bacsy = new BacSyDAO().selectById(bacsy_id);
			int luotdanhgia = bacsy.getLuotDanhGia()+1;
			double danhgia = (bacsy.getDanhGia()*bacsy.getLuotDanhGia() + star)/luotdanhgia;
			bacsy.setDanhGia(danhgia);
			bacsy.setLuotDanhGia(luotdanhgia);
			result += new BacSyDAO().update(bacsy);
			
			//update danh gia cua kham 
			ktt.setDanhgia(star);
			result += new KhamTrucTuyenDAO().update(ktt);
		}

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
