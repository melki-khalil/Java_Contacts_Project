package connect.com;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Contact {

	public static void main(String[] args) {
		BasedFrame frame = new BasedFrame();
		
		 	
	        frame.setVisible(true);
	        
	        frame.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent e) {
	                frame.SaveAndClose();  // Safe to do here!
	            }
	        });


	}

}
