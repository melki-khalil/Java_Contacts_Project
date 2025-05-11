package connect.com;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import API.PhoneNumber;

public class CallingPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2157170028194798706L;
	JButton btnBack = new JButton("End the call");
	PhoneNumber contact;
   

    public CallingPanel( PhoneNumber contact) {
    	
    this.contact= contact;
        setLayout(null);
        setBackground(new Color(230, 240, 250));

        JLabel name = new JLabel(this.contact.getName());
        name.setFont(new Font("Arial", Font.BOLD, 30));
        name.setBounds(0, 100, 500, 50);
        name.setHorizontalAlignment(JLabel.CENTER);

        JLabel number = new JLabel(this.contact.getNumber());
        number.setFont(new Font("Arial", Font.BOLD, 30));
        number.setBounds(0, 150, 500, 50);
        number.setHorizontalAlignment(JLabel.CENTER);
        

        JLabel callingLabel = new JLabel("Calling...");
        callingLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        callingLabel.setBounds(0, 200, 500, 50);
        callingLabel.setHorizontalAlignment(JLabel.CENTER);
      
        btnBack.setBounds(160, 580, 200, 40);
        btnBack.setBackground(Color.RED);
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setBorderPainted(false);
        btnBack.setOpaque(true);
       

        add(name);
        add(number);
        add(callingLabel);
        add(btnBack);
    }
}
