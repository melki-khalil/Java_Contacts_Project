package connect.com;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CallingPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2157170028194798706L;
	JButton btnBack = new JButton("Back");
  
    public CallingPanel(JLabel nameLabel, JLabel numberLabel) {
        
        this(nameLabel.getText(), numberLabel.getText());
    }

    public CallingPanel(String nameText, String numberText) {
        setLayout(null);
        setBackground(new Color(230, 240, 250));

        JLabel name = new JLabel(nameText);
        name.setFont(new Font("Arial", Font.BOLD, 30));
        name.setBounds(0, 100, 500, 50);
        name.setHorizontalAlignment(JLabel.CENTER);

        JLabel number = new JLabel(numberText);
        number.setFont(new Font("Arial", Font.BOLD, 30));
        number.setBounds(0, 150, 500, 50);
        number.setHorizontalAlignment(JLabel.CENTER);
        

        JLabel callingLabel = new JLabel("Calling...");
        callingLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        callingLabel.setBounds(0, 200, 500, 50);
        callingLabel.setHorizontalAlignment(JLabel.CENTER);
        btnBack.setText("Back");
        btnBack.setBounds(180, 580, 100, 40);
        btnBack.setBackground(Color.RED);
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setBorderPainted(false);
        btnBack.setOpaque(true);
        btnBack.setBorder(new RoundedBorder(10));

        add(name);
        add(number);
        add(callingLabel);
        add(btnBack);
    }
}
