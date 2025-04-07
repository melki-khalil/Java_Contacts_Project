package connect.com;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CallingPanel extends JPanel {
    JButton btnBack = new JButton("Back");

    public CallingPanel() {
        this.setLayout(null);
        this.setBackground(new Color(230, 240, 250));

        JLabel callingLabel = new JLabel("Calling...");
        callingLabel.setFont(new Font("Arial", Font.BOLD, 30));
        callingLabel.setBounds(150, 200, 200, 50);
        this.btnBack.setBorderPainted(false);
        this.btnBack.setContentAreaFilled(false);
        this.btnBack.setOpaque(true);
        this.btnBack.setBounds(180, 300, 40, 40);
        this.btnBack.setBackground(Color.red);
        this.btnBack.setBorder(new RoundedBorder(10));
        this.add(callingLabel);
        this.add(btnBack);
        
    }
}
