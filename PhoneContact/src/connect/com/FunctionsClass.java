package connect.com;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class FunctionsClass {
	BasedFrame parent;
	PersonPanel panel;
	
	FunctionsClass( BasedFrame parent){
	 this.parent=parent;
	}
	FunctionsClass(PersonPanel panel){
		this.panel=panel;
	}
	
	 //functions 
	//showing the log in page
	public void logging() {
		this.parent.getContentPane().removeAll();
		SignIn log=new SignIn(this.parent);
    	this.parent.add(log);
    	this.parent.revalidate();
    	this.parent.repaint();
	}
	
    // the search function
    public void findByInput(String search) {
        List<PersonPanel> result = new ArrayList<>();
        boolean match=false;
        parent.contentPanel.removeAll();
        
        if (search.isEmpty()) {
            result = parent.panelList;
        } else {
        	// Looping throw the contact list to find our match
            for (PersonPanel panel : parent.panelList) {
            	// find contact by name
            	
            	 match = panel.name.getText().toLowerCase().startsWith(search.trim().toLowerCase())|| panel.number.getText().startsWith(search);
            	

                if (match) {

                    result.add(panel);
                	
                }
            }
        }
  
        rearrangePanels(result);
     
    }
    
    // a function that check if the number you are calling throw the panel exist in your contact list or not
    public PersonPanel isAContact(String str){
    
    	PersonPanel contact=new PersonPanel(parent);
    	contact.name.setText("");
    	contact.number.setText(str);
    	for (PersonPanel panel : parent.panelList) {

               boolean match =  panel.number.getText().equals(str);

              if(match) {
            	  contact=panel;
            	  break;
              }
        	}
    	
    
    	
      return contact;
    }
    
    
    
    // a function that rearrange the contact panel according to the function need
    public void rearrangePanels( List<PersonPanel> panels ) {   	
        int y = 0; //Initializing the Y position
        
        // if statement  to check if you are in the in favorite section or not 
        if(parent.modfav) {
        	for (PersonPanel panel :panels) {
        		int height = 100 + panel.k;
                panel.options.remove(panel.call);
                if(panel.isFavorite) {
                	panel.setBounds(0, y, 500, height);
                	parent.contentPanel.add(panel);

                    y += height; // changing the Y position to have rearranges panels 
                }
            }
        }
        else {
        	
        	for (PersonPanel panel :panels) {
        		   panel.options.remove(panel.call);
        		int height = 100 + panel.k;
        		panel.setBounds(0, y, 500, height);
        		parent.contentPanel.add(panel);
        		y += height; // changing the Y position to have rearranges panels 
        	}
        }

        // if statement to resize the control panel according to the number of contacts
        if (y > 600) {
            parent.contentPanel.setPreferredSize(new Dimension(500, y));
        } else {
            parent.contentPanel.setPreferredSize(new Dimension(500, 600));
        }

        parent.contentPanel.revalidate();
        parent.contentPanel.repaint();
    }
    
   
    
    // function that remove a contact from the data base
    public void removeContact(PersonPanel Contact) {
    	
    	
		
		parent.contentPanel.removeAll();
		
    	parent.panelList.remove(Contact); //remove the contact from the panel list
    	
    	//refresh the contact panel
		 rearrangePanels(this.parent.panelList);  
		
		
    	
    }
    
    // function that bring us back to the main frame contacts
    public void ShowContent() {
    	parent.searching=true;
        parent.getContentPane().removeAll();
        parent.pane.removeAll(); // Clear the layered pane just in case
        parent.scroll.setBounds(0, 60, 500, 540);
    	parent.pane.setBounds(0, 0, 500, 600);
    	parent.searchText.setText("");
        parent.pane.add(parent.btntel, Integer.valueOf(2));
        parent.pane.add(parent.scroll, Integer.valueOf(1));
        parent.pane.add(parent.searchText, Integer.valueOf(0));
        parent.setLayout(null);
        parent.add(parent.pane);
        parent.add(parent.buttonstPanel);
        parent.fun.findByInput("");
        
        parent.revalidate();
        parent.repaint();
   }
    

    // function that modify a contact information data base
    public void editContact(PersonPanel panel, boolean isnew) {
    	parent.getContentPane().removeAll();
    	InformationPanel edit= new InformationPanel(panel,parent,isnew);
		
    	parent.setLayout(null);
        parent.add(edit);
        parent.revalidate();
        parent.repaint();
    	
    }
    
    // function that add number palate to the frame 
    public void ShowNumberPanel(){
		parent.getContentPane().removeAll();
		KeyPadPanel number= new KeyPadPanel(parent);			
		
		parent.pane.removeAll();
		parent.scroll.setBounds(0,0,500,200);
        parent.pane.setBounds(0,0,500,200);
        parent.pane.setLayout(null);
        parent.pane.add(parent.scroll, Integer.valueOf(0));
      

    	parent.add(parent.pane);
        parent.add(number);
        
        parent.revalidate();
        parent.repaint();
 ;
        number.btnBack.addActionListener(e -> {
	   
            ShowContent();
            
        });
	}
    public void showCallingPanel(PersonPanel panel) {
    	parent.getContentPane().removeAll();
    	CallingPanel callp= new CallingPanel(panel.name,panel.number);
    	panel.name=panel.name;
    	panel.number=panel.number;
    	callp.setBounds(0, 0, 500, 700);
    	parent.setLayout(null);
        parent.add(callp);
        parent.revalidate();
        parent.repaint();
        
        callp.btnBack.addActionListener(e -> {
        	
          ShowContent();
        });

    }
    public void showContacts(){
    	parent.Defaultpanel();
		parent.contentPanel.removeAll();
		
		int y=0;
		for (PersonPanel panel :parent.panelList) {
    		int height = 100 + panel.k;
    		panel.setBounds(0, y, 500, height);
    		panel.options.add(panel.call);
    		parent.contentPanel.add(panel);
    		y += height;
    	}
		 
		
		parent.contentPanel.revalidate(); 
		parent.contentPanel.repaint();    
		
    }
    
 
  
// storing function 
    
    public void SaveAndClose() {
        List<ContactData> contactList = new ArrayList<>();

        for (PersonPanel panel : parent.panelList) {
            ContactData data = new ContactData();
            data.name = panel.name.getText();
            data.number = panel.number.getText();
            data.favorite = panel.isFavorite;
      
            ImageIcon imageIcon = (ImageIcon) panel.image.getIcon();
            if (imageIcon != null) {
                try {
                    data.imageData = imageToByteArray(imageIcon.getImage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            contactList.add(data);
        }

        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("contacts_data.json")) {
            gson.toJson(contactList, writer);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        parent.dispose(); 
    }

    public void extractContacts() {
        File file = new File("contacts_data.json");
        if (!file.exists()) {
            try {
                boolean created = file.createNewFile(); // Creates an empty file
                if (created) {
                    System.out.println("contacts_data.json created.");
                    // Optionally write an empty JSON array to it
                    FileWriter writer = new FileWriter(file);
                    writer.write("[]");
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Load the contacts into ContactData objects
        List<ContactData> loadedData = loadFromJsonToContacts("contacts_data.json");
        
        for (ContactData contact : loadedData) {
            PersonPanel panel = new PersonPanel(parent);
            panel.name.setText(contact.name);
            panel.number.setText(contact.number);
            panel.isFavorite = contact.favorite;
            panel.btnfav.setBackground(contact.favorite? Color.yellow : Color.white );
            panel.image.setBackground(new Color((int) (Math.random() * 0x1000000)));
            Image image =panel.fun.setImageFromBytes(contact.imageData);
            panel.image.setIcon(new ImageIcon(image));
            parent.panelList.add(panel);
        }
    }

    public List<ContactData> loadFromJsonToContacts(String  filePath) {
        Gson gson = new Gson();

        try (Reader reader = new FileReader(filePath)) {
            java.lang.reflect.Type listType = new TypeToken<List<ContactData>>() {}.getType();
            List<ContactData> contactList = gson.fromJson(reader, listType);
            
          
            return contactList;
         
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            System.out.println("JSON is malformed or not as expected.");
            e.printStackTrace();
        }
        return new ArrayList<>(); // return an empty list if loading fails
    }



// person panel functions
    //convert image to a byte matrix  
    public byte[] imageToByteArray(Image img) throws IOException {
         if (img == null) return null;

         // Wait until image is fully loaded
         ImageIcon checkIcon = new ImageIcon(img);
         int width = checkIcon.getIconWidth();
         int height = checkIcon.getIconHeight();

         if (width <= 0 || height <= 0) {
             throw new IOException("Image not fully loaded or invalid dimensions.");
         }

         BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
         Graphics2D g2d = bufferedImage.createGraphics();
         g2d.drawImage(img, 0, 0, null);
         g2d.dispose();

         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

         boolean success = ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
         if (!success) {
             throw new IOException("ImageIO.write failed: PNG writer not available");
         }

         return byteArrayOutputStream.toByteArray();
     }

     // convert image blob that we stored into the data base into an image
     public Image setImageFromBytes(byte[] imageData) {
         if (imageData != null) {
             try {
                 ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                 BufferedImage bufferedImage = ImageIO.read(bis);
                 if (bufferedImage != null) {
                     Image scaledImage = getHighQualityScaledImage(bufferedImage, 90, 90);
                     return scaledImage;
                    
                 }
             } catch (IOException e) {
                 e.printStackTrace();
             }
         } else {
             // Set default image if no image data is available
             ImageIcon defaultImage = new ImageIcon("person.png");
             Image image= getHighQualityScaledImage(defaultImage.getImage(), 90, 90);
             return image;
         }
		return null;
     }

     // rendering the image
     public Image getHighQualityScaledImage(Image srcImg, int w, int h) {
         BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
         Graphics2D g2 = resizedImg.createGraphics();
         
         g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
         g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
         g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
         g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
         g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
         g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

         g2.drawImage(srcImg, 0, 0, w, h, null);
         g2.dispose();

         return resizedImg;
     }
     
     
     // create a panel for recent calls
     public PersonPanel timeOfCall(PersonPanel panel) {
     	LocalDate date = LocalDate.now();
     	LocalTime time = LocalTime.now();
     	PersonPanel recent=new PersonPanel(panel.parent);
     	recent.name.setText(panel.name.getText());
     	recent.number.setText(panel.number.getText());
     	recent.image.setIcon(panel.image.getIcon());;
     	recent.image.setBackground(new Color((int) (Math.random() * 0x1000000)));
     	recent.remove(recent.btnfav);
     	recent.remove(recent.btnOptions);
     	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
     	recent.timeLabel.setText(time.format(formatter));
     	recent.dateLabel.setText(date.toString());
     	recent.add(recent.timeLabel);
     	recent.add(recent.dateLabel);
 		return recent;
     	
     }
  public void theme(Color themeColor) {
 	 if(panel.lightMood) {
 		 panel.themeColor= themeColor;
 	 }
 	 else {
 		 panel.themeColor= Color.black;
 	 }
  }
    
  




  

  


}
