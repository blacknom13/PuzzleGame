import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Background extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage image;
	Image pImage;
	
	public Background() {
		// TODO Auto-generated constructor stub
		try {
			image=ImageIO.read(this.getClass().getResource("res/wet_grass.jpg"));
			//image=ImageIO.read(new File("res/wet_grass.jpg"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		pImage=image.getScaledInstance(this.getSize().width, this.getSize().height,0);
		//pImage.
		g.drawImage(pImage, 0, 0,null);
		//System.out.println("This one called");
	}
}
