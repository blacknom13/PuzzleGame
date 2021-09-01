import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MyFrame extends JFrame implements MouseListener,MouseMotionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel x;
	JLabel ex;
	ImageIcon c;
	BufferedImage im;
	int mousePosX;
	int mousePosY;
	
	public MyFrame()
	{
		super();
		this.setUndecorated(true);
		this.setSize(600,600);
		this.setLayout(null);
		x=new Panels("res/panel.png");
		x.setLayout(null);
		x.addMouseListener(this);
		x.addMouseMotionListener(this);
		//x.setLayout();
	//	System.out.println( x.getLayout().toString());
		x.setPreferredSize(new Dimension(this.getWidth(), 30));
//		System.out.println(x.getSize());
//		x.setMaximumSize(x.getSize());
//		x.setMinimumSize(x.getSize());
		
		x.setBounds(this.getBounds().x, this.getBounds().y, this.getWidth(), 30);
		try {
			im=ImageIO.read(getClass().getResource("res/exit.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c=new ImageIcon(im.getScaledInstance(20, 20, 0));
		ex=new JLabel(c);
		ex.setPreferredSize(new Dimension(20,20));
		ex.addMouseListener(this);
		x.add(ex);
		
		this.add(x);
		x.setBackground(Color.BLACK);
		ex.setBounds(this.getWidth()-30, 5, 20, 20);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(ex))
		{
			System.exit(0);
		}
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(x))
		{
			mousePosX= x.getMousePosition().x;
			mousePosY=x.getMousePosition().y;
			//setLocation(e.getX(),e.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(x))
		{
			this.setLocation(e.getXOnScreen()-mousePosX,e.getYOnScreen()-mousePosY);
		//	System.out.println(e.getX()+"  "+e.getXOnScreen());
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
