package connect.com;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MessagePanel extends JPanel implements KeyListener {
	JButton btnExit= new JButton();
	JTextField textbox= new JTextField();
	JLabel text= new JLabel();
	
	 MessagePanel(){
		 this.textbox.setBounds(0,525,500,35);
		 this.btnExit.setBounds(0, 0, 50, 50);
		 JPanel topPanel= new JPanel();
		 topPanel.setBounds(0, 0, 500, 50);
		 topPanel.setLayout(null);
		 topPanel.add(this.btnExit);
		 
		 this.textbox.addKeyListener(this);
		 
		 this.setLayout(null);
		 this.setBounds(0, 0, 500, 700);
		 
		 this.add(topPanel);
		 this.add(this.textbox);
	 }
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getSource()==this.textbox)	{
			if(e.getKeyCode()==10) {
				this.text.setText(this.textbox.getText());
				this.textbox.setText("");
			}
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
