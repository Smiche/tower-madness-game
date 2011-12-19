package edu.tower.def;

import javax.swing.JFrame;

public class Main {
	public static Tower towers[];
	public static Monster monsters[];
	public static void main(String[] args) {
		JFrame frame = new JFrame("TD Madness");
		frame.add(new Board());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200,800);
		frame.setVisible(true);
	}
	void buyTower(int type){
		towers[Tower.towersCount] = new Tower(type);
		Tower.towersCount++;
	}

}
