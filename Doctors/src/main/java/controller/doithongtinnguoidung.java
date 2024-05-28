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

import database.NguoiDungDAO;
import model.NguoiDung;
import util.MaHoa;

/**
 * Servlet implementation class doithongtinnguoidung
 */
@WebServlet("/doithongtinnguoidung")
public class doithongtinnguoidung extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doithongtinnguoidung() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		NguoiDung nguoiDung = null;
		HttpSession session = request.getSession(false);
	    if (session != null) {
	    	nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
	    }
	    
		String ten = request.getParameter("ten-dangky");
		String email = request.getParameter("email-dangky");
		String diachi = request.getParameter("diachi-dangky");
		String sdt = request.getParameter("sdt-dangky");
		String ngaysinh = request.getParameter("ngaysinh-dangky");
		String gioitinh = request.getParameter("gioitinh");
		request.setAttribute("ten", ten);
		request.setAttribute("email", email);
		request.setAttribute("diachi", diachi);
		request.setAttribute("sdt", sdt);
		request.setAttribute("ngaysinh", ngaysinh);
		request.setAttribute("gioitinh", gioitinh);
		
		String url = "";
		String error = "";
		NguoiDungDAO nguoiDungDAO = new NguoiDungDAO();
		if(!nguoiDung.getEmail().equals(email.trim())) {
			if(nguoiDungDAO.kiemTraEmail(email.trim())) {
				error += "Email người dùng đã tồn tại";
			}
		}
		boolean check = false;
		request.setAttribute("error", error);
		if(error.length() > 0) {
			url = "/nguoidung_doithongtin.jsp";
		}else {
			int id = nguoiDung.getId();
			String password = nguoiDung.getPassword();
			NguoiDung nguoiDungmoi = new NguoiDung(id, ten, email, password, 2, diachi, sdt, Date.valueOf(ngaysinh), gioitinh);
			nguoiDungDAO.update(nguoiDungmoi);
            session.setAttribute("nguoiDung", nguoiDungmoi);
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
