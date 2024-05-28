package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
	private static final String emailRegex = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
	private static final Pattern emailPattern = Pattern.compile(emailRegex);
	
	private static final String phoneRegex = "(84|0[3|5|7|8|9])+([0-9]{8})";
	private static final Pattern phonePattern = Pattern.compile(phoneRegex);
	
	public static boolean isValidEmail(String email) {
		if(email.trim().length()==0) return false;
		Matcher match = emailPattern.matcher(email);
		return match.matches();		
	}
	
	public static boolean isValidPhone(String phone) {
		if(phone.trim().length() == 0) return false;
		Matcher match = phonePattern.matcher(phone);
		return match.matches();
	}
}
