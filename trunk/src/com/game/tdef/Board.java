package com.game.tdef;

import java.awt.*;
import javax.swing.*;

public class Board extends JPanel{
	Image img1;
		
	public Board(){
		java.net.URL myurl = this.getClass().getResource("/Images/test.jpg");
		Toolkit tk = this.getToolkit();
		img1 = tk.getImage(myurl);
		setFocusable(true);

	}
	
	

	 public void paint(Graphics g){
         super.paint(g);
         Graphics2D g2d = (Graphics2D) g;
         g2d.drawImage(img1, 0,0,null);
         Monster[] monsters = Main.monsters;
         for(Monster m : monsters){
        	 if(m != null)
			m.paint(g2d);
         }
         Main.sleep(100);
         repaint();
 }
}
