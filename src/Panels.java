import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Panels extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage im;
	Image image;
	
	Panels(String str)
	{
		try {
			im=ImageIO.read(getClass().getResource(str));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		image=im.getScaledInstance(this.getWidth(), this.getHeight(), 0);
		g.drawImage(image, 0, 0, null);
	}
}
