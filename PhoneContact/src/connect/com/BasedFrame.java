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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class BasedFrame extends JFrame implements MouseListener {
	boolean modfav=false;
    JPanel contentPanel = new JPanel();
    JScrollPane scroll = new JScrollPane(contentPanel);	
    JLabel btnFav= new JLabel("favorite");
    JLabel btnctn= new JLabel("Contact");
    List<PersonPanel> panelList = new ArrayList<>();
    public BasedFrame() {
        this.contentPanel.setLayout(null);
        this.contentPanel.setPreferredSize(new Dimension(500, 600));
        this.btnFav.setBounds(330,600,165,65);
        this.btnctn.setBounds(165,600,165,65);
        
        
        this.setTitle("app");
        this.setSize(500, 700);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(200, 200, 200));
        
         //contact section
        this.btnctn.setBackground(Color.gray);
        this.btnctn.setOpaque(true);
        this.btnctn.setFont(new Font("Arial", Font.PLAIN, 25));
        this.btnctn.setVerticalTextPosition(JLabel.CENTER);
        this.btnctn.setVerticalTextPosition(JLabel.BOTTOM);
        this.btnctn.addMouseListener(this);
        //favorite section
        ImageIcon favorignalIcon = new ImageIcon ("Star.png");
        Image Favimg= favorignalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        this.btnFav.setIcon(new ImageIcon(Favimg));
   
        this.btnFav.setBackground(Color.yellow);
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
        this.add(this.scroll);
        this.add(this.btnctn);
        this.add(btnFav);
        for (int i = 0; i < 10; i++) {
            PersonPanel panel = new PersonPanel(this); // pass this!
            panel.name.setText("name" + (i + 1));
            panel.image.setBackground(new Color((int) (Math.random() * 0x1000000)));
            this.contentPanel.add(panel);
            this.panelList.add(panel);
        }
        rearrangePanels(); 

        
        

    }
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

    public void showCallingPanel() {
    	this.getContentPane().removeAll();
    	CallingPanel callp= new CallingPanel();
    	callp.setBounds(0, 0, 500, 700);
    	this.setLayout(null);
        this.add(callp);
        this.revalidate();
        this.repaint();
        
        callp.btnBack.addActionListener(e -> {
            this.getContentPane().removeAll();
            this.setLayout(null);
            this.add(scroll);
            this.add(btnctn);
            this.add(btnFav);
            rearrangePanels();
            this.revalidate();
            this.repaint();
        });

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
			for(PersonPanel panel: this.panelList) {
				if(panel.isFavorite) {
					panel.setBounds(0, 100*i, 500, 100);
					this.contentPanel.add(panel);
					i++;
				}
			}
			rearrangePanels(); 
			
		
	}
	//contact button action	
		if(e.getSource()==this.btnctn) {
			this.modfav=false;
			for(PersonPanel panel: this.panelList) {
				if(panel.isFavorite) {
					this.contentPanel.remove(panel);
				}
			}
			int i=0;
			for(PersonPanel panel: this.panelList) {
				this.contentPanel.remove(panel);
				panel.setBounds(0, 100*i, 500, 100);
				this.contentPanel.add(panel);
				i++;
			}
			rearrangePanels(); 
			
			this.contentPanel.revalidate(); 
			this.contentPanel.repaint();    
			
		
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
