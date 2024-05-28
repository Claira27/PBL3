package datlichkham;

public class TimeAndID {
	private int id;
	private String time;
	public TimeAndID() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public TimeAndID(int id, String time) {
		super();
		this.id = id;
		this.time = time;
	}
	@Override
	public String toString() {
		return "TimeAndId [id=" + id + ", time=" + time + "]";
	}
}
