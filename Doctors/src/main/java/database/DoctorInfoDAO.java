package database;

import java.util.ArrayList;

import model.BacSy;
import model.DoctorInfo;

public class DoctorInfoDAO {

	public ArrayList<DoctorInfo> selectAll() {
		ArrayList<DoctorInfo> result = new ArrayList<DoctorInfo>();
		PhanChuyenMonDAO pcmDAO = new PhanChuyenMonDAO();
		ChuyenKhoaDAO ckDAO =  new ChuyenKhoaDAO();
		ArrayList<BacSy> bacsyList = new BacSyDAO().selectAll();
		for (BacSy bacsy : bacsyList) {
			DoctorInfo doctorInfo = new DoctorInfo(bacsy);
			ArrayList<Integer> id_chuyenkhoa = pcmDAO.selectByIdBacsy(bacsy.getId());
			for(int id_ck : id_chuyenkhoa){
				doctorInfo.getSpecialties().add(ckDAO.selectById(id_ck).getTen());
			}
			result.add(doctorInfo);
		}
		return result;
	} 
	public ArrayList<DoctorInfo> selectDoctors(ArrayList<BacSy> bacsyList) {
		ArrayList<DoctorInfo> result = new ArrayList<DoctorInfo>();
		PhanChuyenMonDAO pcmDAO = new PhanChuyenMonDAO();
		ChuyenKhoaDAO ckDAO =  new ChuyenKhoaDAO();
		
		for (BacSy bacsy : bacsyList) {
			DoctorInfo doctorInfo = new DoctorInfo(bacsy);
			ArrayList<Integer> id_chuyenkhoa = pcmDAO.selectByIdBacsy(bacsy.getId());
			for(int id_ck : id_chuyenkhoa){
				doctorInfo.getSpecialties().add(ckDAO.selectById(id_ck).getTen());
			}
			result.add(doctorInfo);
		}
		return result;
	}

	public DoctorInfo selectById(int id) {
		DoctorInfo result = null;
		BacSy bacsy = new BacSyDAO().selectById(id);
		if(bacsy != null) {
			PhanChuyenMonDAO pcmDAO = new PhanChuyenMonDAO();
			ChuyenKhoaDAO ckDAO =  new ChuyenKhoaDAO();
			result = new DoctorInfo(bacsy);
			ArrayList<Integer> id_chuyenkhoa = pcmDAO.selectByIdBacsy(id);
			for(int id_ck : id_chuyenkhoa){
				result.getSpecialties().add(ckDAO.selectById(id_ck).getTen());
			}
		}
		return result;
	}
}
