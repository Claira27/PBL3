package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.BacSyDAO;
import database.NguoiDungDAO;
import model.BacSy;
import model.NguoiDung;

/**
 * Servlet implementation class doimatkhaubacsy
 */
@WebServlet("/doimatkhaubacsy")
public class doimatkhaubacsy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doimatkhaubacsy() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		BacSy bacsy = null;
		HttpSession session = request.getSession(false);
	    if (session != null) {
	    	bacsy = (BacSy) session.getAttribute("bacsy");
	    }
	    
	    int id = bacsy.getId();
		String ten = bacsy.getTen();
		String email = bacsy.getEmail();
		int benhvien = bacsy.getId_benhVien();
		String bangcap = bacsy.getBangCap();
		int luotdanhgia = bacsy.getLuotDanhGia();
		double danhgia = bacsy.getDanhGia();
		String hinhanh = bacsy.getHinhanh();
		int dichvu = bacsy.getDichvu();
		double giadichvu = bacsy.getGiadichvu();
		String matkhaucu = request.getParameter("matkhau-cu");
		String matkhaumoi = request.getParameter("matkhau-dangky");
		
		String url = "";
		String error = "";
		BacSyDAO bacsyDAO = new BacSyDAO();
		
		if(!matkhaucu.equals(bacsy.getPassword())) {
			error += "Mật khẩu cũ không chính xác\n";
		}else {
			if(matkhaumoi.equals(bacsy.getPassword())) {
				error += "Mật khẩu mới không được giống mật khẩu cũ\n";
			}
		}
		
		boolean check = false;
		//set attribute error sau khi set xong loi
		request.setAttribute("error", error);
		if(error.length() > 0) {
			url = "/bacsy_doimatkhau.jsp";
		}else {
			BacSy bacsymoi = new BacSy(id, ten, email, matkhaumoi, 1, benhvien, bangcap, benhvien, luotdanhgia, danhgia, hinhanh, dichvu, giadichvu);
			bacsyDAO.update(bacsymoi);
            session.setAttribute("bacsy", bacsymoi);
            check=true;
		}
		if(check) {
			url = "/index.jsp";
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
