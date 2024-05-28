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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import database.BenhVienDAO;
import database.ChuyenKhoaDAO;
import database.DoctorInfoDAO;
import database.TinhThanhDAO;
import model.BenhVien;
import model.ChuyenKhoa;
import model.DoctorInfo;
import model.TinhThanh;

/**
 * Servlet implementation class taidulieu
 */
@WebServlet("/taidulieu")
public class taidulieu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public taidulieu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<TinhThanh> provinces = new ArrayList<TinhThanh>();
		List<BenhVien> hospitals = new ArrayList<BenhVien>();
		List<ChuyenKhoa> specialties = new ArrayList<ChuyenKhoa>();
		
		TinhThanhDAO ttDAO = new TinhThanhDAO();
		provinces.addAll(ttDAO.selectAll());
		
		BenhVienDAO bvDAO = new BenhVienDAO();
		hospitals.addAll(bvDAO.selectAll());
		
		ChuyenKhoaDAO ckDAO = new ChuyenKhoaDAO();
		specialties.addAll(ckDAO.selectAll());
		
		
		// Chuyển đổi danh sách thành JSON
		Gson gson = new Gson();
		Data data = new Data(provinces,hospitals,specialties);
		String jsonData = gson.toJson(data);

		// Thiết lập header cho phản hồi là JSON
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		// Gửi dữ liệu JSON về cho trình duyệt
        PrintWriter out = response.getWriter();
        out.print(jsonData);
        out.flush();
	}
	private class Data {
        List<TinhThanh> provinces;
        List<BenhVien> hospitals;
        List<ChuyenKhoa> specialties;
        
        public Data(List<TinhThanh> provinces, List<BenhVien> hospitals, List<ChuyenKhoa> specialties) {
            this.provinces = provinces;
            this.hospitals = hospitals;
            this.specialties = specialties;
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
