package connect.com;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.JTextField;
import javax.swing.border.Border;

public class BasedFrame extends JFrame implements MouseListener ,KeyListener{
	boolean modfav=false;
	 boolean searching=true;
    JPanel contentPanel = new JPanel();
    JPanel buttonstPanel = new JPanel();
    JScrollPane scroll = new JScrollPane(contentPanel);	
    JLabel btnFav= new JLabel("favorite");
    JLabel btnctn= new JLabel("Contact");
    JLabel btntel= new JLabel();
    JLayeredPane pane= new JLayeredPane();
    JPanel searchPanel=new JPanel();
    JTextField searchText= new JTextField();

    
    
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
        
        for(int i=1; i<11;i++) {
        	PersonPanel panel =new PersonPanel(this);
        	panel.name.setText("name"+i);
        	PersonPanel panel2 =new PersonPanel(this);
        	panel2.name.setText("person"+i);
        	this.panelList.add(panel);
        	this.panelList.add(panel2);
        }
   
       
        Border border = BorderFactory.createMatteBorder(0, 1, 0, 1, new Color(0x706A69));
        this.btnFav.setBorder(border);
        this.setLayout(null);

        ImageIcon Icon = new ImageIcon("Icon.png"); 
        this.setIconImage(Icon.getImage());

        this.searchText.setBounds(5, 5, 475, 50);
        this.searchText.setFont(new Font("Arial", Font.BOLD, 24));
        this.searchText.addKeyListener(this);
        
        this.scroll.setBounds(0, 60, 500, 540); 
        this.scroll.getVerticalScrollBar().setUnitIncrement(16); 
        this.scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        //adding components
        this.pane.add(this.btntel, Integer.valueOf(1));
        this.pane.add(this.scroll, Integer.valueOf(0));
        this.pane.add(this.searchText, Integer.valueOf(0));
        this.add(pane);
        this.buttonstPanel.add(this.btnctn);
        this.buttonstPanel.add(this.btnFav);
        this.add(this.buttonstPanel);
        
        showContact();
        rearrangePanels(this.panelList); 


    }
    
    //functions 
    public static String removeLastIfSpecial(String input) {
	    if (input == null || input.isEmpty()) return input;

	    char lastChar = input.charAt(input.length() - 1);
	    if (!Character.isLetterOrDigit(lastChar)&& lastChar!=' ') {
	        return input.substring(0, input.length() - 1);
	    }
	    return input;
	}
    
    public void rearrangePanels( List<PersonPanel> panels ) {
        int y = 0;
        if(this.modfav) {
        	for (PersonPanel panel :panels) {
                int height = 100 + panel.k;
                if(panel.isFavorite) {
                	panel.setBounds(0, y, 500, height);
                	this.contentPanel.add(panel);
                    y += height;
                }
            }
        }
        else {
        	
        	for (PersonPanel panel :panels) {
        		int height = 100 + panel.k;
        		panel.setBounds(0, y, 500, height);
        		this.contentPanel.add(panel);
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
    			panel.setBounds(0, 100*i, 500, 100);
    			this.contentPanel.add(panel);
    			i++;
    		}
    	}
    }
    
    
    public void removeContact(PersonPanel Contact) {
    	
    	
		
		this.contentPanel.removeAll();
		
    	this.panelList.remove(Contact);
    	
		panelMode();
		 rearrangePanels(this.panelList);  
		
		this.contentPanel.revalidate(); 
		this.contentPanel.repaint();
    	
    }
    public void goBack() {
    	this.searching=true;
        this.getContentPane().removeAll();
        this.pane.removeAll(); // Clear the layered pane just in case
        this.scroll.setBounds(0, 60, 500, 540);
    	this.pane.setBounds(0, 0, 500, 600);
        this.pane.add(this.btntel, Integer.valueOf(1));
        this.pane.add(this.scroll, Integer.valueOf(0));
        this.pane.add(this.searchText, Integer.valueOf(0));
        this.setLayout(null);
        this.add(pane);
        this.add(this.buttonstPanel);
        showContact();
         rearrangePanels(this.panelList); 
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
		
		this.scroll.setBounds(0,0,500,200);
        this.pane.setBounds(0,0,500,200);
        
   
        
    	this.setLayout(null);
    	this.add(this.pane);
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
		this.contentPanel.removeAll();
		
		panelMode();
		 rearrangePanels(this.panelList);  
		
		this.contentPanel.revalidate(); 
		this.contentPanel.repaint();    
		
    }
    
    public void findByInput(String search) {
        List<PersonPanel> result = new ArrayList<>();
        
        this.contentPanel.removeAll();

        if (search.isEmpty()) {
            result = this.panelList;
        } else {
            for (PersonPanel panel : this.panelList) {
                boolean matchName = panel.name.getText().toLowerCase().startsWith(search.trim().toLowerCase());
                boolean matchNumber =  panel.number.getText().startsWith(search);

                if ((matchName || matchNumber)) {

                    result.add(panel);
                }
            }
        }

        rearrangePanels(result);
     
    }
 

       
  

    
    //mouse listener functions
	@Override
	public void mouseClicked(MouseEvent e) {
		this.modfav=true;
		if(e.getSource()==this.btnFav) {
			
				this.contentPanel.removeAll();
				
			
			this.contentPanel.revalidate(); 
			this.contentPanel.repaint();    
			
		
			int i=0;
			panelMode();
			 rearrangePanels(this.panelList);  
			
		
	}
	//contact button action	
		else if(e.getSource()==this.btnctn) {
			showContact();
		
	}
		else if(e.getSource()==this.btntel) {
			 this.searching=false;
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		}
		
	

//	
	@Override
	public void keyReleased(KeyEvent e) {
		Object src=e.getSource();
	
		
		
	    if (src == this.searchText) {
	        String str = removeLastIfSpecial(this.searchText.getText());
	        this.searchText.setText(str);
	        
	        findByInput(str);
	    } 
		
	}
}


