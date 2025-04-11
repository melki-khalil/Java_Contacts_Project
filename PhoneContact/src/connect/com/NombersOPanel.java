package connect.com;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class NombersOPanel extends JPanel implements MouseListener {
    JPanel NombersArea = new JPanel(new GridLayout(5, 3, 5, 5));
    JLabel textArea = new JLabel();
    JLabel btncall = new JLabel();
    JLabel removeNumber = new JLabel();
    JLabel options = new JLabel();
    BasedFrame parent;

    // Digit labels stored in an array for easy iteration
    JLabel[] buttons = new JLabel[12];
    String[] labels = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "*", "0", "#"};

    public NombersOPanel() {
        setLayout(null);
        setBounds(0, 200, 500, 500);
        setBackground(new Color(240, 240, 240));

        // Text area styling
        textArea.setBounds(20, 20, 450, 60);
        textArea.setOpaque(true);
        textArea.setBackground(Color.WHITE);
        textArea.setFont(new Font("Arial", Font.BOLD, 24));
        textArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        textArea.setHorizontalAlignment(SwingConstants.CENTER);
        textArea.setLayout(null);
        // Keypad area styling
        NombersArea.setBounds(50, 100, 400, 300);
        NombersArea.setBackground(new Color(230, 230, 230));

        // Create number buttons
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JLabel(labels[i], SwingConstants.CENTER);
            buttons[i].setOpaque(true);
            buttons[i].setBackground(Color.WHITE);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 24));
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.GRAY));
            buttons[i].addMouseListener(this);
            NombersArea.add(buttons[i]);
        }

        // Call button
        btncall.setBounds(220, 300, 50, 50);
        ImageIcon callIcon = new ImageIcon("telephone.png");
        Image scaledCall = callIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        btncall.setIcon(new ImageIcon(scaledCall));
        btncall.setOpaque(true);
        btncall.setBackground(new Color(0x4CAF50));
        btncall.setHorizontalAlignment(SwingConstants.CENTER);
        btncall.setVerticalAlignment(SwingConstants.CENTER);
        btncall.setBorder(new EmptyBorder(5, 5, 5, 5));
        btncall.addMouseListener(this);
        
        ImageIcon deleteIcon = new ImageIcon("delete.png");
        Image scaledDelete = deleteIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        this.removeNumber.setIcon(new ImageIcon(scaledDelete));
        this.removeNumber.setOpaque(true);
    
        this.removeNumber.addMouseListener(this);
       	this.removeNumber.setBackground(Color.white);
        this.removeNumber.setBounds(390,10,50,30);
        
        ImageIcon dotsIcon = new ImageIcon("dots.png");
        Image scaledDots = dotsIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        this.options.setIcon(new ImageIcon(scaledDots));
        this.options.setOpaque(true);
        this.options.setBackground(Color.white);
        this.options.setBounds(10,10,50,40);
        // Add components
        this.NombersArea.add(new JLabel(""));
        this.NombersArea.add(btncall);
        this.NombersArea.add(new JLabel(""));
        
        
        this.add(textArea);
       this.add(NombersArea);
    }

    
    private void updateText(String newText) {
        this.textArea.setText(newText);
        if(newText.length()!=0) {
        	this.textArea.add(this.removeNumber);
        	this.textArea.add(this.options);
        }
        else {
        	this.textArea.remove(this.removeNumber);
        	this.textArea.remove(this.options);
        }
        this.textArea.revalidate();
        this.textArea.repaint();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        for (JLabel btn : buttons) {
            if (e.getSource() == btn) {
            	updateText(textArea.getText() + btn.getText());
                return;
            }
        }
        if (e.getSource() == this.removeNumber) {
        	String str = textArea.getText();
        	updateText(str.substring(0,str.length()-1));
            return;
        }
        if (e.getSource() == btncall) {
            // Placeholder: Action when call button is pressed
            System.out.println("Calling: " + textArea.getText());
            JOptionPane.showMessageDialog(this, "Calling: " + textArea.getText());
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
