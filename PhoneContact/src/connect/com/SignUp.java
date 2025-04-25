package connect.com;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Base64;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SignUp  extends JPanel implements KeyListener ,  MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel nameLabel= new JLabel("namename");
	JTextField nameinput= new JTextField();
	JLabel surnameLabel= new JLabel("surnamename");
	JTextField surnameinput= new JTextField();
	JLabel usernameLabel= new JLabel("username");
	JTextField usernameinput= new JTextField();
	JLabel EmailLabel= new JLabel("email");
	JTextField Emailinput= new JTextField();
	JLabel passpasswordLabel= new JLabel("Passesword");
	JTextField passpasswordinput= new JTextField();
	JLabel ConformPasspasswordLabel= new JLabel("Conform epassword");
	JTextField ConformPasspasspasswordinput= new JTextField();
	JButton btnCheack= new JButton("Log in");
	JLabel btnView= new JLabel("view");
	String inputPW="";
	String cp=encode("apple");
	String password=decode(cp);
	BasedFrame parent;
	
	 SignUp(BasedFrame parent){	 
		 this.parent=parent;
		 this.parent.getContentPane().removeAll();
		 Defaultpanel();
		 this.parent.add(this);
		 this.parent.revalidate();
		 this.repaint();
	 }
	
public void Defaultpanel(){
	
	this.setLayout(null);
    this.setBounds(0, 0, 500, 700);
    this.setBackground(new Color(240, 240, 240));
    
    int y=50;

    // position
    this.nameLabel.setBounds(50, y, 200, 50);
    this.nameinput.setBounds(50, y+50, 200, 50);
    this.surnameLabel.setBounds(270, y, 200, 50);
    this.surnameinput.setBounds(270, y+50, 200, 50);
    this.usernameLabel.setBounds(50, y+100, 400, 50);
    this.usernameinput.setBounds(50, y+150, 400, 50);
    this.EmailLabel.setBounds(50, y+200, 400, 50);
    this.Emailinput.setBounds(50, y+250, 400, 50);
    this.passpasswordLabel.setBounds(50, y+300, 400, 50);
    this.passpasswordinput.setBounds(50, y+350, 400, 50);
    this.ConformPasspasswordLabel.setBounds(50, y+400, 400, 50);
    this.ConformPasspasspasswordinput.setBounds(50, y+450, 400, 50);
    this.btnView.setBounds(350,10,50,30);
    this.btnCheack.setBounds(190,y+500,100,50);
    
    //font style
    Border border = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x7E6A69));
    this.EmailLabel.setFont(new Font("Arial", Font.BOLD, 30));
    this.Emailinput.setFont(new Font("Arial", Font.BOLD, 30));

    this.Emailinput.setBorder(border);
    this.passpasswordLabel.setFont(new Font("Arial", Font.BOLD, 30));
    this.passpasswordinput.setFont(new Font("Arial", Font.BOLD, 50));
    this.btnView.setFont(new Font("Arial", Font.ROMAN_BASELINE, 18));
    this.btnView.setOpaque(true);
    this.btnView.setBackground(Color.white);
    this.passpasswordinput.setBorder(border);
    
   
    
    this.Emailinput.addKeyListener(this);
    this.passpasswordinput.addKeyListener(this);
    this.btnView.addMouseListener(this);
    this.btnCheack.addMouseListener(this);

    this.add(this.nameLabel);
    this.add(this.nameinput);
    
    this.add(this.surnameLabel);
    this.add(this.surnameinput);
    
    this.add(this.usernameLabel);
    this.add(this.usernameinput);
    
    this.add(this.ConformPasspasswordLabel);
    this.add(this.ConformPasspasspasswordinput);
    
    
    this.add(this.EmailLabel);
    this.add(this.Emailinput);
    this.add(this.passpasswordLabel);
    this.add(this.passpasswordinput);
    this.passpasswordinput.add(btnView);
    this.add(this.btnCheack);
    
    
}


    public  String encode(String passpassword) {
        return Base64.getEncoder().encodeToString(passpassword.getBytes());
    }

    public String decode(String encoded) {
        byte[] decodedBytes = Base64.getDecoder().decode(encoded);
        return new String(decodedBytes);
    }
    public boolean isMail(String domain) {
    	return domain.equalsIgnoreCase("gmail.com") || domain.equalsIgnoreCase("yahoo.com") || domain.equalsIgnoreCase("outlook.com");
    }

    public void checkPassword() {
    	if (!this.inputPW.equals(this.password)) {
			JOptionPane.showMessageDialog(btnCheack, "Wrong passpassword ", "Warning", JOptionPane.WARNING_MESSAGE);
		}
    	else {
    		this.parent.Defaultpanel();
    	}
    }
@Override
public void keyTyped(KeyEvent e) {

}
@Override
public void keyPressed(KeyEvent e) {

}

@Override
public void keyReleased(KeyEvent e) {
	Object src = e.getSource();

    if (src == this.passpasswordinput) {
       
    	char ch = e.getKeyChar();
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && !this.inputPW.isEmpty()) {
            inputPW = this.inputPW.substring(0, this.inputPW.length() - 1);
        } else if (ch != KeyEvent.VK_DELETE && ch != KeyEvent.CHAR_UNDEFINED && ch!= KeyEvent.VK_BACK_SPACE) {
           
            if (Character.isLetterOrDigit(ch)|| !Character.isISOControl(ch)) {
                inputPW += e.getKeyChar();
            }
        }

        
        StringBuilder masked = new StringBuilder();
        for (int i = 0; i < this.inputPW.length(); i++) {
            masked.append("*");
        }
        this.passpasswordinput.setText(masked.toString());

      
    }
	
	
}

@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
}


