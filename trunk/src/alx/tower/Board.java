package alx.tower;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;


import javax.swing.*;

public class Board extends JPanel{
	Monster m;
	Image img1;
	public Board(){
		m = new Monster(1);
		setFocusable(true);
		ImageIcon i = new ImageIcon("C:/test.jpg");
		img1 = i.getImage();
		repaint();
	}

	public void Paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img1, 0,0,null);
		g2d.drawImage(m.getImage(), m.getX(), m.getY(), null);
		repaint();
	}
}
