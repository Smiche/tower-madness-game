package edu.tower.def;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;


import javax.swing.*;

public class Board extends JPanel{
	Image img1;
	public Board(){
		setFocusable(true);
		ImageIcon i = new ImageIcon("C:/test.jpg");
		img1 = i.getImage();
	}
	
	

	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img1, 0,0,null);
		g2d.drawImage(Main.monsters.getImage(), Main.monsters.getX(), Main.monsters.getY(), null);
		repaint();
	}
}
