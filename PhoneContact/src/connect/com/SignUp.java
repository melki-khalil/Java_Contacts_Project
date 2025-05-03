package connect.com;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Base64;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SignUp  extends JPanel implements  MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel contentPanel = new JPanel();
    JScrollPane scroll = new JScrollPane(contentPanel);	
    
	JLabel nameLabel= new JLabel("namename");
	JTextField nameinput= new JTextField();
	JLabel surnameLabel= new JLabel("surnamename");
	JTextField surnameinput= new JTextField();
	JLabel usernameLabel= new JLabel("username");
	JTextField usernameinput= new JTextField();
	JLabel EmailLabel= new JLabel("email");
	JTextField Emailinput= new JTextField();
	
	
	JLabel passpasswordLabel= new JLabel("Passesword");
	JPasswordField passwordinput = new JPasswordField();
	JLabel ConformPasswordLabel= new JLabel("Conform epassword");
	JPasswordField Conformpasswordinput = new JPasswordField();
	
	JLabel countryLabel= new  JLabel("Country code");
	CountrySelector countryCB= new CountrySelector();

	
	JButton btnCheck= new JButton("Log in");
	JLabel btnView= new JLabel("view");
	JLabel btnView2= new JLabel("view");
	String PW="";
	String CPW="";
	String cp;
	String password;
	String selected;
	BasedFrame parent;
	

	SignUp(BasedFrame parent) {	 
	    this.parent = parent;

	    this.parent.getContentPane().removeAll();
	    this.parent.repaint();
	    this.parent.revalidate();  // force refresh before creating

	    Defaultpanel();

	    this.parent.add(this);
	    this.parent.repaint();
	    this.parent.revalidate();
	}

	
public void Defaultpanel(){
	
	this.setLayout(null);
    this.setBounds(0, 0, 500, 700);
    this.setBackground(new Color(240, 240, 240));
    
    int y=50;

    // position
    contentPanel.setLayout(null);
    this.contentPanel.setPreferredSize(new Dimension(500, 750));

    this.scroll.setBounds(0, 0, 500, 700);
    
    this.nameLabel.setBounds(50, y, 200, 50);
    this.nameinput.setBounds(50, y+50, 200, 50);
    this.surnameLabel.setBounds(270, y, 200, 50);
    this.surnameinput.setBounds(270, y+50, 200, 50);
    this.usernameLabel.setBounds(50, y+100, 400, 50);
    this.usernameinput.setBounds(50, y+150, 400, 50);
    this.EmailLabel.setBounds(50, y+200, 400, 50);
    this.Emailinput.setBounds(50, y+250, 400, 50);
    this.passpasswordLabel.setBounds(50, y+300, 400, 50);
    this.passwordinput.setBounds(50, y+350, 400, 50);
    this.ConformPasswordLabel.setBounds(50, y+400, 400, 50);
    this.Conformpasswordinput.setBounds(50, y+450, 400, 50);
    this.countryLabel.setBounds(50,y+520,300,50);
    this.countryCB.setBounds(250,y+520,200,50);
    this.btnView.setBounds(350,10,50,30);
    this.btnView2.setBounds(350,10,50,30);
    this.btnCheck.setBounds(190,y+580,100,50);
    
    // borders
    Border border = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x7E6A69));
    
    this.nameinput.setBorder(border);
    this.surnameinput.setBorder(border);
    this.usernameinput.setBorder(border);
    this.Emailinput.setBorder(border);
    this.passwordinput.setBorder(border);
    this.Conformpasswordinput.setBorder(border);
    
    
    //font style
    Font generalFont=new Font("Arial", Font.BOLD, 30);
    Font passwordFont=new Font("Arial", Font.BOLD, 50);
    this.nameLabel.setFont(generalFont);
    this.nameinput.setFont(generalFont);
    this.surnameLabel.setFont(generalFont);
    this.surnameinput.setFont(generalFont);
    this.usernameLabel.setFont(generalFont);
    this.usernameinput.setFont(generalFont);
    this.EmailLabel.setFont(generalFont);
    this.Emailinput.setFont(generalFont);
    this.countryLabel.setFont(generalFont);
    this.countryCB.setFont(generalFont);
   
    
    this.passpasswordLabel.setFont(generalFont);
    this.passwordinput.setFont(passwordFont);
    this.ConformPasswordLabel.setFont(generalFont);
    this.Conformpasswordinput.setFont(passwordFont);
    this.btnView.setFont(new Font("Arial", Font.ROMAN_BASELINE, 18));
    this.btnView.setOpaque(true);
    this.btnView.setBackground(Color.white);
    this.btnView2.setFont(new Font("Arial", Font.ROMAN_BASELINE, 18));
    this.btnView2.setOpaque(true);
    this.btnView2.setBackground(Color.white);
    
    
    //scroll settings

    this.scroll.getVerticalScrollBar().setUnitIncrement(16); 
    this.scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    
    //add listeners
    this.btnCheck.removeMouseListener(this);
    
  
    this.btnView.addMouseListener(this);
    this.btnView2.addMouseListener(this);
    this.btnCheck.addMouseListener(this);
    this.countryCB.suggestionList.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
        	SignUp.this.selected =  countryCB.getSelected(e);
        		 
        }
    });
    


   // add components
    this.contentPanel.add(this.nameLabel);
    this.contentPanel.add(this.nameinput);
    
    this.contentPanel.add(this.surnameLabel);
    this.contentPanel.add(this.surnameinput);
    
    this.contentPanel.add(this.usernameLabel);
    this.contentPanel.add(this.usernameinput);
    
    this.contentPanel.add(this.ConformPasswordLabel);
    this.contentPanel.add(this.Conformpasswordinput);
    
    
    this.contentPanel.add(this.EmailLabel);
    this.contentPanel.add(this.Emailinput);
    
    this.contentPanel.add(this.passpasswordLabel);
    this.contentPanel.add(this.passwordinput);
    
    contentPanel.add(countryLabel);
    contentPanel.add(countryCB);
 
    this.passwordinput.add(btnView);
    this.Conformpasswordinput.add(btnView2);
    
    this.contentPanel.add(this.btnCheck);
    this.add(scroll);
    
    
}


    public  String encode(String passpassword) {
        return Base64.getEncoder().encodeToString(passpassword.getBytes());
    }

    public String decode(String encoded) {
        byte[] decodedBytes = Base64.getDecoder().decode(encoded);
        return new String(decodedBytes);
    }
  



@Override
public void mouseClicked(MouseEvent e) {
	Object src= e.getSource();
	if (src == this.btnCheck) {
	    String name = this.nameinput.getText();
	    String surname = this.surnameinput.getText();
	    String username = this.usernameinput.getText();
	    String email = this.Emailinput.getText();
	    this.PW=RegisteringFunctions.get_password(passwordinput);
	    this.CPW=RegisteringFunctions.get_password(Conformpasswordinput);
	    
	    if (RegisteringFunctions.isLetterOnly(name, "name") 
	        && RegisteringFunctions.isLetterOnly(surname, "surname")
	        && RegisteringFunctions.isValidUsername(username)
	        && RegisteringFunctions.isMail(email)
	        && RegisteringFunctions.checkPassword(PW, CPW)
	        && this.selected!=null){
	        
	        JOptionPane.showMessageDialog(null, "Your account has been added successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
	        
	        this.parent.fun.logging();  
	    }
	}

}

@Override
public void mousePressed(MouseEvent e) {
	Object src =e.getSource();
	if(src==this.btnView) {
	this.Conformpasswordinput.setEchoChar((char)0);
	}
	if(src==this.btnView2) {
		this.Conformpasswordinput.setEchoChar('•');
	}
	
}

@Override
public void mouseReleased(MouseEvent e) {
	Object src =e.getSource();
	if(src==this.btnView) {
		this.passwordinput.setEchoChar((char)0);
	}
	if(src==this.btnView2) {
		this.passwordinput.setEchoChar('•');
	}
	
}

@Override
public void mouseEntered(MouseEvent e) {
	Object src =e.getSource();
	if(src==this.btnView) {
		 this.btnView.setBackground(Color.LIGHT_GRAY);
	}
	if(src==this.btnView2) {
		this.btnView2.setBackground(Color.LIGHT_GRAY);
	}
	
}

@Override
public void mouseExited(MouseEvent e) {
	Object src =e.getSource();
	if(src==this.btnView) {
		 this.btnView.setBackground(Color.WHITE);
	}
	if(src==this.btnView2) {
		this.btnView2.setBackground(Color.WHITE);
	}
	
	
}
}


