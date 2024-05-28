package datlichkham;

import java.util.List;


public class TimeInfo {
	TimeAndID date;
    List<TimeAndID> morning;
    List<TimeAndID> afternoon;
    public TimeInfo() {}
    public TimeInfo(TimeAndID date, List<TimeAndID> morning, List<TimeAndID> afternoon) {
    	this.date = date;
        this.morning = morning;
        this.afternoon = afternoon;
    }
	public TimeAndID getDate() {
		return date;
	}
	public void setDate(TimeAndID date) {
		this.date = date;
	}
	public List<TimeAndID> getMorning() {
		return morning;
	}
	public void setMorning(List<TimeAndID> morning) {
		this.morning = morning;
	}
	public List<TimeAndID> getAfternoon() {
		return afternoon;
	}
	public void setAfternoon(List<TimeAndID> afternoon) {
		this.afternoon = afternoon;
	}
	@Override
	public String toString() {
		return "TimeInfo [date=" + date + ", morning=" + morning + ", afternoon=" + afternoon + "]";
	}
}
