package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import database.AdminDAO;
import database.BacSyDAO;
import database.NguoiDungDAO;
import model.Admin;
import model.BacSy;
import model.NguoiDung;

@WebServlet("/dangnhap")
public class dangnhap extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Gson gson = new Gson();

    public dangnhap() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8"); 
        request.setCharacterEncoding("utf-8");

        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }

        JsonObject jsonData;
        try {
            jsonData = gson.fromJson(sb.toString(), JsonObject.class);
        } catch (JsonParseException e) {
            sendErrorResponse(response, "Invalid JSON format.");
            return;
        }

        String email = jsonData.get("email-dangnhap").getAsString();
        String password = jsonData.get("matkhau-dangnhap").getAsString();
        String isDoctorValue = jsonData.has("isdoctor") ? jsonData.get("isdoctor").getAsString() : "";

        JsonObject jsonResponse = new JsonObject();
        if (isDoctorValue.isEmpty()) {
            handleAdminOrUserLogin(email, password, request, jsonResponse);
        } else {
            handleDoctorLogin(email, password, request, jsonResponse);
        }

        try (PrintWriter out = response.getWriter()) {
            out.print(gson.toJson(jsonResponse));
            out.flush();
        }
    }

    private void handleAdminOrUserLogin(String email, String password, HttpServletRequest request, JsonObject jsonResponse) {
        Admin admin = new AdminDAO().selectByEmailAndPassword(email, password);
        if (admin != null) {
            HttpSession session = request.getSession();
            session.setAttribute("admin", admin);
            jsonResponse.addProperty("account", 0);
            jsonResponse.addProperty("success", true);
        } else {
            NguoiDung nguoiDung = new NguoiDungDAO().selectByEmailAndPassword(email, password);
            if (nguoiDung != null) {
                HttpSession session = request.getSession();
                session.setAttribute("nguoiDung", nguoiDung);
                jsonResponse.addProperty("account", 2);
                jsonResponse.addProperty("success", true);
            } else {
                jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("error", "Tên đăng nhập hoặc mật khẩu không đúng.");
            }
        }
    }

    private void handleDoctorLogin(String email, String password, HttpServletRequest request, JsonObject jsonResponse) {
        BacSy bacSy = new BacSyDAO().selectByEmailAndPassword(email, password);
        if (bacSy != null) {
            HttpSession session = request.getSession();
            session.setAttribute("bacsy", bacSy);
            jsonResponse.addProperty("account", 1);
            jsonResponse.addProperty("success", true);
        } else {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("error", "Tên đăng nhập hoặc mật khẩu không đúng.");
        }
    }

    private void sendErrorResponse(HttpServletResponse response, String errorMessage) throws IOException {
        JsonObject errorResponse = new JsonObject();
        errorResponse.addProperty("success", false);
        errorResponse.addProperty("error", errorMessage);

        try (PrintWriter out = response.getWriter()) {
            out.print(gson.toJson(errorResponse));
            out.flush();
        }
    }
}
