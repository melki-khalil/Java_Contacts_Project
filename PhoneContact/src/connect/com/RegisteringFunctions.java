package connect.com;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class RegisteringFunctions {
	public static boolean isLetterOnly(String str,String name) {
		if (str.isEmpty()) {
			JOptionPane.showMessageDialog(null, name+" field is empty ", "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
		}
		for(int i=0; i<str.length();i++) {
			if(!Character.isLetter(str.charAt(i))) {
				JOptionPane.showMessageDialog(null, "Invalid " +name+ ". it should contain letters only", "Warning", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
		
		return true;
	}
	public static boolean isValidUsername(String username)  {
	    if (username.isEmpty()) {
	    	JOptionPane.showMessageDialog(null, "username field is empty ", "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    for (int i = 0; i < username.length(); i++) {
	        char ch = username.charAt(i);
	        if (!Character.isLetterOrDigit(ch) && ch != '_'&& ch!='-') {
	        	 JOptionPane.showMessageDialog(null, "Invalid username", "Warning", JOptionPane.WARNING_MESSAGE);
	            return false;
	        }
	    }

	    return true; 
	}
	
	public static boolean isMail(String userd) {
    	String[] parts=userd.split("@");
		if(parts.length==2) {
			String domain=parts[1];
			if(domain.equalsIgnoreCase("gmail.com") || domain.equalsIgnoreCase("yahoo.com") || domain.equalsIgnoreCase("outlook.com")) {
			
				return true;
			}
			else {
				 JOptionPane.showMessageDialog(null, "Unknown or unsupported email domain", "Warning", JOptionPane.WARNING_MESSAGE);
				 return false;
			}
		}
		else {
			 JOptionPane.showMessageDialog(null, "Invalide Email formate", "Warning", JOptionPane.WARNING_MESSAGE);
			 return false;
		}
    	
    }
	

    public static boolean checkPassword(String PW, String CPW) {
    	if(PW.isEmpty()) {
    		JOptionPane.showMessageDialog(null, "you need to insert a password first ", "Warning", JOptionPane.WARNING_MESSAGE);
    		return false;
    	}
    	
    	else if(CPW.isEmpty()) {
    		JOptionPane.showMessageDialog(null, "password field is empty ", "Eror", JOptionPane.ERROR_MESSAGE);
    		return false;
    	}
    	else if(CPW.length()<7) {
    		JOptionPane.showMessageDialog(null, "your password should at least contains 7 characters ", "Warning", JOptionPane.WARNING_MESSAGE);
    		return false;
    	}
    	else {
    		
    		if (!PW.equals(CPW)) {
    			JOptionPane.showMessageDialog(null, "Wrong passpassword ", "Warning", JOptionPane.WARNING_MESSAGE);
    			return false;
    		}
    		else {
    			return true;
    		
    		}
    	}
    }

	
	 public static String get_password( JPasswordField textarea) {
		 String password;
		 char[] passwordChars = textarea.getPassword();
		 password= new String(passwordChars);  // If you need it as String
	
			return password;

	    }
	
}
