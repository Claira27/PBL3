package model;

import java.util.ArrayList;
import java.util.Objects;

import database.BacSyDAO;
import database.BenhVienDAO;

public class DoctorInfo {
	private int id;
	private String name;
	private String imageUrl;
	private String title;
	private String unit;
	private int experience;
	private int dichvu;
	private double giadichvu;
	private ArrayList<String> specialties = new ArrayList<String>();
	private Rating rating = new Rating();
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public int getDichvu() {
		return dichvu;
	}
	public void setDichvu(int dichvu) {
		this.dichvu = dichvu;
	}
	public ArrayList<String> getSpecialties() {
		return specialties;
	}
	public void setSpecialties(ArrayList<String> specialties) {
		this.specialties = specialties;
	}
	public Rating getRating() {
		return rating;
	}
	public void setRating(Rating rating) {
		this.rating = rating;
	}
	public double getGiadichvu() {
		return giadichvu;
	}
	public void setGiadichvu(double giadichvu) {
		this.giadichvu = giadichvu;
	}
	@Override
	public String toString() {
		return "DoctorInfo [name=" + name + ", imageUrl=" + imageUrl + ", title=" + title + ", unit=" + unit
				+ ", experience=" + experience + ", dichvu=" + dichvu + ", specialties=" + specialties + ", rating="
				+ rating + "]";
	}
	public DoctorInfo(int id, String name, String imageUrl, String title, String unit, int experience, int dichvu,
			double giadichvu,ArrayList<String> specialties, Rating rating) {
		super();
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.title = title;
		this.unit = unit;
		this.experience = experience;
		this.dichvu = dichvu;
		this.giadichvu = giadichvu;
		this.specialties = specialties;
		this.rating = rating;
	}
	
	public DoctorInfo() {
		// TODO Auto-generated constructor stub
	}
	public DoctorInfo(BacSy bacsy) {
		BenhVienDAO bvDAO = new BenhVienDAO();
		this.id = bacsy.getId();
		this.name = bacsy.getTen();
		this.imageUrl = bacsy.getHinhanh();
		this.title = bacsy.getBangCap();
		this.unit = bvDAO.selectById(bacsy.getId_benhVien()).getTen();
		this.dichvu = bacsy.getDichvu();
		this.giadichvu = bacsy.getGiadichvu();
		this.rating.setStars((int)bacsy.getDanhGia());
		this.rating.setTotalRatings(bacsy.getLuotDanhGia());
		this.experience = bacsy.getNamKinhNghiem();
	}
}

