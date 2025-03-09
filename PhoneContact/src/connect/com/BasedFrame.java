package connect.com;

import java.awt.Color;

import javax.swing.*;

public class BasedFrame extends JFrame {
	
	 BasedFrame(){
		 this.setTitle("app");
		 this.setSize(500,700);
		 this.setResizable(false);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 this.getContentPane().setBackground(new Color(200,200,200));
		 ImageIcon Icon= new ImageIcon("Icon.png"); // applicationICon
		 this.setIconImage(Icon.getImage());
		 this.setVisible(true);
	 }
	
}
