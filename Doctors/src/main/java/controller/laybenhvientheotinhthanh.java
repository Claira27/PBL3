package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.BenhVienDAO;
import model.BenhVien;

/**
 * Servlet implementation class laybenhvientheotinhthanh
 */
@WebServlet("/laybenhvientheotinhthanh")
public class laybenhvientheotinhthanh extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public laybenhvientheotinhthanh() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<BenhVien> hospitals = new ArrayList<BenhVien>();
		String area = request.getParameter("area");
		
		if(area!=null) {
			int id_tinhthanh = Integer.parseInt(area);
			BenhVienDAO bvDAO = new BenhVienDAO();
			hospitals.addAll(bvDAO.selectBenhVienByIdtinhthanh(id_tinhthanh));
		}
		
		Gson gson = new Gson();
		String jsonData =gson.toJson(hospitals);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
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
