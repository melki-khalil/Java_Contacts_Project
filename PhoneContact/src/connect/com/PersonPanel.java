package connect.com;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class PersonPanel extends JPanel implements MouseListener{
	
	JLabel image = new JLabel();
	PersonPanel(){
		
		
		ImageIcon orignalIcon= new ImageIcon("person.png");
		Image bp= orignalIcon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
		this.image.setIcon( new ImageIcon (bp));
		this.image.addMouseListener(this);
		this.image.setHorizontalTextPosition(JLabel.CENTER);
		this.image.setForeground(Color.gray);
		this.image.setFont(new Font("Arial", Font.PLAIN, 40));
		this.image.setBounds(5, 5, 90, 90);
		this.image.setOpaque(true);
		this.setLayout(null);
		this.add(image);
		Border border = BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(0x706A69));

		this.setBorder(border);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==this.image) {
			JFileChooser fc= new JFileChooser();
			fc.setAcceptAllFileFilterUsed(false);
			fc.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "jpeg", "png"));
			int option=fc.showOpenDialog(null);
			if(option==JFileChooser.APPROVE_OPTION) {
				File fl=fc.getSelectedFile();
				String sfile=fl.getAbsolutePath();
				ImageIcon orignalIcon= new ImageIcon(sfile);
				Image pic= orignalIcon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
				this.image.setIcon( new ImageIcon (pic));
				
			}
			
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
		this.image.setText("+");
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.image.setText("");
		// TODO Auto-generated method stub
		
	}

}
