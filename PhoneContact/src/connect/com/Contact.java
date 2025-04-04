package connect.com;

import java.awt.Color;

import javax.swing.JLabel;

public class Contact {

	public static void main(String[] args) {
		BasedFrame frame= new BasedFrame();
		
		for (int i = 0; i < 6; i++) {
			PersonPanel panel = new PersonPanel();
			
            panel.image.setBackground(new Color((int) (Math.random() * 0x1000000)));
            panel.setBounds(0, 100*i, 500, 100);
            frame.add(panel); 
        }
		frame.setVisible(true);
	}

}
