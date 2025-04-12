package connect.com;

import javax.swing.*;
import java.awt.*;

public class CallingPanel extends JPanel {

    JButton btnBack = new JButton("Back");
  
    public CallingPanel(JLabel nameLabel, JLabel numberLabel) {
        
        this(nameLabel.getText(), numberLabel.getText());
    }

    public CallingPanel(String nameText, String numberText) {
        setLayout(null);
        setBackground(new Color(230, 240, 250));

        JLabel name = new JLabel(nameText);
        name.setFont(new Font("Arial", Font.BOLD, 30));
        name.setBounds(160, 100, 200, 50);

        JLabel number = new JLabel(numberText);
        number.setFont(new Font("Arial", Font.BOLD, 30));
        number.setBounds(160, 150, 200, 50);

        JLabel callingLabel = new JLabel("Calling...");
        callingLabel.setFont(new Font("Arial", Font.BOLD, 30));
        callingLabel.setBounds(160, 200, 200, 50);

        btnBack.setText("Back");
        btnBack.setBounds(180, 300, 100, 40);
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
