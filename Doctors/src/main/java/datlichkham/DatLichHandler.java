package datlichkham;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import database.DangKyLichDAO;
import database.DatLichDAO;
import database.DoctorInfoDAO;
import database.KhamTrucTuyenDAO;
import database.NguoiDungDAO;
import database.TimePeriodDAO;
import model.DangKyLich;
import model.DatLich;
import model.DoctorInfo;
import model.KhamTrucTuyen;
import model.NguoiDung;

public class DatLichHandler {
	/*
	 *MIEU TA CHUC NANG (PHIA NGUOI DUNG)
	 1. Chon Dat Lich Kham
	 - Input: ID bac sy 
	 - Output: 7 ngay tiep theo(Khong tinh "Chu Nhat") voi slot tuong ung moi ngay:
	 	+ Ten ngay
	 	+ Danh sach slot buoi sang(1-8)
	 	+ Danh sach slot buoi chieu(9-15)
	 	-> kieu du lieu: TimeInfo
	 -Algorithm: vi la dat lich truoc de den kham tai benh vien nen se co du 15 moc thoi gian(tao mac dinh tu ham tao)
	 			 loai tru thoi gian da duoc dat truoc va thoi gian bac sy dang ky kham dich vu(Tu van truc tuyen)
	*/
	
	/*
	 2. Chon Dat Lich Tu Van Online
	 - Input: ID bac sy 
	 - Output: 7 ngay tiep theo(Khong tinh "Chu Nhat") voi slot tuong ung moi ngay:
	 	+ Ten ngay
	 	+ Danh sach slot buoi sang(1-8)
	 	+ Danh sach slot buoi chieu(9-22)
	 	-> Kieu du lieu: TimeInfo
	 -Algorithm: Chon ra moc thoi gian trong csdl bang "dangkylich", lich duoc bac sy dang ky se luu vao day
	 			 loai tru thoi gian da duoc dat truoc 
	*/
	
	/*
	 3. Luu Dat Lich Kham
	 - Input: int isService, doctorId
	 - Kiem tra trung lich trong csdl, phia nguoi dung da dat lich nay chua, thong bao hay chon lich khac
	 - Output: Luu vao bang off/on tuong ung
	*/
	
	/*
	 * MIEU TA CHUC NANG (PHIA BAC SY)
	 1.  Chon dang ky lich de tu van truc tuyen( dich vu)
	 - Bac sy co the dang ky khung gio co dinh cho thu 2 den thu 7
	 - Thay doi se duoc luu tu dong sau 1 tuan
	*/
	
	//get next 7 days( except Sunday)
	public ArrayList<TimeAndID> getUpcomingDates(){
		ArrayList<TimeAndID> result = new ArrayList<TimeAndID>();
		
		Calendar calendar = Calendar.getInstance();
        // Xác định giờ, phút, giây, và mili giây thành 0 để chỉ lấy ngày tháng năm
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        ArrayList<Date> dates = new ArrayList<>();
        
        // Thêm 7 ngày tiếp theo vào danh sách
        for (int i = 0; i < 7; i++) {
            // Thêm một ngày vào ngày hiện tại
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            // Lấy ngày sau khi đã thêm
            Date nextDate = new Date(calendar.getTimeInMillis());
            // Thêm ngày vào danh sách
            dates.add(nextDate);
        }
        
        for (Date date : dates) {
            // Xác định tên ngày
            String datename = "";
            if (dates.indexOf(date) == 0) {
            	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM", new Locale("vi"));
                datename = "Ngày mai, " + sdf.format(date);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd/MM", new Locale("vi"));
                datename = sdf.format(date);
            }
            Calendar calendar1 = Calendar.getInstance();
            // Xác định giờ, phút, giây, và mili giây thành 0 để chỉ lấy ngày tháng năm
            calendar1.set(Calendar.HOUR_OF_DAY, 0);
            calendar1.set(Calendar.MINUTE, 0);
            calendar1.set(Calendar.SECOND, 0);
            calendar1.set(Calendar.MILLISECOND, 0);
            Date today = new Date(calendar1.getTimeInMillis());
            
            TimeAndID t = new TimeAndID(dayDifference(date, today), datename);
            result.add(t);
        }
		return result;
	}
	public ArrayList<TimeAndID> getDates(){
		ArrayList<TimeAndID> result = new ArrayList<TimeAndID>();
		
		Calendar calendar = Calendar.getInstance();
        // Xác định giờ, phút, giây, và mili giây thành 0 để chỉ lấy ngày tháng năm
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        ArrayList<Date> dates = new ArrayList<>();
        dates.add(new Date(calendar.getTimeInMillis()));
        
        // Thêm 7 ngày tiếp theo vào danh sách
        for (int i = 0; i < 7; i++) {
            // Thêm một ngày vào ngày hiện tại
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            // Lấy ngày sau khi đã thêm
            Date nextDate = new Date(calendar.getTimeInMillis());
            // Thêm ngày vào danh sách
            dates.add(nextDate);
        }
        
        for (Date date : dates) {
            // Xác định tên ngày
            String datename = "";
            if (dates.indexOf(date) == 0) {
            	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM", new Locale("vi"));
                datename = "Hôm nay, " + sdf.format(date);
            } else if (dates.indexOf(date) == 1) {
            	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM", new Locale("vi"));
                datename = "Ngày mai, " + sdf.format(date);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd/MM", new Locale("vi"));
                datename = sdf.format(date);
            }
            Calendar calendar1 = Calendar.getInstance();
            // Xác định giờ, phút, giây, và mili giây thành 0 để chỉ lấy ngày tháng năm
            calendar1.set(Calendar.HOUR_OF_DAY, 0);
            calendar1.set(Calendar.MINUTE, 0);
            calendar1.set(Calendar.SECOND, 0);
            calendar1.set(Calendar.MILLISECOND, 0);
            Date today = new Date(calendar1.getTimeInMillis());
            
            TimeAndID t = new TimeAndID(dayDifference(date, today), datename);
            result.add(t);
        }
		return result;
	}
	
	private int dayDifference(Date a, Date b) {
	    long millisecondsPerDay = 24 * 60 * 60 * 1000L; // Số mili giây trong một ngày
	    long differenceInMillis = a.getTime() - b.getTime();
	    return (int) (differenceInMillis / millisecondsPerDay);
	}

	private boolean sameDate(Date a, Date b) {
		// Lấy ra giá trị của ngày, tháng, năm từ các đối tượng Date
	    int dayA = a.toLocalDate().getDayOfMonth();
	    int monthA = a.toLocalDate().getMonthValue();
	    int yearA = a.toLocalDate().getYear();
	    
	    int dayB = b.toLocalDate().getDayOfMonth();
	    int monthB = b.toLocalDate().getMonthValue();
	    int yearB = b.toLocalDate().getYear();
	    
	    // So sánh các thành phần ngày, tháng, năm
	    return (dayA == dayB) && (monthA == monthB) && (yearA == yearB);
	}
	
	
	//Xu ly du lieu dat lich Kham sau hien tai
	public ArrayList<BookingInfo> SetNextBooking(ArrayList<DatLich> kham){
		ArrayList<BookingInfo> result = new ArrayList<>();
        for (DatLich ktt : kham) {
        	int id = ktt.getId();
            // Xác định tên ngày
        	SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd/MM", new Locale("vi"));
            String datename = sdf.format(ktt.getNgay());
            String time = "";
            int id_time = ktt.getIdTimePeriod();
            if (id_time <= 8) {
            	time = String.format("%02d:", 7 + (id_time - 1) / 2) + ((id_time % 2 == 0) ? "30" : "00");
            } else {
            	time = String.format("%02d:", 13 + (id_time - 8) / 2) + ((id_time % 2 != 0) ? "30" : "00");
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime timeToCompare = LocalTime.parse(time, formatter);
            LocalTime currentTime = LocalTime.now();
            boolean check = true;
            //kiem tra neu cung ngay thi chi lay thoi gian sau thoi gian hien tai
            if(sameDate(ktt.getNgay(), Date.valueOf(LocalDate.now()))) {
            	if (currentTime.isAfter(timeToCompare)){
            		check = false;
            	}
            }
            if(check){
            	//sau khi kiem tra thoi gian hop le 
            	NguoiDung benhnhan = new NguoiDungDAO().selectById(ktt.getIdBenhNhan());
            	DoctorInfo bacsy = new DoctorInfoDAO().selectById(ktt.getIdBacSy());
            	int dateid = dayDifference(ktt.getNgay(), Date.valueOf(LocalDate.now()));
            	TimeAndID date = new TimeAndID(dateid, datename);
            	TimeAndID timeandid = new TimeAndID(id_time, time);
                BookingInfo timeinfo = new BookingInfo(id, benhnhan, bacsy, date, timeandid);
                result.add(timeinfo);
            }
        }
        return result;
	}
	
	//Xu ly du lieu dat lich Kham truc tuyen sau hien tai
	public ArrayList<ServiceInfo> SetNextService(ArrayList<KhamTrucTuyen> tuvan){
		ArrayList<ServiceInfo> result = new ArrayList<>();
		for (KhamTrucTuyen ktt : tuvan) {
        	int id = ktt.getId();
            // Xác định tên ngày
        	SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd/MM", new Locale("vi"));
            String datename = sdf.format(ktt.getNgay());
            int rating = ktt.getDanhgia();
            double fee = ktt.getPhi();
            String time = "";
            int id_time = ktt.getIdTimePeriod();
            if (id_time <= 8) {
            	time = String.format("%02d:", 7 + (id_time - 1) / 2) + ((id_time % 2 == 0) ? "30" : "00");
            } else {
            	time = String.format("%02d:", 13 + (id_time - 8) / 2) + ((id_time % 2 != 0) ? "30" : "00");
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime timeToCompare = LocalTime.parse(time, formatter);
            LocalTime currentTime = LocalTime.now();
            boolean check = true;
            if(sameDate(ktt.getNgay(), Date.valueOf(LocalDate.now()))) {
            	if (currentTime.isAfter(timeToCompare)){
            		check = false;
            	}
            }
            if(check){
            	NguoiDung benhnhan = new NguoiDungDAO().selectById(ktt.getIdBenhNhan());
            	DoctorInfo bacsy = new DoctorInfoDAO().selectById(ktt.getIdBacSy());
            	int dateid = dayDifference(ktt.getNgay(), Date.valueOf(LocalDate.now()));
            	TimeAndID date = new TimeAndID(dateid, datename);
            	TimeAndID timeandid = new TimeAndID(id_time, time);
                ServiceInfo serviceinfo = new ServiceInfo(ktt.getId(), benhnhan, bacsy, date, timeandid, rating, fee);
                result.add(serviceinfo);
            }
        }
		return result;
	}
	
	//Xu ly du lieu dat lic Kham truc tuyen truoc hien tai
	public ArrayList<ServiceInfo> SetPrevService(ArrayList<KhamTrucTuyen> tuvan){
		ArrayList<ServiceInfo> result = new ArrayList<>();
		for (KhamTrucTuyen ktt : tuvan) {
        	int id = ktt.getId();
            // Xác định tên ngày
        	SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd/MM", new Locale("vi"));
            String datename = sdf.format(ktt.getNgay());
            int rating = ktt.getDanhgia();
            double fee = ktt.getPhi();
            String time = "";
            int id_time = ktt.getIdTimePeriod();
            if (id_time <= 8) {
            	time = String.format("%02d:", 7 + (id_time - 1) / 2) + ((id_time % 2 == 0) ? "30" : "00");
            } else {
            	time = String.format("%02d:", 13 + (id_time - 8) / 2) + ((id_time % 2 != 0) ? "30" : "00");
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime timeToCompare = LocalTime.parse(time, formatter);
            LocalTime currentTime = LocalTime.now();
            boolean check = true;
            if(sameDate(ktt.getNgay(), Date.valueOf(LocalDate.now()))) {
            	if (currentTime.isBefore(timeToCompare)){
            		check = false;
            	}
            }
            if(check){
            	NguoiDung benhnhan = new NguoiDungDAO().selectById(ktt.getIdBenhNhan());
            	DoctorInfo bacsy = new DoctorInfoDAO().selectById(ktt.getIdBacSy());
            	int dateid = dayDifference(ktt.getNgay(), Date.valueOf(LocalDate.now()));
            	TimeAndID date = new TimeAndID(dateid, datename);
            	TimeAndID timeandid = new TimeAndID(id_time, time);
                ServiceInfo serviceinfo = new ServiceInfo(ktt.getId(), benhnhan, bacsy, date, timeandid, rating, fee);
                result.add(serviceinfo);
            }
        }
		return result;
	}
	
	//Booking sap toi cua bac sy
	public ArrayList<BookingInfo> Upcoming_BS(int id_bacsy) {
		ArrayList<DatLich> kham = new DatLichDAO().selectNextByDoctorID(id_bacsy);
		ArrayList<BookingInfo> result = SetNextBooking(kham);
		return result;
    }
	
	//Booking sap toi cua benh nhan
	public ArrayList<BookingInfo> Upcoming_BN(int id_nguoidung) {
		ArrayList<DatLich> kham = new DatLichDAO().selectNextByPatientID(id_nguoidung);
		ArrayList<BookingInfo> result = SetNextBooking(kham);
		return result;
    }
	
	//select free time to book for offline service
	public ArrayList<TimeInfo> selectFreeBookingSlotByDoctorID(int id_bacsy) {
        ArrayList<TimeInfo> result = new ArrayList<>();
        //Lich da dat boi bac sy
        ArrayList<DatLich> lich1 = new DatLichDAO().selectNextByDoctorID(id_bacsy);
        
        Calendar calendar = Calendar.getInstance();
        // Xác định giờ, phút, giây, và mili giây thành 0 để chỉ lấy ngày tháng năm
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        ArrayList<Date> dates = new ArrayList<>();
        
        // Thêm 7 ngày tiếp theo vào danh sách
        for (int i = 0; i < 7; i++) {
            // Thêm một ngày vào ngày hiện tại
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            // Lấy ngày sau khi đã thêm
            Date nextDate = new Date(calendar.getTimeInMillis());
            // Thêm ngày vào danh sách
            dates.add(nextDate);
        }
	
        // Tạo danh sách thời gian trống cho mỗi ngày
        for (Date date : dates) {
        	Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(date);
        	ArrayList<Integer> id_timeperiods = new ArrayList<>();
            if(!(calendar1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
                for (int i = 1; i <= 15; i++) {
                    id_timeperiods.add(i);
                }
            }
            int dateid = 0;

            // Tạo danh sách thời gian sáng và chiều
            ArrayList<TimeAndID> morning = new ArrayList<>();
            ArrayList<TimeAndID> afternoon = new ArrayList<>();
            
            if (calendar1.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) dateid = 2;
            if (calendar1.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) dateid = 3;
            if (calendar1.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) dateid = 4;
            if (calendar1.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) dateid = 5;
            if (calendar1.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) dateid = 6;
            if (calendar1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) dateid = 7;
            
            // Loại bỏ các thời gian đã đặt
            for (DatLich l : lich1) {
                if (sameDate(date, l.getNgay())) {
                    id_timeperiods.remove((Integer) l.getIdTimePeriod());
                }
            }
            
            //loai bo thoi gian trung voi slot kham truc tuyen
            ArrayList<Integer> lich2 = new DangKyLichDAO().selectTimeByDayByDoctoID(id_bacsy, dateid);            
            for(Integer i : lich2) {
            	id_timeperiods.remove(i);   
            }
            
            for (int id : id_timeperiods) {
                if (id <= 8) {
                    morning.add(new TimeAndID(id, String.format("%02d:", 7 + (id - 1) / 2) + ((id % 2 == 0) ? "30" : "00")));
                } else {
                    afternoon.add(new TimeAndID(id, String.format("%02d:", 13 + (id - 8) / 2) + ((id % 2 != 0) ? "30" : "00")));
                }
            }
            
            // Xác định tên ngày
            String datename = "";
            if (dates.indexOf(date) == 0) {
            	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM", new Locale("vi"));
                datename = "Ngày mai, " + sdf.format(date);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd/MM", new Locale("vi"));
                datename = sdf.format(date);
            }
            Calendar calendar2 = Calendar.getInstance();
            // Xác định giờ, phút, giây, và mili giây thành 0 để chỉ lấy ngày tháng năm
            calendar2.set(Calendar.HOUR_OF_DAY, 0);
            calendar2.set(Calendar.MINUTE, 0);
            calendar2.set(Calendar.SECOND, 0);
            calendar2.set(Calendar.MILLISECOND, 0);
            Date today = new Date(calendar2.getTimeInMillis());
            
            TimeAndID t = new TimeAndID(dayDifference(date, today), datename);
            
			// Thêm thông tin thời gian vào kết quả
            TimeInfo ngaydatlich = new TimeInfo(t, morning, afternoon);
            result.add(ngaydatlich);
        }
        return result;
    }
	
	//select free time to book for online service
	public ArrayList<TimeInfo> selectFreeServiceSlotByDoctorID(int id_bacsy) {
        ArrayList<TimeInfo> result = new ArrayList<>();
        
        //lich da duoc dang ky tu van truc tuyen 
        ArrayList<KhamTrucTuyen> lich1 = new KhamTrucTuyenDAO().selectNextByDoctorID(id_bacsy);
        
        Calendar calendar = Calendar.getInstance();
        // Xác định giờ, phút, giây, và mili giây thành 0 để chỉ lấy ngày tháng năm
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        ArrayList<Date> dates = new ArrayList<>();
        
        // Thêm 7 ngày tiếp theo vào danh sách
        for (int i = 0; i < 7; i++) {
            // Thêm một ngày vào ngày hiện tại
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            // Lấy ngày sau khi đã thêm
            Date nextDate = new Date(calendar.getTimeInMillis());
            // Thêm ngày vào danh sách
            dates.add(nextDate);
        }
	
        // Tạo danh sách thời gian trống cho mỗi ngày
        for (Date date : dates) {
        	int dateid = 0;
        	Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(date);
        	
            // Tạo danh sách thời gian sáng và chiều
            ArrayList<TimeAndID> morning = new ArrayList<>();
            ArrayList<TimeAndID> afternoon = new ArrayList<>();
	            
            if (calendar1.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) dateid = 2;
            if (calendar1.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) dateid = 3;
            if (calendar1.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) dateid = 4;
            if (calendar1.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) dateid = 5;
            if (calendar1.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) dateid = 6;
            if (calendar1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) dateid = 7;
            if (calendar1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) dateid = 8;
        	// Tạo danh sách thời gian trống cho mỗi ngày duoc dang ky boi bac sy
            ArrayList<Integer> id_timeperiods = new DangKyLichDAO().selectTimeByDayByDoctoID(id_bacsy, dateid);
            
            // Loại bỏ các thời gian đã đặt
            for (KhamTrucTuyen l : lich1) {
                if (sameDate(date, l.getNgay())) {
                    id_timeperiods.remove((Integer) l.getIdTimePeriod());
                }
            }
            for (int id : id_timeperiods) {
                if (id <= 8) {
                    morning.add(new TimeAndID(id, String.format("%02d:", 7 + (id - 1) / 2) + ((id % 2 == 0) ? "30" : "00")));
                } else {
                    afternoon.add(new TimeAndID(id, String.format("%02d:", 13 + (id - 8) / 2) + ((id % 2 != 0) ? "30" : "00")));
                }
            }

            // Xác định tên ngày
            String datename = "";
            if (dates.indexOf(date) == 0) {
            	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM", new Locale("vi"));
                datename = "Ngày mai, " + sdf.format(date);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd/MM", new Locale("vi"));
                datename = sdf.format(date);
            }
            Calendar calendar2 = Calendar.getInstance();
            // Xác định giờ, phút, giây, và mili giây thành 0 để chỉ lấy ngày tháng năm
            calendar2.set(Calendar.HOUR_OF_DAY, 0);
            calendar2.set(Calendar.MINUTE, 0);
            calendar2.set(Calendar.SECOND, 0);
            calendar2.set(Calendar.MILLISECOND, 0);
            Date today = new Date(calendar2.getTimeInMillis());
            
            TimeAndID t = new TimeAndID(dayDifference(date, today), datename);
            
			// Thêm thông tin thời gian vào kết quả
            TimeInfo ngaydatlich = new TimeInfo(t, morning, afternoon);
            result.add(ngaydatlich);
        }
        return result;
    }
		
	//Chon ra lich kham Tu Van Truc Tuyen sap toi cho bac sy
	public ArrayList<ServiceInfo> UpcomingService_BS(int id_bacsy) {
        ArrayList<KhamTrucTuyen> tuvan = new KhamTrucTuyenDAO().selectNextByDoctorID(id_bacsy);
		ArrayList<ServiceInfo> result = SetNextService(tuvan);
        return result;
    }
	
	//Chon ra lich kham Tu Van Truc Tuyen sap toi cho nguoi dung
	public ArrayList<ServiceInfo> UpcomingService_BN(int id_benhnhan) {
        ArrayList<KhamTrucTuyen> tuvan = new KhamTrucTuyenDAO().selectNextByPatientID(id_benhnhan);
		ArrayList<ServiceInfo> result = SetNextService(tuvan);
        return result;
    }
	
	//Chon ra lich kham Tu Van Truc Tuyen da dat cho bac sy
	public ArrayList<ServiceInfo> History_BS(int id_bacsy) {
        ArrayList<KhamTrucTuyen> tuvan = new KhamTrucTuyenDAO().selectPrevByDoctorID(id_bacsy);
		ArrayList<ServiceInfo> result = SetPrevService(tuvan);       
        return result;
    }
	
	//Chon ra lich kham Tu Van Truc Tuyen da dat cho nguoi dung
	public ArrayList<ServiceInfo> History_BN(int id_benhnhan) {
        ArrayList<KhamTrucTuyen> tuvan = new KhamTrucTuyenDAO().selectPrevByPatientID(id_benhnhan);
		ArrayList<ServiceInfo> result = SetPrevService(tuvan);
        return result;
    }
	
	//Chon ra lich chua duoc danh gia boi nguoi dung
	public ArrayList<ServiceInfo> NotRated(int id_benhnhan) {
        ArrayList<KhamTrucTuyen> tuvan = new KhamTrucTuyenDAO().selectNotRated(id_benhnhan);
		ArrayList<ServiceInfo> result = SetPrevService(tuvan);
        return result;
    }
}
