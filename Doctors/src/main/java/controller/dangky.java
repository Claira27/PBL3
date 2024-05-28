
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

import database.AdminDAO;
import database.NguoiDungDAO;
import model.NguoiDung;
import model.Validate;
import util.MaHoa;

/**
 * Servlet implementation class dangky
 */
@WebServlet("/dang-ky")
public class dangky extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dangky() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		String ten = request.getParameter("ten-dangky");
		String email = request.getParameter("email-dangky");
		String matkhau = request.getParameter("matkhau-dangky");
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
		if(nguoiDungDAO.kiemTraEmail(email)) {
			error += "Email người dùng đã tồn tại";
		}
		if(new AdminDAO().kiemTraEmail(email)) {
			error += "Email người dùng đã tồn tại";
		}
		if(Validate.isValidEmail(email) || Validate.isValidPhone(sdt)) {
			error += "\nDinh dang du lieu khong phu hop";
		 
			//matkhau = MaHoa.toSHA1(matkhau);
		}
		request.setAttribute("error", error);
		if(error.length() > 0) {
			url = "/dangky.jsp";
		}else {
			NguoiDung nguoiDung = new NguoiDung(0, ten, email, matkhau, 2, diachi, sdt, Date.valueOf(ngaysinh), gioitinh);
			nguoiDungDAO.insert(nguoiDung);
			HttpSession session = request.getSession();
            session.setAttribute("nguoiDung", nguoiDung);
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
