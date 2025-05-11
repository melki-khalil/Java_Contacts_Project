package connect.com;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import API.PhoneNumber;
import API.RecentCall;

public class FunctionsClass {
    private BasedFrame parent;
    private PersonPanel panel;
    private PhoneNumber contact;
    // Constructors
    public FunctionsClass(BasedFrame parent) { this.parent = parent; }
    public FunctionsClass(PersonPanel panel) { this.panel = panel; }

    // Display login page
    public void logging() {
        parent.getContentPane().removeAll();
        SignIn log = new SignIn(parent);
        parent.add(log);
        parent.revalidate();
        parent.repaint();
    }

    // Search function to filter contacts based on input
    public void findByInput(String search) {
        List<PersonPanel> result = new ArrayList<>();
        boolean match;

        parent.contentPanel.removeAll();

        if (search.isEmpty()) {
            result = parent.panelList;
        } else {
            for (PersonPanel panel : parent.panelList) {
                match = panel.name.getText().toLowerCase().startsWith(search.trim().toLowerCase()) ||
                        panel.number.getText().startsWith(search);
                if (match) {
                    result.add(panel);
                }
            }
        }

        rearrangePanels(result);
    }

    public void loadContactsToPanels(List<PhoneNumber> contacts) {
        parent.panelList.clear();  // Clear existing panels

        for (PhoneNumber contact : contacts) {
            PersonPanel panel = new PersonPanel(contact, parent);
            panel.image.setBackground(new Color((int) (Math.random() * 0x1000000)));
            parent.panelList.add(panel);
            
        }

        rearrangePanels(parent.panelList); // Refresh the GUI layout
    }
    
    public void loadRecesntCallsToPanels(List<RecentCall> recents) {
        parent.recentList.clear();  // Clear existing panels

        for (RecentCall contact : recents) {
            PersonPanel panel = new PersonPanel(contact, parent);
            panel.image.setBackground(new Color((int) (Math.random() * 0x1000000)));
            parent.recentList.add(panel);
            
        }

        rearrangePanels(parent.recentList); // Refresh the GUI layout
    }


    // Rearrange the panels in the content area based on the list of panels
    public void rearrangePanels(List<PersonPanel> panels) {
        int y = 0; // Y-axis for positioning panels

        // Check if we are in the favorite section
        if (parent.modfav) {
            for (PersonPanel panel : panels) {
                int height = 100 + panel.k;
                panel.options.remove(panel.call);

                if (panel.isFavorite) {
                    panel.setBounds(0, y, 500, height);
                    parent.contentPanel.add(panel);
                    y += height; // Update the Y position for next panel
                }
            }
        } else {
            for (PersonPanel panel : panels) {
                panel.options.remove(panel.call);
                int height = 100 + panel.k;
                panel.setBounds(0, y, 500, height);
                parent.contentPanel.add(panel);
                y += height; // Update the Y position for next panel
            }
        }

        // Resize the content panel based on the number of panels
        if (y > 600) {
            parent.contentPanel.setPreferredSize(new Dimension(500, y));
        } else {
            parent.contentPanel.setPreferredSize(new Dimension(500, 600));
        }

        parent.contentPanel.revalidate();
        parent.contentPanel.repaint();
    }

    // Remove a contact from the database (panel list)
    public void removeContact(PersonPanel contact) {
        parent.contentPanel.removeAll();
        parent.panelList.remove(contact);
        rearrangePanels(parent.panelList); // Refresh the contact panels
    }

    // Display main contact page
    public void ShowContent() {
        parent.searching = true;
        parent.getContentPane().removeAll();
        parent.pane.removeAll(); // Clear the layered pane
        parent.scroll.setBounds(0, 60, 500, 540);
        parent.pane.setBounds(0, 0, 500, 600);
        parent.searchText.setText("");
        parent.pane.add(parent.btntel, Integer.valueOf(2));
        parent.pane.add(parent.scroll, Integer.valueOf(1));
        parent.pane.add(parent.searchText, Integer.valueOf(0));
        parent.setLayout(null);
        parent.add(parent.pane);
        parent.add(parent.buttonstPanel);
        
        loadContactsToPanels(parent.connection.getContactsByIdA(parent.account.getIdA()));
        parent.fun.findByInput("");
        parent.revalidate();
        parent.repaint();
    }

    // Edit contact information
    public void editContact(PersonPanel panel) {
        parent.getContentPane().removeAll();
        InformationPanel edit = new InformationPanel(panel.contact, this.parent);
        parent.setLayout(null);
        parent.add(edit);
        parent.revalidate();
        parent.repaint();
    }

    // Show number pad for dialing
    public void ShowNumberPanel( KeyPadPanel number ) {
        parent.getContentPane().removeAll();
        
        
        parent.pane.removeAll();
        parent.scroll.setBounds(0, 0, 500, 200);
        parent.pane.setBounds(0, 0, 500, 200);
        parent.pane.setLayout(null);
        parent.pane.add(parent.scroll, Integer.valueOf(0));

        parent.add(parent.pane);
        parent.add(number);
        
        parent.revalidate();
        parent.repaint();

        // Back button action to return to main content
        number.btnBack.addActionListener(e -> ShowContent());
    }

    // Show the calling screen when making a call
    public void showCallingPanel(PhoneNumber contact) {
        parent.getContentPane().removeAll();
        CallingPanel callp = new CallingPanel(contact);
        callp.setBounds(0, 0, 500, 700);
        parent.setLayout(null);
        parent.add(callp);
        parent.revalidate();
        parent.repaint();

        // Back button action to return to main content
        callp.btnBack.addActionListener(e -> ShowContent());
    }

    // Convert an image to a byte array for storage
    public byte[] imageToByteArray(Image img) throws IOException {
        if (img == null) return null;

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

    // Convert a byte array to an image
    public Image setImageFromBytes(byte[] imageData) {
        if (imageData != null) {
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                BufferedImage bufferedImage = ImageIO.read(bis);
                if (bufferedImage != null) {
                    return getHighQualityScaledImage(bufferedImage, 90, 90);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Set default image if no image data is available
        ImageIcon defaultImage = new ImageIcon("person.png");
        return getHighQualityScaledImage(defaultImage.getImage(), 90, 90);
    }

    // High quality image resizing
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

    // Create a panel for recent calls with date and time
    public PersonPanel timeOfCall(PersonPanel panel) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        PersonPanel recent = new PersonPanel(parent.contact,panel.parent);
        recent.name.setText(panel.name.getText());
        recent.number.setText(panel.number.getText());
        recent.image.setIcon(panel.image.getIcon());
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
}
