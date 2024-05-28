package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import database.DangKyLichDAO;
import model.BacSy;
import model.DangKyLich;

/**
 * Servlet implementation class dangkylich
 */
@WebServlet("/dangkylich")
public class dangkylich extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dangkylich() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int doctorid = 0;
	    int result = 0;
	    HttpSession session = request.getSession(false);
	    if(session != null) {
	        BacSy bacsy = (BacSy) session.getAttribute("bacsy");
	        if(bacsy != null) {
	            doctorid = bacsy.getId();
	        }
	    }
	    
	    // Read the JSON data from the request body
	    BufferedReader reader = request.getReader();
	    JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
	    Gson gson = new Gson();
	    int[][] tem = gson.fromJson(jsonObject.get("tem"), int[][].class);
	    ArrayList<DangKyLich> list = new ArrayList<DangKyLich>();
	    for (int i = 0; i < tem.length; i++) {
	        for (int j : tem[i]) {
	            DangKyLich dkl = new DangKyLich(0, doctorid, i+2, j);
	            list.add(dkl);
	        }
	    }
	    // Call the method to save the schedule data
	    DangKyLichDAO dklDAO = new DangKyLichDAO();
	    dklDAO.deleteByDoctorId(doctorid);
	    result = dklDAO.insertAll(list);
	    
	    // Send the result back to the client as JSON response
	    String jsonData = gson.toJson(result);
	    
	    // Set response content type and send response
	    response.setContentType("application/json");
	    PrintWriter out = response.getWriter();
	    out.print(jsonData);
	    out.flush();
	}
}
