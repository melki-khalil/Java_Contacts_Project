package connect.com;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class SignIn extends JPanel implements KeyListener ,  MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	JLabel userLabel= new JLabel("username or email");
	JTextField userinput= new JTextField();
	JLabel passpasswordLabel= new JLabel("Password");
	JTextField passpasswordinput= new JTextField();
	JButton btnCheack= new JButton("Log in");
	JLabel btnView= new JLabel("view");
	JLabel signUp= new JLabel("I don't have an account");
	String inputPW="";
	String cp=encode("apple");
	String password=decode(cp);
	BasedFrame parent;
	
	 SignIn(BasedFrame parent){
		 this.parent=parent;
		 Defaultpanel();
	 }
	
public void Defaultpanel(){
	
	this.setLayout(null);
    this.setBounds(0, 0, 500, 700);
    this.setBackground(new Color(240, 240, 240));
    
    int y=100;

    // position
    this.userLabel.setBounds(50, y, 400, 50);
    this.userinput.setBounds(50, y+50, 400, 50);
    this.passpasswordLabel.setBounds(50, y+100, 400, 50);
    this.passpasswordinput.setBounds(50, y+150, 400, 50);
    this.btnView.setBounds(350,10,50,30);
    this.signUp.setBounds(170,y+200,200,20);
    this.btnCheack.setBounds(190,y+220,100,50);
    
    //font style
    Border border = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x7E6A69));
    this.userLabel.setFont(new Font("Arial", Font.BOLD, 30));
    this.userinput.setFont(new Font("Arial", Font.BOLD, 30));

    this.userinput.setBorder(border);
    this.passpasswordLabel.setFont(new Font("Arial", Font.BOLD, 30));
    this.passpasswordinput.setFont(new Font("Arial", Font.BOLD, 50));
    this.btnView.setFont(new Font("Arial", Font.ROMAN_BASELINE, 18));
    this.signUp.setFont(new Font("Arial", Font.ROMAN_BASELINE, 14));
 
    this.btnView.setOpaque(true);
    this.btnView.setBackground(Color.white);
    this.passpasswordinput.setBorder(border);
    
   
    
    this.userinput.addKeyListener(this);
    this.passpasswordinput.addKeyListener(this);
    this.btnView.addMouseListener(this);
    this.btnCheack.addMouseListener(this);
    this.signUp.addMouseListener(this);


    this.add(this.userLabel);
    this.add(this.userinput);
    this.add(this.passpasswordLabel);
    this.add(this.passpasswordinput);
    this.passpasswordinput.add(btnView);
    this.add(this.signUp);
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
    		this.parent.fun.showContacts();
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
	Object src =e.getSource();

	if(src==this.btnCheack) {
		String userd=this.userinput.getText();
		if(userd.contains(" ")|| userd.length()<3) {
			JOptionPane.showMessageDialog(btnCheack, "invalide username  ", "Warning", JOptionPane.WARNING_MESSAGE);
		} else if(this.userinput.getText().contains("@")) {
			String[] parts=userd.split("@");
			if(parts.length==2) {
				String domain=parts[1];
				if(this.isMail(domain)) {
					JOptionPane.showMessageDialog(btnCheack, "valid Email ", "info", JOptionPane.INFORMATION_MESSAGE);
					this.checkPassword();
				}
				else {
					 JOptionPane.showMessageDialog(btnCheack, "Unknown or unsupported email domain", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
			else {
				 JOptionPane.showMessageDialog(btnCheack, "Invalide Email formate", "Warning", JOptionPane.WARNING_MESSAGE);
			}
			
			
		} else {
			JOptionPane.showMessageDialog(btnCheack, "valid username ", "info", JOptionPane.INFORMATION_MESSAGE);
			this.checkPassword();
		}
		
	}
	
	
	 // signing up in case you don't own an account
    if(src==this.signUp) {
    	SignUp link= new SignUp(this.parent);
    	link.Defaultpanel();
    	System.out.println("test");
    }
	
	
}

@Override
public void mousePressed(MouseEvent e) {
	Object src =e.getSource();
	if(src==this.btnView) {
		this.passpasswordinput.setText(this.inputPW);
	}
}

@Override
public void mouseReleased(MouseEvent e) {
	Object src =e.getSource();
	if(src==this.btnView) {
		StringBuilder masked= new StringBuilder();
		for (int i = 0; i < this.inputPW.length(); i++) {
            masked.append("*");
        }
        this.passpasswordinput.setText(masked.toString());

	}
}

@Override
public void mouseEntered(MouseEvent e) {
	Object src =e.getSource();

	if(src==this.btnView) {
		 this.btnView.setBackground(Color.LIGHT_GRAY);
	}
	if(src==this.signUp) {
    	
    	this.signUp.setForeground(Color.cyan);
    }
}

@Override
public void mouseExited(MouseEvent e) {
	Object src =e.getSource();
	if(src==this.btnView) {
		 this.btnView.setBackground(Color.white);
	}
	if(src==this.signUp) {
    	
    	this.signUp.setForeground(Color.black);
    }
}

}