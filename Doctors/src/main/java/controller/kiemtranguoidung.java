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

import model.NguoiDung;

/**
 * Servlet implementation class kiemtranguoidung
 */
@WebServlet("/kiemtranguoidung")
public class kiemtranguoidung extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public kiemtranguoidung() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");  // Corrected content type
		request.setCharacterEncoding("utf-8");
		int result = 0;
		HttpSession session = request.getSession(false);
	    if (session == null) {
	        // Session ko tồn tại, trả về kết quả 0
	    	result = 0;
	    } else {
	    	NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
	        if (nguoiDung == null) {
	        	result = 0;
	        }
	        else {
	        	result = 1;
	        }
	    }
	    Gson gson = new Gson();
	    String jsonData = gson.toJson(result);
	    
	    PrintWriter out = response.getWriter();
	    out.print(jsonData);
	    out.flush();
	}

}
