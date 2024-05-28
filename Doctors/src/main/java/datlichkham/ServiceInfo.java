package datlichkham;

import model.DoctorInfo;
import model.NguoiDung;

public class ServiceInfo {
	int id;
	NguoiDung benhnhan;
	DoctorInfo bacsy;
	TimeAndID date;
    TimeAndID time;
    int rating;
    double fee;
    
	public ServiceInfo() {
		// TODO Auto-generated constructor stub
	}

	public ServiceInfo(int id, NguoiDung benhnhan, DoctorInfo bacsy, TimeAndID date, TimeAndID time, int rating,
			double fee) {
		super();
		this.id = id;
		this.benhnhan = benhnhan;
		this.bacsy = bacsy;
		this.date = date;
		this.time = time;
		this.rating = rating;
		this.fee = fee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public NguoiDung getBenhnhan() {
		return benhnhan;
	}

	public void setBenhnhan(NguoiDung benhnhan) {
		this.benhnhan = benhnhan;
	}

	public DoctorInfo getBacsy() {
		return bacsy;
	}

	public void setBacsy(DoctorInfo bacsy) {
		this.bacsy = bacsy;
	}

	public TimeAndID getDate() {
		return date;
	}

	public void setDate(TimeAndID date) {
		this.date = date;
	}

	public TimeAndID getTime() {
		return time;
	}

	public void setTime(TimeAndID time) {
		this.time = time;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}
	
}
