package connect.com;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InformationPanel extends JPanel implements KeyListener,MouseListener {
	JLabel labelname= new JLabel("name");
	JTextField nameText= new JTextField();
	JLabel labelnumber= new JLabel("number");
	JTextField numberText= new JTextField();
	JPanel options=new JPanel();
	JLabel btnBack= new JLabel("");
	JLabel btnSave= new JLabel("save");
	PersonPanel contact;
	BasedFrame parent;
	InformationPanel(PersonPanel panel,BasedFrame parent) {
		this.parent=parent;
		this.contact=panel;
		
		Defaultpanel();
		this.nameText.setText(panel.name.getText());
		this.numberText.setText(panel.number.getText());
        
    }
	public void Defaultpanel(){
		this.setLayout(null);
        this.setBounds(0, 0, 500, 700);
        this.setBackground(new Color(240, 240, 240));
        
        
        this.options.setLayout(null);
        this.options.setBackground(Color.gray);
        this.options.setOpaque(true);
        this.options.setBounds(0, 0, 500, 50);
        
        this.labelname.setBounds(50, 100, 400, 50);
        this.nameText.setBounds(50, 150, 400, 50);
        this.labelnumber.setBounds(50, 250, 400, 50);
        this.numberText.setBounds(50, 300, 400, 50);
        
        //font style
        this.labelname.setFont(new Font("Arial", Font.BOLD, 30));
        this.nameText.setFont(new Font("Arial", Font.BOLD, 30));
        this.labelnumber.setFont(new Font("Arial", Font.BOLD, 30));
        this.numberText.setFont(new Font("Arial", Font.BOLD, 30));
        this.btnSave.setFont(new Font("Arial", Font.BOLD, 30));
        
        ImageIcon BackorignalIcon = new ImageIcon ("Back.png");
        Image Backimg= BackorignalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        this.btnBack.setIcon(new ImageIcon(Backimg));
        this.btnBack.setBounds(0, 0, 50, 50);
        this.btnSave.setBounds(400,0,100,50);
        
        this.btnBack.setBackground(Color.GRAY);
        this.btnSave.setBackground(Color.GRAY);
        
        this.btnBack.setOpaque(true);
        this.btnSave.setOpaque(true);
        
        this.nameText.addKeyListener(this);
        this.numberText.addKeyListener(this);
        this.btnBack.addMouseListener(this);
        this.btnSave.addMouseListener(this);
        
        this.options.add(this.btnBack);
        this.options.add(this.btnSave);
        
        
        this.add(this.options);
        this.add(this.labelname);
        this.add(this.nameText);
        this.add(this.labelnumber);
        this.add(this.numberText);
        
        
	}
	
	public static String removeLastIfSpecial(String input) {
	    if (input == null || input.isEmpty()) return input;

	    char lastChar = input.charAt(input.length() - 1);
	    if (!Character.isLetterOrDigit(lastChar)&& lastChar!=' ') {
	        return input.substring(0, input.length() - 1);
	    }
	    return input;
	}
	public static String onlyDigit(String input) {
	    if (input == null || input.isEmpty()) return input;

	    char lastChar = input.charAt(input.length() - 1);
	    if (!Character.isDigit(lastChar)) {
	        return input.substring(0, input.length() - 1);
	    }
	    return input;
	}


	@Override
	public void keyTyped(KeyEvent e) {
		
		}

		
	

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Object src=e.getSource();
		
		
	    if (src == this.nameText) {
	        String str = removeLastIfSpecial(this.nameText.getText());
	        this.nameText.setText(str);
	    } else if (src == this.numberText) {
	        String str = onlyDigit(this.numberText.getText());
	        this.numberText.setText(str);
	    }
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Object src =e.getSource();
		if (src==this.btnBack) {
			parent.goBack();
		}
		if(src==this.btnSave) {
			this.contact.name.setText(this.nameText.getText());
			this.contact.number.setText(this.numberText.getText());
			parent.goBack();
			
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		Object src =e.getSource();
		if (src==this.btnBack) {
			this.btnBack.setBackground(Color.LIGHT_GRAY);
		}
		if(src==this.btnSave) {
			this.btnSave.setBackground(Color.LIGHT_GRAY);
			
		}
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		Object src =e.getSource();
		if (src==this.btnBack) {
			this.btnBack.setBackground(Color.GRAY);
		}
		if(src==this.btnSave) {
			this.btnSave.setBackground(Color.GRAY);
		}
		
	}

}
