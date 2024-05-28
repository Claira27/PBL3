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

/**
 * Servlet implementation class doimatkhaunguoidung
 */
@WebServlet("/doimatkhaunguoidung")
public class doimatkhaunguoidung extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doimatkhaunguoidung() {
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
	    
	    int id = nguoiDung.getId();
		String ten = nguoiDung.getTen();
		String email = nguoiDung.getEmail();
		String diachi = nguoiDung.getDiaChi();
		String sdt = nguoiDung.getSdt();
		Date ngaysinh = nguoiDung.getNgaySinh();
		String gioitinh = nguoiDung.getGioiTinh();
		String matkhaucu = request.getParameter("matkhau-cu");
		String matkhaumoi = request.getParameter("matkhau-dangky");
		
		String url = "";
		String error = "";
		NguoiDungDAO nguoiDungDAO = new NguoiDungDAO();
		
		if(!matkhaucu.equals(nguoiDung.getPassword())) {
			error += "Mật khẩu cũ không chính xác\n";
		}else {
			if(matkhaumoi.equals(nguoiDung.getPassword())) {
				error += "Mật khẩu mới không được giống mật khẩu cũ\n";
			}
		}
		
		boolean check = false;
		//set attribute error sau khi set xong loi
		request.setAttribute("error", error);
		if(error.length() > 0) {
			url = "/nguoidung_doimatkhau.jsp";
		}else {
			NguoiDung nguoiDungmoi = new NguoiDung(id, ten, email, matkhaumoi, 2, diachi, sdt, ngaysinh, gioitinh);
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
