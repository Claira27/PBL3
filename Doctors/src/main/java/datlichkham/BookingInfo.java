package datlichkham;

import model.DoctorInfo;
import model.NguoiDung;

public class BookingInfo {
	int id;
	NguoiDung benhnhan;
	DoctorInfo bacsy;
	TimeAndID date;
	TimeAndID time;
	
    public BookingInfo() {
		// TODO Auto-generated constructor stub
	}

	public BookingInfo(int id, NguoiDung benhnhan, DoctorInfo bacsy, TimeAndID date, TimeAndID time) {
		super();
		this.id = id;
		this.benhnhan = benhnhan;
		this.bacsy = bacsy;
		this.date = date;
		this.time = time;
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
}