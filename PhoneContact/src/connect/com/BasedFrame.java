package connect.com;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class BasedFrame extends JFrame implements MouseListener {
	boolean modfav=false;
    JPanel contentPanel = new JPanel();
    JPanel buttonstPanel = new JPanel();
    JScrollPane scroll = new JScrollPane(contentPanel);	
    JLabel btnFav= new JLabel("favorite");
    JLabel btnctn= new JLabel("Contact");
    JLabel btntel= new JLabel();
    JLayeredPane pane= new JLayeredPane();
    
    
    
    List<PersonPanel> panelList = new ArrayList<>();
    public BasedFrame() {
    	
    	this.pane.setLayout(null);
    	this.pane.setBounds(0, 0, 500, 600);
    	this.buttonstPanel.setLayout(null);
    	this.buttonstPanel.setBounds(0, 600, 500, 100);
        this.contentPanel.setLayout(null);
        this.contentPanel.setPreferredSize(new Dimension(500, 600));
        this.btnFav.setBounds(330,0,165,65);
        this.btnctn.setBounds(165,0,165,65);
        this.btntel.setBounds(410,525,65,65);
        
        
        this.setTitle("app");
        this.setSize(500, 700);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(200, 200, 200));
        //keypad
        ImageIcon telorignalIcon = new ImageIcon ("keypad.png");
        Image telimg= telorignalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        this.btntel.setIcon(new ImageIcon(telimg));
        this.btntel.setBackground(Color.cyan);
        this.btntel.setOpaque(true);
        this.btntel.setHorizontalAlignment(JLabel.CENTER);
        this.btntel.setVerticalAlignment(JLabel.CENTER);
        this.btntel.addMouseListener(this);
        
         //contact section
        this.btnctn.setBackground(Color.gray);
        this.btnctn.setOpaque(true);
        this.btnctn.setFont(new Font("Arial", Font.PLAIN, 25));
        this.btnctn.setVerticalTextPosition(JLabel.CENTER);
        this.btnctn.setVerticalTextPosition(JLabel.BOTTOM);
        this.btnctn.addMouseListener(this);
        //favorite section
      
        
        this.btnFav.setOpaque(true);
        this.btnFav.setFont(new Font("Arial", Font.PLAIN, 25));
        this.btnFav.setVerticalTextPosition(JLabel.CENTER);
        this.btnFav.setVerticalTextPosition(JLabel.BOTTOM);
        this.btnFav.addMouseListener(this);
        

   
       
        Border border = BorderFactory.createMatteBorder(0, 1, 0, 1, new Color(0x706A69));
        this.btnFav.setBorder(border);
        this.setLayout(null);

        ImageIcon Icon = new ImageIcon("Icon.png"); 
        this.setIconImage(Icon.getImage());

        this.scroll.setBounds(0, 0, 500, 600); 
        this.scroll.getVerticalScrollBar().setUnitIncrement(16); 
        this.scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        //adding components
        this.pane.add(this.btntel, Integer.valueOf(1));
        this.pane.add(this.scroll, Integer.valueOf(0));
        this.add(pane);
        this.buttonstPanel.add(this.btnctn);
        this.buttonstPanel.add(this.btnFav);
        this.add(this.buttonstPanel);
        
        for (int i = 0; i < 10; i++) {
            PersonPanel panel = new PersonPanel(this); // pass this!
            panel.name.setText("name" + (i + 1));
            panel.image.setBackground(new Color((int) (Math.random() * 0x1000000)));
            this.contentPanel.add(panel);
            this.panelList.add(panel);
        }
        rearrangePanels(); 


    }
    
    //functions 
    
    public void rearrangePanels() {
        int y = 0;
        if(this.modfav) {
        	for (PersonPanel panel : panelList) {
                int height = 100 + panel.k;
                if(panel.isFavorite) {
                	panel.setBounds(0, y, 500, height);
                    y += height;
                }
            }
        }
        else {
        	
        	for (PersonPanel panel : panelList) {
        		int height = 100 + panel.k;
        		panel.setBounds(0, y, 500, height);
        		y += height;
        	}
        }

   
        if (y > 600) {
            contentPanel.setPreferredSize(new Dimension(500, y));
        } else {
            contentPanel.setPreferredSize(new Dimension(500, 600));
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    public void panelMode() {
    	int i=0;
    	if(this.modfav) {
    		for(PersonPanel panel: this.panelList) {
				if(panel.isFavorite) {
					panel.setBounds(0, 100*i, 500, 100);
					this.contentPanel.add(panel);
					i++;
				}
			}
    		
    	}
    	else {
    		for(PersonPanel panel: this.panelList) {
    			this.contentPanel.remove(panel);
    			panel.setBounds(0, 100*i, 500, 100);
    			this.contentPanel.add(panel);
    			i++;
    		}
    	}
    }
    
    
    public void removeContact(PersonPanel Contact) {
    	
    	for(PersonPanel panel: this.panelList) {
		
			this.contentPanel.remove(panel);
			
		}
    	this.panelList.remove(Contact);
    	
		panelMode();
		rearrangePanels(); 
		
		this.contentPanel.revalidate(); 
		this.contentPanel.repaint();
    	
    }
    public void goBack() {
        this.getContentPane().removeAll();
        this.pane.removeAll(); // Clear the layered pane just in case

        this.pane.add(this.btntel, Integer.valueOf(1));
        this.pane.add(this.scroll, Integer.valueOf(0));

        this.setLayout(null);
        this.add(pane);
        this.add(this.buttonstPanel);
        showContact();
        rearrangePanels();
        this.revalidate();
        this.repaint();
   }
    public void addContact(String number) {
    	PersonPanel panel = new PersonPanel(this);
    	panel.number.setText(number);
    	editContact(panel);
    	panel.image.setBackground(new Color((int) (Math.random() * 0x1000000)));
    	this.panelList.add(panel);
    	
    	
    }
    public void editContact(PersonPanel panel) {
    	this.getContentPane().removeAll();
    	InformationPanel edit= new InformationPanel(panel,this);
		
    	this.setLayout(null);
        this.add(edit);
        this.revalidate();
        this.repaint();
    	
    }
    public void ShowNumberPanel(){
		this.getContentPane().removeAll();
		NombersOPanel number= new NombersOPanel(this);
		
    	this.setLayout(null);
        this.add(number);
        this.revalidate();
        this.repaint();
        number.btnBack.addActionListener(e -> {
            this.goBack();
        });
	}
    public void showCallingPanel(PersonPanel panel) {
    	this.getContentPane().removeAll();
    	CallingPanel callp= new CallingPanel(panel.name,panel.number);
    	panel.name=panel.name;
    	panel.number=panel.number;
    	callp.setBounds(0, 0, 500, 700);
    	this.setLayout(null);
        this.add(callp);
        this.revalidate();
        this.repaint();
        
        callp.btnBack.addActionListener(e -> {
            this.goBack();
        });

    }
    public void showContact(){
    	this.modfav=false;
		for(PersonPanel panel: this.panelList) {
			if(panel.isFavorite) {
				this.contentPanel.remove(panel);
			}
		}
		panelMode();
		rearrangePanels(); 
		
		this.contentPanel.revalidate(); 
		this.contentPanel.repaint();    
		
    }
	@Override
	public void mouseClicked(MouseEvent e) {
		this.modfav=true;
		if(e.getSource()==this.btnFav) {
			for(PersonPanel panel: this.panelList) {
				this.contentPanel.remove(panel);
				
			}
			this.contentPanel.revalidate(); 
			this.contentPanel.repaint();    
			
			
			int i=0;
			panelMode();
			rearrangePanels(); 
			
		
	}
	//contact button action	
		else if(e.getSource()==this.btnctn) {
			showContact();
		
	}
		else if(e.getSource()==this.btntel) {
			ShowNumberPanel();
	        
	        
			    
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
