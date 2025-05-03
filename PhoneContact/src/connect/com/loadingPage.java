package connect.com;

import java.awt.Color;
import java.awt.Image;

import javax.swing.*;

public class loadingPage extends JPanel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BasedFrame parent;
	JLabel Icon=new JLabel();
	JProgressBar bar =new JProgressBar();
	 loadingPage(BasedFrame parent){
		 this.parent=parent;
		 this.parent.getContentPane().removeAll();
		 Defaultpanel();
		 this.parent.add(this);
			this.parent.revalidate();
			this.parent.repaint();
		
		 fill(5);
	 }
	public void Defaultpanel(){
		
		
		
		this.setLayout(null);
	    this.setBounds(0, 0, 500, 700);
	    this.setBackground(new Color(240, 240, 240));
	    	int y=200;
	    	ImageIcon iconimage= new ImageIcon("Icon.png");
	    	Image image= parent.fun.getHighQualityScaledImage(iconimage.getImage(), 90, 90);
	    	iconimage= new ImageIcon(image);
		    this.Icon.setIcon(iconimage);
		    this.Icon.setBounds(200, y, 100, 100);
		    
		   
		    bar.setBounds(60, y+120, 350, 50);
	    this.add(Icon);
	    this.add(bar);

	    }
	
	public void fill(int step) {
	    Timer timer = new Timer(100, null);
	    timer.addActionListener(e -> {
	        int currentValue = bar.getValue();
	        if (currentValue >= 100) {
	            timer.stop();
	            this.parent.Defaultpanel();
	        } else {
	            bar.setValue(currentValue + step);
	        }
	    });
	    timer.start();
	}

	
}
