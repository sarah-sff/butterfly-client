package swingView.define;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ButterPanel extends JPanel {
	
	Image image=new ImageIcon(this.getClass().getResource("/images/back.jpg")).getImage();  

	// �̶�����ͼƬ���������JPanel������ͼƬ������������  
    protected void paintComponent(Graphics g) {  
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
    }  
}
