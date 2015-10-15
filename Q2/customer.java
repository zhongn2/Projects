package intervals;

public class customer {
	double lat1;
	double lat2;
	int user_ID;
	String user_Name;
	
	public customer(){
	}
	
	public customer(double lat1, int user_ID, String user_Name, double lat2) {
		this.lat1 = lat1;
		this.lat2 = lat2;
		this.user_ID = user_ID;
		this.user_Name = user_Name;
	}
	
	public double getLat1() {
		return lat1;
	}
	public void setLat1(double lat1) {
		this.lat1 = lat1;
	}
	public double getLat2() {
		return lat2;
	}
	public void setLat2(double lat2) {
		this.lat2 = lat2;
	}
	public int getUser_ID() {
		return user_ID;
	}
	public void setUser_ID(int user_ID) {
		this.user_ID = user_ID;
	}
	public String getUser_Name() {
		return user_Name;
	}
	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}
	

}
