package connect.com;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;

public class PersonPanel extends JPanel implements MouseListener {
	FunctionsClass fun= new FunctionsClass(this);
	
    int k = 0;
    JLabel image = new JLabel();
    JLabel name = new JLabel("");
    JLabel number = new JLabel("123654789");
    JLabel btnfav = new JLabel();
    JLabel btncall = new JLabel();
    JLabel btnmsg = new JLabel();
    JLabel timeLabel = new JLabel();
    JLabel dateLabel = new JLabel();
    Border sqrborder = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x706A69));
    boolean isFavorite = false;
    JPopupMenu options;
    JLabel btnOptions = new JLabel();
    JMenuItem delete = new JMenuItem("delete");
    JMenuItem edit = new JMenuItem("edit");
    JMenuItem call = new JMenuItem("call");
    BasedFrame parent;
    boolean lightMood =true;
    Color themeColor=  Color.white ;

    PersonPanel(BasedFrame parent) {
        this.parent = parent;
        this.addMouseListener(this);
        
        ImageIcon orignalIcon = new ImageIcon("person.png");
        ImageIcon callorignalIcon = new ImageIcon("telephone.png");
        ImageIcon messageorignalIcon = new ImageIcon("message.png");
        ImageIcon favorignalIcon = new ImageIcon("Star.png");

        Image Favimg = favorignalIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        Image call = callorignalIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image message = messageorignalIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        Image bp = orignalIcon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);

        // options bar
     

        // Create pop up menu
        this.options = new JPopupMenu(); 
        this.delete.addActionListener(e -> {
            parent.fun.removeContact(this);
        });
        this.edit.addActionListener(e -> {
            parent.fun.editContact(this);
        });
        this.call.addActionListener(e -> {
        	PersonPanel recent= fun.timeOfCall(this);
        	this.parent.recentList.add(recent);
        	
            parent.fun.showCallingPanel(this);
        });
        this.options.add(this.edit);
        this.options.add(this.delete);

        // Dots icon that shows the pop up menu
        ImageIcon dotsIcon = new ImageIcon("dots.png");
        Image scaledDots = dotsIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        btnOptions.setIcon(new ImageIcon(scaledDots));
        btnOptions.setBounds(440, 25, 50, 40);
        btnOptions.setOpaque(true);
        btnOptions.setBackground(themeColor);
        btnOptions.addMouseListener(this);
        
       //
        this.setBackground(this.themeColor);
        this.setOpaque(true);
        this.image.setIcon(new ImageIcon(bp));
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
        
        
        this.timeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        this.timeLabel.setBounds(300, 50, 200, 40);
        this.dateLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        this.dateLabel.setBounds(300, 10, 200, 40);
        // add compounds
        this.add(this.btnmsg);
        this.add(this.btncall);
        this.add(image);
        this.add(this.name);
        this.add(this.number);
        this.add(this.btnfav);
        this.add(btnOptions);

        Border border = BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(0x706A69));
  
        this.setBorder(border);
    }

 
  
 

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == this.image) {
            JFileChooser fc = new JFileChooser("C:\\Users\\midor\\OneDrive\\Pictures\\fake people");
            fc.setAcceptAllFileFilterUsed(false);
            fc.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "jpeg", "png"));
            int option = fc.showOpenDialog(null);
            if (option == JFileChooser.APPROVE_OPTION) {
                File fl = fc.getSelectedFile();
                String sfile = fl.getAbsolutePath();
                ImageIcon orignalIcon = new ImageIcon(sfile);
                Image pic = fun.getHighQualityScaledImage(orignalIcon.getImage(), 90, 90);
                this.image.setIcon(new ImageIcon(pic));
            }
        }
        else if (e.getSource() == this.btnfav) {
            if (!this.isFavorite) {
                this.btnfav.setBackground(Color.yellow);
                this.isFavorite = true;
            } else {
                this.btnfav.setBackground(themeColor);
                this.isFavorite = false;
            }
            this.parent.contentPanel.removeAll();
            this.parent.fun.rearrangePanels(this.parent.panelList);
        } 
        else if (e.getSource() == this.btncall) {
        	
        	PersonPanel recent= fun.timeOfCall(this);
        	this.parent.recentList.add(recent);
        	
            parent.fun.showCallingPanel(this);
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
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == this.image) {
            this.image.setText("+");
        } else if (e.getSource() == this.btncall) {
            this.btncall.setBorder(this.sqrborder);
        } else if (e.getSource() == this.btnmsg) {
            this.btnmsg.setBorder(this.sqrborder);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == this.image) {
            this.image.setText("");
        } else if (e.getSource() == this.btncall) {
            this.btncall.setBorder(null);
        }
    }

	
}
