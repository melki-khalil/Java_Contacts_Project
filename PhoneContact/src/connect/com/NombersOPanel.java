package connect.com;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class NombersOPanel extends JPanel implements MouseListener {
    JPanel NombersArea = new JPanel(new GridLayout(5, 3, 5, 5));
    JLabel textArea = new JLabel();
    JLabel btncall = new JLabel();
    JLabel removeNumber = new JLabel();
    JPopupMenu options ;
    JLabel btnOptions = new JLabel();
    JButton btnBack = new JButton();
    JMenuItem edit= new JMenuItem("new contact");

    // Digit labels stored in an array for easy iteration
    JLabel[] buttons = new JLabel[12];
    String[] labels = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "*", "0", "#"};

  
    BasedFrame parent;
    
    
    public NombersOPanel(BasedFrame parent) {
    	this.parent=parent;
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
        options=new JPopupMenu();
        btncall.setBounds(220, 300, 50, 50);
        ImageIcon BackorignalIcon = new ImageIcon ("Back.png");
        Image Backimg= BackorignalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        this.btnBack.setIcon(new ImageIcon(Backimg));
        ImageIcon callIcon = new ImageIcon("telephone.png");
        Image scaledCall = callIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        btncall.setIcon(new ImageIcon(scaledCall));
        btncall.setOpaque(true);
        btncall.setBackground(new Color(0x4CAF50));
        btncall.setHorizontalAlignment(SwingConstants.CENTER);
        btncall.setVerticalAlignment(SwingConstants.CENTER);
        btncall.setBorder(new EmptyBorder(5, 5, 5, 5));
      
        
        this.edit.addActionListener(e -> {
		    
			this.parent.addContact(this.textArea.getText());
		});
		this.options.add(this.edit);
        
       btnBack.setBounds(10,400,50,50);
       btnBack.setBackground(Color.WHITE);
       btnBack.setOpaque(true);
       btnBack.setFont(new Font("Arial", Font.BOLD, 24));
       btnBack.addMouseListener(this);
       
        ImageIcon deleteIcon = new ImageIcon("delete.png");
        Image scaledDelete = deleteIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        this.removeNumber.setIcon(new ImageIcon(scaledDelete));
        this.removeNumber.setOpaque(true);
    
        this.removeNumber.addMouseListener(this);
       	this.removeNumber.setBackground(Color.white);
        this.removeNumber.setBounds(390,10,50,30);
        
        ImageIcon dotsIcon = new ImageIcon("dots.png");
        Image scaledDots = dotsIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        btnOptions.setIcon(new ImageIcon(scaledDots));
		btnOptions.setBounds(20, 10, 50, 40);
		btnOptions.setOpaque(true);
		btnOptions.setBackground(Color.white);
		btnOptions.addMouseListener(this);
		btncall.addMouseListener(this);
        // Add components
        this.NombersArea.add(new JLabel(""));
        this.NombersArea.add(btncall);
        this.NombersArea.add(new JLabel(""));
        
        this.add(btnBack);
        this.add(textArea);
       this.add(NombersArea);
      
    }

    
    private void updateText(String newText) {
        this.textArea.setText(newText);
        if(newText.length()!=0) {
        	this.textArea.add(this.removeNumber);
        	this.textArea.add(this.btnOptions);
        	
        }
        else {
        	this.textArea.remove(this.removeNumber);
        	this.textArea.remove(this.btnOptions);
        	  
        }
        this.parent.findByInput(newText);
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
        else if (e.getSource() == btncall) {
        	String str=this.textArea.getText();
        	
        	
        	if ( str.startsWith("*")&& str.endsWith("#")) {
        	    JOptionPane.showMessageDialog(null, " Code "+str+" is in process","code", JOptionPane.INFORMATION_MESSAGE);
        	} else if( str.startsWith("*")) {
        	    JOptionPane.showMessageDialog(null, "Error: invalide code", "Error", JOptionPane.ERROR_MESSAGE);
        	} else if(str.endsWith("#")) {
        	    JOptionPane.showMessageDialog(null, "Error: invalide code", "Error", JOptionPane.ERROR_MESSAGE);
        	}
        	else if(str!=""){
        		
        		PersonPanel panel = new PersonPanel(this.parent);
        		panel.name.setText("Unknown");
        		panel.number.setText(str);
        		this.parent.showCallingPanel(panel);
        	}
        	
        	
        
        }
        else if(e.getSource()==this.btnOptions) {
			options.show(btnOptions, e.getX(), e.getY());
		}
       
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
