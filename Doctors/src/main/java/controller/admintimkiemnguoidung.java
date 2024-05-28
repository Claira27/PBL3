package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.BacSyDAO;
import database.NguoiDungDAO;
import model.BacSyTichCuc;
import model.NguoiDungTichCuc;

/**
 * Servlet implementation class admintimkiemnguoidung
 */
@WebServlet("/admintimkiemnguoidung")
public class admintimkiemnguoidung extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admintimkiemnguoidung() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String chuoiten = request.getParameter("ten");
	    
	    //lay ra thong tin tim kiem
        ArrayList<NguoiDungTichCuc> nguoidung = new NguoiDungDAO().selectByTen(chuoiten);
        
		// Chuyển đổi danh sách thành JSON
		Gson gson = new Gson();
		String jsonData = gson.toJson(nguoidung);
		
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
