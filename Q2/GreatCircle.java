package intervals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.TreeMap;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

/*
 * @author: Ning zhong
 * @params: input: filename
 * 			output: userid and username fits the requirements
 */

public class GreatCircle {
	
	private static double OFFICE_LAT = 53.3381985;
	private static double OFFICE_LONG = -6.2592576;
	private static double EARTH_CIRC_METERS = 40030218;
	TreeMap<Integer, String> output = new TreeMap<Integer, String>();
	/*
	 * Read txt file line by line
	 * input: file name
	 */
	public void ReadFile(String fileName) {
		try {
			File file = new File(fileName);
			FileReader fReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fReader);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				invite_List(line);
			}
			fReader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * input: the string line from file
	 * parse the string using jsonparser
	 * output: customer object contains contents
	 * 
	 */
	
	private customer parseJson(String s){
		JSONParser jsonParser = new JSONParser();
		customer curr = new customer();
		
		try {
			JSONObject object = (JSONObject) jsonParser.parse(s);
			curr.setLat1(Double.parseDouble(object.get("latitude").toString()));
			curr.setUser_ID(Integer.parseInt(object.get("user_id").toString()));
			curr.setUser_Name(object.get("name").toString());
			curr.setLat2(Double.parseDouble(object.get("longitude").toString()));
			
			} catch (Exception e) {
			e.printStackTrace();
		}
		return curr;
	}
	
	/*
	 * get the result of distance and put into treemap
	 * treemap will sort the userid in an order
	 */
	
	public void invite_List(String s){
		customer singleCustomer = parseJson(s);
		if(cal_Distance(singleCustomer, OFFICE_LAT, OFFICE_LONG)){
			output.put(singleCustomer.user_ID, singleCustomer.user_Name);
		}
	}
	
	/*
	 * input: customer object and specific office location
	 * function: calculate the distance of customer and office location
	 * output: return true if the customer is within 100km
	 * else return false
	 */
	private boolean cal_Distance(customer singleCustomer, double officeLat,
			double officeLong) {
		double lat1 = Math.toRadians(singleCustomer.getLat1());
		double lon1 = Math.toRadians(singleCustomer.getLat2());
		double lat2 = Math.toRadians(officeLat);
		double lon2 = Math.toRadians(officeLong);
		
		double distance = Math.acos( ( Math.cos( lat1 ) * Math.cos(lat2 ) ) +
				 ( Math.sin( lat1 ) * Math.sin( lat2 ) ) * ( Math.cos(lon1 - lon2)));
		 if(distance * EARTH_CIRC_METERS <= 100000){
			 return true;
		 }
		 return false;
	}

	
	public static void main(String[] args) {
		GreatCircle circle = new GreatCircle();
		circle.ReadFile("C:/Users/ning zhong/Documents/JavaWorks/Test/src/intervals/customers.txt");
		System.out.println(circle.output);
	}	
}
