package connect.com;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Contact {

	public static void main(String[] args) {
		BasedFrame frame = new BasedFrame();
		
		 	
	  
	        
	        frame.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent e) {
	                frame.fun.SaveAndClose();  // Safe to do here!
	            }
	        });


	}

}
