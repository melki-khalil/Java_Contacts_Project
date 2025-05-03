package connect.com;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class InformationPanel extends JPanel implements KeyListener,MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel labelname= new JLabel("name");
	JTextField nameText= new JTextField();
	JLabel labelnumber= new JLabel("number");
	JTextField numberText= new JTextField();
	JPanel options=new JPanel();
	JLabel btnBack= new JLabel("");
	JLabel btnSave= new JLabel("save");
	JLabel image = new JLabel();
	PersonPanel contact;
	BasedFrame parent;
	static String path="";
	boolean isnew=false;
	InformationPanel(PersonPanel panel,BasedFrame parent,boolean isnew) {
		this.parent=parent;
	
		this.contact=panel;
		this.isnew=isnew;
		this.image.setIcon(this.contact.image.getIcon());
		Defaultpanel();
		this.nameText.setText(panel.name.getText());
		this.numberText.setText(panel.number.getText());
        
    }
	public void Defaultpanel(){
		
		this.setLayout(null);
        this.setBounds(0, 0, 500, 700);
        this.setBackground(new Color(240, 240, 240));
        
        this.image.addMouseListener(this);
        this.image.setHorizontalTextPosition(JLabel.CENTER);
        this.image.setForeground(Color.gray);
        this.image.setFont(new Font("Arial", Font.PLAIN, 40));
        this.image.setBounds(5, 105, 90, 90);
        this.image.setBackground(Color.gray);
        this.image.setOpaque(true);
        Border imageBorder = BorderFactory.createMatteBorder(1,1, 1, 1, new Color(0x7E6A69));
        this.image.setBorder(imageBorder);
        
        this.options.setLayout(null);
        this.options.setBackground(Color.gray);
        this.options.setOpaque(true);
        this.options.setBounds(0, 0, 500, 50);
        
        this.labelname.setBounds(50, 190, 400, 50);
        this.nameText.setBounds(50, 240, 400, 50);
        this.labelnumber.setBounds(50, 290, 400, 50);
        this.numberText.setBounds(50, 340, 400, 50);
        
        //font style
        Border border = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x7E6A69));
        this.labelname.setFont(new Font("Arial", Font.BOLD, 30));
        this.nameText.setFont(new Font("Arial", Font.BOLD, 30));
     
        this.nameText.setEditable(false);
        this.nameText.setBorder(border);
        this.labelnumber.setFont(new Font("Arial", Font.BOLD, 30));
        this.numberText.setFont(new Font("Arial", Font.BOLD, 30));
        this.numberText.setEditable(false);
        this.numberText.setBorder(border);
        
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
        
        this.add(this.image);
        this.add(this.options);
        this.add(this.labelname);
        this.add(this.nameText);
        this.add(this.labelnumber);
        this.add(this.numberText);
        
        
	}
	
	

	@Override
	public void keyTyped(KeyEvent e) {
		  Object src = e.getSource();

		    if (src == this.nameText) {
		        String str = this.nameText.getText();
		        char ch = e.getKeyChar();
		        if (Character.isLetterOrDigit(ch)) {
		        	 
		            this.nameText.setText(str + ch);
		        }
		       
		    }
	    else if (src == this.numberText) {
	    	char ch = e.getKeyChar();
	    	String str=this.numberText.getText();
	    	if (Character.isDigit(ch)) {
	    		this.numberText.setText(str+ch);
	    	}
	    }
	    
		}

		
	

	@Override
	public void keyPressed(KeyEvent e) {
		Object src = e.getSource();

	    if (src == this.nameText) {
	        String str = this.nameText.getText();

	        if ((e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) && !str.isEmpty()) {
	           
	            str = str.substring(0, str.length() - 1);
	            this.nameText.setText(str);
	        }
	    }
	    else if (src == this.numberText) {
	    	
	    	String str=this.numberText.getText();
	    	
	    	  if ((e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) && !str.isEmpty()) {
	    		str=str.substring(0,str.length()-1);
	    		this.numberText.setText(str);
	    	}
	    }
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		

		
		
		
		
		
	}
	
	
	
	//mouse listener	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object src =e.getSource();
		if (src==this.btnBack) {
			this.parent.fun.ShowContent();
		}
		if(src==this.btnSave) {
			if(nameText.getText().isEmpty()) {
	     	    JOptionPane.showMessageDialog(null, "You need to insert name", "Warning", JOptionPane.WARNING_MESSAGE);
			}else {
				this.contact.name.setText(this.nameText.getText());
				this.contact.number.setText(this.numberText.getText());
				this.contact.image.setIcon(this.image.getIcon());
				if(isnew) {
					parent.panelList.add(contact);
				}
				
				this.parent.fun.ShowContent();
			}
			
		}
		if (src == this.image) {
            JFileChooser fc = new JFileChooser(InformationPanel.path);
            fc.setAcceptAllFileFilterUsed(false);
            fc.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "jpeg", "png"));
            int option = fc.showOpenDialog(null);
            if (option == JFileChooser.APPROVE_OPTION) {
                File fl = fc.getSelectedFile();
                String sfile = fl.getAbsolutePath();
                InformationPanel.path=sfile;
                ImageIcon orignalIcon = new ImageIcon(sfile);
                Image pic = contact.fun.getHighQualityScaledImage(orignalIcon.getImage(), 90, 90);
                this.image.setIcon(new ImageIcon(pic));
            }
        }
		
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
		
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
		 if (src== this.image) {
	            this.image.setText("+");
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
		 if (src== this.image) {
	            this.image.setText("");
	        } 
		
	}

}
