package datlichkham;

import java.time.LocalTime;

public class TimePeriod {
	private int id;
	private LocalTime startTime;
	
	public TimePeriod() {
		// TODO Auto-generated constructor stub
	}
	
	public TimePeriod(int id, LocalTime startTime) {
		super();
		this.id = id;
		this.startTime = startTime;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	@Override
	public String toString() {
		return "TimePeriod [id=" + id + ", startTime=" + startTime + "]";
	}
}
