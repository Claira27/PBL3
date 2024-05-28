package controller;

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

import database.BacSyDAO;
import database.DatLichDAO;
import database.KhamTrucTuyenDAO;
import database.NguoiDungDAO;
import database.TimKiemDAO;
import model.BacSy;
import model.BacSyTichCuc;
import model.NguoiDungTichCuc;

/**
 * Servlet implementation class taiadminthongke
 */
@WebServlet("/taiadminthongke")
public class taiadminthongke extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public taiadminthongke() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
	    
	    //lay ra thong tin thong ke
	    
	    int soluongNd = new NguoiDungDAO().TongNguoiDung();
        int soluongBs = new BacSyDAO().TongBacSy();
        int timkiem = new TimKiemDAO().TongHomNay();
        int datlich = new DatLichDAO().TongTrongThang();
        int tuvan = new KhamTrucTuyenDAO().TongTrongThang();
        ArrayList<NguoiDungTichCuc> nguoidung = new NguoiDungDAO().NguoiDungNhieuNhat();
        ArrayList<BacSyTichCuc> bacsy = new BacSyDAO().BacSyNhieuNhat();
        
		// Chuyển đổi danh sách thành JSON
		Gson gson = new Gson();
		String jsonData = gson.toJson(new Data(soluongNd, soluongBs, timkiem, datlich, tuvan, nguoidung, bacsy));
		
		// Gửi dữ liệu JSON về cho trình duyệt
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
	private class Data {
        int soluongNd;
        int soluongBs;
        int timkiem;
        int datlich;
        int tuvan;
        ArrayList<NguoiDungTichCuc> nguoidung = new ArrayList<NguoiDungTichCuc>();
        ArrayList<BacSyTichCuc> bacsy = new ArrayList<BacSyTichCuc>();
        
		public Data(int soluongNd, int soluongBs, int timkiem, int datlich, int tuvan,
				ArrayList<NguoiDungTichCuc> nguoidung, ArrayList<BacSyTichCuc> bacsy) {
			super();
			this.soluongNd = soluongNd;
			this.soluongBs = soluongBs;
			this.timkiem = timkiem;
			this.datlich = datlich;
			this.tuvan = tuvan;
			this.nguoidung = nguoidung;
			this.bacsy = bacsy;
		}
	}
}
