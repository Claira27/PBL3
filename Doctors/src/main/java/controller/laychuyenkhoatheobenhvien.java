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

import database.ChuyenKhoaDAO;
import model.ChuyenKhoa;

/**
 * Servlet implementation class laychuyenkhoatheobenhvien
 */
@WebServlet("/laychuyenkhoatheobenhvien")
public class laychuyenkhoatheobenhvien extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public laychuyenkhoatheobenhvien() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<ChuyenKhoa> specialties = new ArrayList<ChuyenKhoa>();
		String hospital = request.getParameter("hospital");

		if(hospital!=null) {
			int id_hospital = Integer.parseInt(hospital);
			ChuyenKhoaDAO ckDAO = new ChuyenKhoaDAO();
			specialties.addAll(ckDAO.selectByIdBenhvien(id_hospital));
		}
		
		Gson gson = new Gson();
		String jsonData = gson.toJson(specialties);
		
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
