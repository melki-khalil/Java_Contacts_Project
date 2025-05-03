package connect.com;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Base64;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SignIn extends JPanel implements  MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	JLabel userLabel= new JLabel("username or email");
	JTextField userinput= new JTextField();
	JLabel passwordLabel= new JLabel("Password");
	JPasswordField passwordinput= new JPasswordField();
	JButton btnCheck= new JButton("Log in");
	JLabel btnView= new JLabel("view");
	JLabel signUp= new JLabel("I don't have an account");
	String inputPW="";
	String cp=encode("apple");
	String password=decode(cp);
	 BasedFrame parent;
	
	 SignIn(BasedFrame parent){
		 this.parent=parent;
		 Defaultpanel();
		 this.parent.add(this);
	 }
	
public void Defaultpanel(){
	
	this.setLayout(null);
    this.setBounds(0, 0, 500, 700);
    this.setBackground(new Color(240, 240, 240));
    
    int y=100;

    // position
    this.userLabel.setBounds(50, y, 400, 50);
    this.userinput.setBounds(50, y+50, 400, 50);
    this.passwordLabel.setBounds(50, y+100, 400, 50);
    this.passwordinput.setBounds(50, y+150, 400, 50);
    this.btnView.setBounds(350,10,50,30);
    this.signUp.setBounds(170,y+200,200,20);
    this.btnCheck.setBounds(190,y+220,100,50);
    
    //font style
    Border border = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x7E6A69));
    this.userLabel.setFont(new Font("Arial", Font.BOLD, 30));
    this.userinput.setFont(new Font("Arial", Font.BOLD, 30));

    this.userinput.setBorder(border);
    this.passwordLabel.setFont(new Font("Arial", Font.BOLD, 30));
    this.passwordinput.setFont(new Font("Arial", Font.BOLD, 50));
    this.btnView.setFont(new Font("Arial", Font.ROMAN_BASELINE, 18));
    this.signUp.setFont(new Font("Arial", Font.ROMAN_BASELINE, 14));
 
    this.btnView.setOpaque(true);
    this.btnView.setBackground(Color.white);
    this.passwordinput.setBorder(border);
    
   
    
   
    this.btnView.addMouseListener(this);
    this.btnCheck.addMouseListener(this);
    this.signUp.addMouseListener(this);


    this.add(this.userLabel);
    this.add(this.userinput);
    this.add(this.passwordLabel);
    this.add(this.passwordinput);
    this.passwordinput.add(btnView);
    this.add(this.signUp);
    this.add(this.btnCheck);
    
    
}


    public  String encode(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public String decode(String encoded) {
        byte[] decodedBytes = Base64.getDecoder().decode(encoded);
        return new String(decodedBytes);
    }
    

    
   




@Override
public void mouseClicked(MouseEvent e) {
	Object src =e.getSource();

	if(src==this.btnCheck) {
		this.inputPW=RegisteringFunctions.get_password(passwordinput);
		String userd=this.userinput.getText();
		 if(userd.contains("@")) {
			if(	RegisteringFunctions.isMail(userd)) {
				
				if (RegisteringFunctions.checkPassword(this.password,this.inputPW))  {
					this.parent.getContentPane().removeAll();
					new loadingPage(this.parent);
				
				}

				}
				
			
		} else if(RegisteringFunctions.isValidUsername(userd)) {
			if (RegisteringFunctions.checkPassword(this.password,this.inputPW)) {
				this.parent.getContentPane().removeAll();
			    new loadingPage(this.parent);
			
			}

		}
		
	}
	
	
	 // signing up in case you don't own an account
    if(src==this.signUp) {
    	SignUp link= new SignUp(this.parent);
    	link.Defaultpanel();

    }
	
	
}

@Override
public void mousePressed(MouseEvent e) {
	Object src =e.getSource();
	if(src==this.btnView) {
		
		passwordinput.setEchoChar((char) 0);
		
	}
}

@Override
public void mouseReleased(MouseEvent e) {
	Object src =e.getSource();
	if(src==this.btnView) {
		
		  passwordinput.setEchoChar('•');

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