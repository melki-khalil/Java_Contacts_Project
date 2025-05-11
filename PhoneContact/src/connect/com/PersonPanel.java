package connect.com;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;

import API.PhoneNumber;
import API.RecentCall;

public class PersonPanel extends JPanel implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	FunctionsClass fun= new FunctionsClass(this);
	
	
	
    int k = 0;
    int idA;
    int idC;
    JLabel image = new JLabel();
    JLabel name = new JLabel();
    JLabel number = new JLabel("");
    JLabel btnfav = new JLabel();
    JLabel btncall = new JLabel();
    JLabel btnmsg = new JLabel();
    JLabel timeLabel = new JLabel();
    JLabel dateLabel = new JLabel();
    Border sqrborder = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x706A69));
    boolean isFavorite = false;
    String coutryCode;
    
    JPopupMenu options;
    
    JLabel btnOptions = new JLabel();
    JMenuItem delete = new JMenuItem("delete");
    JMenuItem edit = new JMenuItem("edit");
    JMenuItem call = new JMenuItem("call");
    
    
    BasedFrame parent;
    PhoneNumber contact;
    RecentCall recent;
    
    PersonPanel(PhoneNumber contact, BasedFrame parent) {  
	
    	this.contact = contact;
        this.parent = parent;
        defaulPanel();
        contactPanel();
       
    }
    PersonPanel(RecentCall recent, BasedFrame parent) {  
    	
    	this.recent = recent;
    	this.parent = parent;
    	defaulPanel();
    	recentCallPanel();
    	
    }

    
    
    public void defaulPanel() {
    	 this.addMouseListener(this);
         
         
         
         ImageIcon callorignalIcon = new ImageIcon("telephone.png");
         ImageIcon messageorignalIcon = new ImageIcon("message.png");
         ImageIcon favorignalIcon = new ImageIcon("Star.png");

         Image Favimg = favorignalIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
         Image call = callorignalIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
         Image message = messageorignalIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

         
         
         // options bar
      

         // Create pop up menu
         this.options = new JPopupMenu(); 
         this.delete.addActionListener(e -> {
             parent.connection.deleteContact(contact);
             
             parent.fun.ShowContent();
         });
         this.edit.addActionListener(e -> {
             parent.fun.editContact(this);
         });
         this.call.addActionListener(e -> {
         	PersonPanel recent= fun.timeOfCall(this);
         	this.parent.recentList.add(recent);
         	
             parent.fun.showCallingPanel(this.contact);
         });
         this.options.add(this.edit);
         this.options.add(this.delete);

         // Dots icon that shows the pop up menu
         ImageIcon dotsIcon = new ImageIcon("dots.png");
         Image scaledDots = dotsIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
         btnOptions.setIcon(new ImageIcon(scaledDots));
         btnOptions.setBounds(440, 25, 50, 40);
         btnOptions.setOpaque(true);
         btnOptions.setBackground(Color.white);
         btnOptions.addMouseListener(this);
         
        //
    
         this.btncall.setIcon(new ImageIcon(call));
         this.btnmsg.setIcon(new ImageIcon(message));

         this.btnfav.setIcon(new ImageIcon(Favimg));
         this.btnfav.setBounds(370, 10, 60, 60);
         this.btnfav.addMouseListener(this);
         this.btnfav.setOpaque(true);
         this.image.addMouseListener(this);
         this.image.setHorizontalTextPosition(JLabel.CENTER);
         this.image.setForeground(Color.gray);
         this.image.setFont(new Font("Arial", Font.PLAIN, 40));
         this.image.setBounds(5, 5, 90, 90);
         this.image.setOpaque(true);
         this.setLayout(null);

         this.name.setBounds(120, 15, 300, 40);
         this.number.setBounds(120, 50, 300, 40);
         this.name.setFont(new Font("Arial", Font.PLAIN, 25));
         
         this.btncall.setBounds(120, 100, 60, 60);
         this.btnmsg.setBounds(200, 100, 60, 60);
         this.btncall.setHorizontalAlignment(JLabel.CENTER);
         this.btnmsg.setHorizontalAlignment(JLabel.CENTER);
         this.btncall.addMouseListener(this);
         this.btnmsg.addMouseListener(this);
         
         
        
         
         // borders
         Border border = BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(0x706A69));
         
         this.setBorder(border);
    }
    
    public void contactPanel() {
    	this.idA=contact.getIdA();
        this.idC=contact.getIdC();
        
        this.name.setText(contact.getName());
        this.number.setText(contact.getNumber());
        this.isFavorite = contact.getIsFav();
        backgeoundFav();
        Image  orignalImage=parent.fun.setImageFromBytes(contact.getImage());
        ImageIcon Icon=new ImageIcon(orignalImage);
        this.image.setIcon(Icon);
        
        this.setBackground(Color.white);
        this.setOpaque(true);
    	
         // add compounds
         this.add(this.btnmsg);
         this.add(this.btncall);
         this.add(image);
         this.add(this.name);
         this.add(this.number);
         this.add(this.btnfav);
         this.add(btnOptions);

        
    }
    public void recentCallPanel() {
    	
    	 this.timeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
         this.timeLabel.setBounds(300, 50, 200, 40);
         this.dateLabel.setFont(new Font("Arial", Font.PLAIN, 15));
         this.dateLabel.setBounds(300, 10, 200, 40);
    	this.idC=this.recent.getIdC();
    	
    	this.name.setText(this.recent.getName());
    	this.number.setText(this.recent.getNumber());
    	
    	this.timeLabel.setText(this.recent.getCallTime().toString());
    	this.dateLabel.setText(this.recent.getCallDate().toString());

    	Image  orignalImage=parent.fun.setImageFromBytes(this.recent.getImage());
    	ImageIcon Icon=new ImageIcon(orignalImage);
    	this.image.setIcon(Icon);
    	
    	this.setBackground(Color.white);
    	this.setOpaque(true);
    	
    	// add compounds
    	this.add(this.btnmsg);
    	this.add(this.btncall);
    	this.add(image);
    	this.add(this.name);
    	this.add(this.number);
    	this.add(this.timeLabel);
    	this.add(this.dateLabel);
    	
    	
    }
 
  
 
    public void backgeoundFav() {
    	if(this.isFavorite) {
    		this.btnfav.setBackground(Color.yellow);
    		
    	}
    	else{
    		this.btnfav.setBackground(Color.white);
    	}
    
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == this.btnfav) {
        	this.isFavorite = !this.isFavorite;
        	backgeoundFav();
            this.contact.setIsFav(this.isFavorite);
            this.parent.connection.updateFav(contact);
            this.parent.contentPanel.removeAll();
            this.parent.fun.rearrangePanels(this.parent.panelList);
            
          
        } 
        else if (e.getSource() == this.btncall) {
        	
           LocalTime callTime = LocalTime.now();
           LocalDate callDate= LocalDate.now();
           parent.connection.insertRecentCall(this.idC, callTime, callDate);
            parent.fun.showCallingPanel(this.contact);

        }
        else if (e.getSource() == this.btnOptions) {
            options.show(btnOptions, e.getX(), e.getY());
        } 
        else {
            if (this.k == 0) {
                this.k = 70; // expand
            } else {
                this.k = 0;  // collapse
            }
            
            if (parent.searching) {
                parent.fun.findByInput(parent.input); 
                
            }
            else {
            	parent.fun.findByInput(parent.number.number); 
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {
   if (e.getSource() == this.btncall) {
            this.btncall.setBorder(this.sqrborder);
        } else if (e.getSource() == this.btnmsg) {
            this.btnmsg.setBorder(this.sqrborder);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
    
    }

	
}
