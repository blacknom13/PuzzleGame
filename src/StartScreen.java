import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;


public class StartScreen extends JPanel implements MouseListener{

	Background background;
	JButton start,about;
	ButtonGroup group;
	JRadioButton easy,normal,hard;
	boolean running;
	
	StartScreen()
	{
		super(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		JPanel temp=new JPanel(new GridBagLayout());
		temp.setBackground(new Color(255,255,255,0));
		JPanel checks=new JPanel(new GridLayout(3,1));
		//checks.setBackground(new Color(255,255,255,0));
		JPanel someTemp;
		running=true;
		//JPanel motherPanel=new JPanel(new GridBagLayout());
		
		//this.add(background);

		background=new Background();
		background.setLayout(new GridBagLayout());
		start=new JButton("New Game");
		//start.setBackground(new Color(255,255,255,0));
		start.addMouseListener(this);
		//start.setOpaque(false);
		//start.setContentAreaFilled(false);
		group=new ButtonGroup();
		
		//group.setLayout(new GridLayout(3,1));
		//group.setOpaque(false);
		easy=new JRadioButton("Easy", true);
		//easy.setOpaque(false);
		//easy.setBackground(new Color(255,255,255,0));
		group.add(easy);
	//	easy.addMouseListener(this);
		easy.setEnabled(false);
		normal=new JRadioButton("Normal", false);
		//normal.setOpaque(false);
		group.add(normal);
	//	normal.addMouseListener(this);
		normal.setEnabled(false);
		hard= new JRadioButton("Hard", false);
		//hard.setOpaque(false);
	//	hard.addMouseListener(this);
		hard.setEnabled(false);
		group.add(hard);
		about=new JButton("About ME");
		//about.setOpaque(false);
		//about.setContentAreaFilled(false);
		//about.setBackground(new Color(255,255,255,0));
		about.addMouseListener(this);
		
		
		//motherPanel.add(background);
	
		gbc.fill=GridBagConstraints.BOTH;
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.weightx=8;
		gbc.weighty=1;
		someTemp=new JPanel();
		someTemp.setBackground(new Color(255,255,255,0));
		background.add(someTemp,gbc);
		
		gbc.gridx=1;
		gbc.weightx=1;
		background.add(temp,gbc);
		
		gbc.gridx=0;
		gbc.weighty=4;
		someTemp=new JPanel();
		someTemp.setBackground(new Color(255,255,255,0));
		temp.add(someTemp,gbc);
		
		gbc.gridy=1;
		gbc.weighty=1;
		temp.add(start,gbc);
		
		gbc.gridy=2;
		gbc.weighty=2;
		temp.add(checks,gbc);
		
		gbc.gridy=3;
		gbc.weighty=1;
		temp.add(about,gbc);
		
		gbc.gridy=4;
		gbc.weighty=6;
		someTemp=new JPanel();
		someTemp.setBackground(new Color(255,255,255,0));
		temp.add(someTemp,gbc);
		
		checks.add(easy);
		checks.add(normal);
		checks.add(hard);

		gbc.fill=GridBagConstraints.BOTH;
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.weightx=1;
		gbc.weighty=1;
		this.add(background,gbc);
	}

	public int getCheckedRadio(){
		int x=-1;
		if (easy.isSelected()){
			x=1;
		}if (normal.isSelected()){
			x=2;
		}if (hard.isSelected()){
			x=3;
		}
		return x;
	}

	public boolean isRunning(){
		return running;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(e.getSource().toString());
		if (e.getSource().equals(start))
		{
			if (start.getText().equals("New Game")) {
				easy.setEnabled(true);
				normal.setEnabled(true);
				hard.setEnabled(true);
				start.setText("Start");
			}else {
				running=false;
			}
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
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
