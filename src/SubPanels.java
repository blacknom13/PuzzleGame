import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;


public class SubPanels extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String what;
	Font x;
	final int α=50;
	SubPanels(String what)
	{
		this.what=what;
		x=new Font("TimesRoman", Font.PLAIN, 20);
	}
	public String getWhat() {
		return what;
	}
	public void setWhat(String what) {
		this.what = what;
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(new Color(0,0,0,0));
		//System.out.println(this.getSize().toString());
		g.fillRect(0, 0, this.getSize().width, this.getSize().height);
		g.setColor(new Color(255,255,255,255));
		g.draw3DRect(0, 0, this.getSize().width, this.getSize().height, true);
		g.setFont(x);
		g.drawString(what, this.getSize().width/2-(int)x.getSize2D(), this.getSize().height/2+10);
	}
	

}
