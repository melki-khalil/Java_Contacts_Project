package connect.com;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

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

public class KeyPadPanel extends JPanel implements MouseListener , KeyListener{
 
	
	
	JPanel NombersArea = new JPanel(new GridLayout(5, 3, 5, 5));
    JLabel textArea = new JLabel();
    JLabel btncall = new JLabel();
    JLabel removeNumber = new JLabel();
    JPopupMenu options ;
    JLabel btnOptions = new JLabel();
    JButton btnBack = new JButton();
    JMenuItem addcon= new JMenuItem("new contact");
    

    // Digit labels stored in an array for easy iteration
    JLabel[] buttons = new JLabel[12];
    String[] labels = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "*", "0", "#"};

    String number="";
    BasedFrame parent;
    
    FunctionsClass fun;
    public KeyPadPanel(BasedFrame parent) {
    	fun= new FunctionsClass(parent);
    	this.parent=parent;
        this.setLayout(null);
        this.setBounds(0, 200, 500, 500);
        setBackground(new Color(240, 240, 240));
        
        // Text area styling
        this.textArea.setBounds(20, 20, 450, 60);
        textArea.setOpaque(true);
        this.textArea.setBackground(Color.WHITE);
        this.textArea.setFont(new Font("Arial", Font.BOLD, 24));
        this.textArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.textArea.setHorizontalAlignment(SwingConstants.CENTER);
        this.textArea.setLayout(null);
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
        // go back button
        ImageIcon BackorignalIcon = new ImageIcon ("Back.png");
        Image Backimg= BackorignalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        this.btnBack.setIcon(new ImageIcon(Backimg));
        
        this.btnBack.setBounds(10,400,50,50);
        this.btnBack.setBackground(Color.WHITE);
        this.btnBack.setOpaque(true);
        this.btnBack.setFont(new Font("Arial", Font.BOLD, 24));
        this.btnBack.addMouseListener(this);
        
        //menu
        options=new JPopupMenu();
        ImageIcon dotsIcon = new ImageIcon("dots.png");
        Image scaledDots = dotsIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        this.btnOptions.setIcon(new ImageIcon(scaledDots));
        this.btnOptions.setBounds(20, 10, 50, 40);
        this.btnOptions.setOpaque(true);
        this.btnOptions.setBackground(Color.white);
        // adding contact function
        this.addcon.addActionListener(e -> {
        	 fun.editContact(fun.isAContact(this.textArea.getText()));
		    
			
		});
        
		this.options.add(this.addcon);
		// remove number button 
       
        ImageIcon deleteIcon = new ImageIcon("delete.png");
        Image scaledDelete = deleteIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        this.removeNumber.setIcon(new ImageIcon(scaledDelete));
        this.removeNumber.setOpaque(true);
    
        this.removeNumber.addMouseListener(this);
       	this.removeNumber.setBackground(Color.white);
        this.removeNumber.setBounds(390,10,50,30);
        
        
		btnOptions.addMouseListener(this);
		btncall.addMouseListener(this);
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(this);

        // Add components
        this.NombersArea.add(new JLabel(""));
        this.NombersArea.add(btncall);
        this.NombersArea.add(new JLabel(""));
        
        this.add(btnBack);
        this.add(textArea);
       this.add(NombersArea);
       this.setFocusable(true);
       requestFocusInWindow();
      
      
    }

    // a function that change the textArea value 
    private void updateText(String newText) {
        this.textArea.setText(newText);
        this.number=newText;
        if(newText.length()!=0) {
        	this.textArea.add(this.removeNumber);
        	this.textArea.add(this.btnOptions);
        	
        }
        else {
        	this.textArea.remove(this.removeNumber);
        	this.textArea.remove(this.btnOptions);
        	  
        }
        parent.input=newText;
        fun.findByInput(newText);
        this.textArea.revalidate();
        this.textArea.repaint();
      
    }
    //mouse listener event
    @Override
    public void mouseClicked(MouseEvent e) {
        for (JLabel btn : buttons) {
            if (e.getSource() == btn) {
            	updateText(textArea.getText() + btn.getText()); //add the button text value to the text araa 
                return;
            }
        }
        if (e.getSource() == this.removeNumber) {
        	String str = textArea.getText();
        	updateText(str.substring(0,str.length()-1)); //remove one character from the text
            return;
        }
        
        else if (e.getSource() == btncall) {
        	String str=this.textArea.getText();
        	
        	// checking the number type
        	
        	if ( str.startsWith("*")&& str.endsWith("#")) {
        	    JOptionPane.showMessageDialog(null, " Code "+str+" is in process","code", JOptionPane.INFORMATION_MESSAGE);
        	} else if( str.startsWith("*")) {
        	    JOptionPane.showMessageDialog(null, "Error: invalide code", "Error", JOptionPane.ERROR_MESSAGE);
        	} else if(str.endsWith("#")) {
        	    JOptionPane.showMessageDialog(null, "Error: invalide code", "Error", JOptionPane.ERROR_MESSAGE);
        	}
        	else if(!str.equals("")){
        		
        		//calling the check method function
        		PersonPanel contact= this.fun.isAContact(str);
        		fun.showCallingPanel(contact);
        	}
        	
        	
        
        }
        // show options menu
        else if(e.getSource()==this.btnOptions) {
			options.show(btnOptions, e.getX(), e.getY());
		}
       
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}


    
   // key listener for typing using the keyboard
	@Override
	public void keyTyped(KeyEvent e) {
		if(this==e.getSource()) {
			char ch = e.getKeyChar();
			
			String str = Character.toString(ch);

			if (Arrays.asList(this.labels).contains(str)) {
				updateText(textArea.getText() + ch);
	            return;
			}
			else if( e.getKeyCode()==0) {
				str= textArea.getText();
				updateText(str.substring(0,str.length()-1));
			}
		}
		

	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
	@Override
	public void addNotify() {
	    super.addNotify();
	    requestFocusInWindow();
	}

}
