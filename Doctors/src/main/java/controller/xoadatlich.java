package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.DatLichDAO;
import database.KhamTrucTuyenDAO;

/**
 * Servlet implementation class xoadatlich
 */
@WebServlet("/xoadatlich")
public class xoadatlich extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public xoadatlich() {
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
		int service = 0;

		try {
			id = Integer.parseInt(request.getParameter("id"));
			service = Integer.parseInt(request.getParameter("service"));
		}catch(Exception e) {
			
		}
		int result = 0;
		if(service == 0) {
			result = new DatLichDAO().deleteByID(id);
		}
		else {
			result = new KhamTrucTuyenDAO().deleteByID(id);
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
