package alx.tower;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Main {
	public static Tower towers[];
	public static Monster monsters[];
	/**
	 * number of towers bought
	 * ,used to store tower objects
	 */
	static int tIndex = 0;
	public static void main(String[] args) {
		
	}
	void buyTower(int type){
		towers[tIndex] = new Tower(type);
		tIndex++;
	}
	
	
	  public void onRepaint(Graphics g1) {	    	
	        Graphics2D g = (Graphics2D) g1;
	        g.drawString("" , 142, 419);
	        g.drawString("Hide", 436,330);
	    }
}
