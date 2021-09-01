import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;



import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PaneledGame extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	float fact=0;
	
	BlockGrid grid;
	BufferedImage image,bomb;
	Image pImage,bombIm;
	final int α=255;
//	long startTime=0;
//	boolean first;
	
	public float getFact() {
		return fact;
	}

	public void setFact(float fact) {
		this.fact = fact;
	}

	public PaneledGame(BlockGrid grid) {
		// TODO Auto-generated constructor stub
		this.grid=grid;
		try {
			image=ImageIO.read(getClass().getResource("res/squere.png"));
			bomb=ImageIO.read(getClass().getResource("res/bomb.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		//g.clearRect(0, 0, this.getSize().width, this.getSize().height);
		int rectHeight = this.getSize().width / (grid.getSize());
		int rectWidth=this.getSize().height/(grid.getSize());
		pImage=image.getScaledInstance(rectWidth, rectHeight,0);
		bombIm=bomb.getScaledInstance(rectWidth, rectHeight, 0);
		//g.setColor(new Color(0,0,0,255));
		//g.fill3DRect(0, 0, this.getSize().width, this.getSize().height, true);

		for (int i = 0; i < grid.getSize(); i++) {
			for (int j = 0; j < grid.getSize(); j++) {
				if (!grid.getGrid()[i][j].isVisited()) {
//					if (grid.getGrid()[i][j].isBomb()) {
//						g.setColor(Color.BLACK);
//						g.fill3DRect(j * rectHeight, i * rectWidth, rectHeight,
//								rectWidth, true);
//					} else if (grid.getGrid()[i][j].isTransparent()) {
//						g.setColor(Color.WHITE);
//						g.fill3DRect(j * rectHeight, i * rectWidth, rectHeight,
//								rectWidth, true);}
//					 else {
//						if (BlockGrid.grid[i][j].getDropping()>0){
//							
//						g.setColor(BlockGrid.grid[i-(int)BlockGrid.grid[i][j].getDropping()][j].getColor());
//						g.fill3DRect(j * rectHeight, i * rectWidth+(int)(fact*BlockGrid.grid[i][j].getDropping())*rectWidth, rectHeight,
//								rectWidth, true);
//						//g.fillOval(j * rectHeight, i * rectWidth, rectHeight, rectWidth);
//						g.setColor(Color.BLACK);
//						g.draw3DRect(j * rectHeight, i * rectWidth, rectHeight,
//								rectWidth,true);
//						}
					//else{
					if (Math.round(grid.getGrid()[i][j].getDropping()-fact)>0)
							grid.getGrid()[i][j].setDropping(grid.getGrid()[i][j].getDropping()-fact);
					else
						grid.getGrid()[i][j].setDropping(0);
					
					g.setColor(grid.getGrid()[i][j].getColor());
					if (grid.getGrid()[i][j].isBomb())
						g.setColor(new Color(0,0,0,0));
					if (grid.getGrid()[i][j].isTransparent())
						g.setColor(new Color(255,255,255,50));
					g.fill3DRect(j * rectHeight, i * rectWidth -(int)(grid.getGrid()[i][j].getDropping()*rectWidth), rectHeight,
							rectWidth, true);
					if (grid.getGrid()[i][j].isBomb())
						g.drawImage(bombIm, j * rectHeight,  i * rectWidth -(int)(grid.getGrid()[i][j].getDropping()*rectWidth), null);
					else
						g.drawImage(pImage, j * rectHeight,  i * rectWidth -(int)(grid.getGrid()[i][j].getDropping()*rectWidth), null);
					//g.fillOval(j * rectHeight, i * rectWidth, rectHeight, rectWidth);
//					g.setColor(Color.BLACK);
//					g.draw3DRect(j * rectHeight, i * rectWidth -(int)(grid.getGrid()[i][j].getDropping()*rectWidth), rectHeight,
//							rectWidth,true);
						
//				}
//				else{

				//System.out.print(grid.getGrid()[i][j].getDropping() + " ");
				}
				g.setColor(Color.BLACK);
				g.draw3DRect(j * rectHeight, i * rectWidth, rectHeight,
						rectWidth, false);}
			//System.out.println();
		}
		//paint(getGraphics());
	}

	


	
	
}
