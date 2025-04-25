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
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int id_u;
	FunctionsClass fun= new FunctionsClass(this);
	
	
	boolean modfav=false;
	 boolean searching=true;
    JPanel contentPanel = new JPanel();
    JPanel buttonstPanel = new JPanel();
    JScrollPane scroll = new JScrollPane(contentPanel);	
    JLabel btnFav= new JLabel("favorite");
    JLabel btnctn= new JLabel("Contact");
    JLabel btnrec= new JLabel("recent");
    JLabel btntel= new JLabel();
    JLayeredPane pane= new JLayeredPane();
    JPanel searchPanel=new JPanel();
    JTextField searchText= new JTextField();
    String input="";
    ArrayList<Object[]> panelData = new ArrayList<>();
    
    Color SectionColor=new Color(143, 139, 129);
      
    List<PersonPanel> panelList = new ArrayList<>();
    List<PersonPanel> recentList = new ArrayList<>();
    public BasedFrame() {
    	  this.setTitle("app");
          this.setSize(500, 700);
          this.setResizable(false);
          this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          this.getContentPane().setBackground(new Color(200, 200, 200));
          this.setLocation(500,10);
     
    	SignIn log=new SignIn(this);

    	this.add(log);
    	this.setVisible(true);
    }
    public void  Defaultpanel(){
    	// application frame properties
      
        
    	
        this.contentPanel.setLayout(null);
        this.contentPanel.setPreferredSize(new Dimension(500, 600));
    	this.pane.setLayout(null);
    	this.pane.setBounds(0, 0, 500, 600);
    	 this.btntel.setBounds(410,525,65,65);
    	 
    	// configuration of the buttons bar 
    	this.buttonstPanel.setLayout(null);
     	this.buttonstPanel.setBounds(0, 600, 500, 100);
	    	//recent section
	    	 this.btnrec.setBounds(5,0,165,65);
	         this.btnrec.setBackground(Color.WHITE);
	         this.btnrec.setOpaque(true);
	         this.btnrec.setFont(new Font("Arial", Font.PLAIN, 25));
	         this.btnrec.setVerticalTextPosition(JLabel.CENTER);
	         this.btnrec.setVerticalTextPosition(JLabel.BOTTOM);
	         this.btnrec.addMouseListener(this);
	          //contact section
	         this.btnctn.setBounds(165,0,165,65);
	         this.btnctn.setBackground(this.SectionColor);
	         this.btnctn.setOpaque(true);
	         this.btnctn.setFont(new Font("Arial", Font.PLAIN, 25));
	         this.btnctn.setVerticalTextPosition(JLabel.CENTER);
	         this.btnctn.setVerticalTextPosition(JLabel.BOTTOM);
	         this.btnctn.addMouseListener(this);
	         //favorite section
	         this.btnFav.setBounds(330,0,165,65);
	         this.btnFav.setBackground(Color.WHITE);
	         this.btnFav.setOpaque(true);
	         this.btnFav.setFont(new Font("Arial", Font.PLAIN, 25));
	         this.btnFav.setVerticalTextPosition(JLabel.CENTER);
	         this.btnFav.setVerticalTextPosition(JLabel.BOTTOM);
	         this.btnFav.addMouseListener(this);
	         // adding the buttons to the bar
	         this.buttonstPanel.add(this.btnctn);
	         this.buttonstPanel.add(this.btnFav);
	         this.buttonstPanel.add(this.btnrec);

        //keypad
        ImageIcon telorignalIcon = new ImageIcon ("keypad.png");
        Image telimg= telorignalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        this.btntel.setIcon(new ImageIcon(telimg));
        this.btntel.setBackground(Color.cyan);
        this.btntel.setOpaque(true);
        this.btntel.setHorizontalAlignment(JLabel.CENTER);
        this.btntel.setVerticalAlignment(JLabel.CENTER);
        this.btntel.addMouseListener(this);
        
        
   
       
        Border border = BorderFactory.createMatteBorder(0, 1, 0, 1, new Color(0x706A69));
        this.btnFav.setBorder(border);
        this.setLayout(null);

        ImageIcon Icon = new ImageIcon("Icon.png"); 
        this.setIconImage(Icon.getImage());

        this.searchText.setBounds(5, 5, 475, 50);
        this.searchText.setFont(new Font("Arial", Font.BOLD, 24));
        this.searchText.setEditable(false);
        this.searchText.addKeyListener(this);
       
        this.setBackground(Color.white);
        
        this.scroll.setBounds(0, 60, 500, 540); 
        this.scroll.getVerticalScrollBar().setUnitIncrement(16); 
        this.scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        //adding components
        this.pane.add(this.btntel, Integer.valueOf(1));
        this.pane.add(this.scroll, Integer.valueOf(0));
        this.pane.add(this.searchText, Integer.valueOf(0));
   

        
        
        this.setVisible(true);
        
        fun.extractContacts();
        fun.ShowContent();
        
       
        

    }
    
   
    
   
    //mouse listener functions
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getSource()==this.btnFav) {
			this.modfav=true;
			this.contentPanel.removeAll();
				
			
			this.contentPanel.revalidate(); 
			this.contentPanel.repaint();    
			
			this.btnFav.setBackground(SectionColor);
			this.btnrec.setBackground(Color.WHITE);
			this.btnctn.setBackground(Color.WHITE);
			
			
			 fun.rearrangePanels(this.panelList);  
			
		
	}
		else if(e.getSource()==this.btnrec) {
			this.modfav=false;
			this.contentPanel.removeAll();
			this.btnrec.setBackground(SectionColor);
			this.btnFav.setBackground(Color.WHITE);
			this.btnctn.setBackground(Color.WHITE);
			
			
			
			 fun.rearrangePanels(this.recentList);  
	
		}
	//contact button action	
		else if(e.getSource()==this.btnctn) {
			this.modfav=false;
			this.btnctn.setBackground(SectionColor);
			this.btnrec.setBackground(Color.WHITE);
			this.btnFav.setBackground(Color.WHITE);
			 fun.rearrangePanels(this.panelList);
			
	}
		else if(e.getSource()==this.btntel) {
			 this.searching=false;
			fun.ShowNumberPanel();
	        
	        
			    
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
		Object src=e.getSource();
	
		
		
	    if (src == this.searchText) {
	    	char ch = e.getKeyChar();
	    	String str=this.searchText.getText();
	    	if (Character.isLetterOrDigit(ch)) {
	    		this.searchText.setText(str+ch);
	    	}
	    
	    }
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
Object src=e.getSource();
	
		
		
	    if (src == this.searchText) {
	    	
	    	String str=this.searchText.getText();
	    	
	    	if ((e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE)&&!str.isEmpty()) {
	    		str=str.substring(0,str.length()-1);
	    		this.searchText.setText(str);
	    	}
	        this.searchText.setText(str);
	        this.input=str;
	        fun.findByInput(this.input);
	    }
		}
		
	

//	
	@Override
	public void keyReleased(KeyEvent e) {
Object src=e.getSource();
	
		
		
	    if (src == this.searchText) {
	    
	        this.input=this.searchText.getText();
	        fun.findByInput(this.input);
	    }
		
	}
}


