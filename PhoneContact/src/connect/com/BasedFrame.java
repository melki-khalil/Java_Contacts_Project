package connect.com;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class BasedFrame extends JFrame {
	
	 BasedFrame(){
		 this.setTitle("app");
		 this.setSize(500,700);
		 this.setResizable(false);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 this.getContentPane().setBackground(new Color(200,200,200));
		 this.setLayout(null);
		 ImageIcon Icon= new ImageIcon("Icon.png"); // applicationICon
		 this.setIconImage(Icon.getImage());
		 
	 }
	
}
