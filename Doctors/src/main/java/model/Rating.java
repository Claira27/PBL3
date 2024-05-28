package model;

public class Rating {
	private int stars;
	private int totalRatings;
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	public int getTotalRatings() {
		return totalRatings;
	}
	public void setTotalRatings(int totalRatings) {
		this.totalRatings = totalRatings;
	}
	public Rating() {
		// TODO Auto-generated constructor stub
	}
	public Rating(int stars, int totalRatings) {
		super();
		this.stars = stars;
		this.totalRatings = totalRatings;
	}
}
