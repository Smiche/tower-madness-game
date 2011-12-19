package edu.tower.def;

import javax.swing.JFrame;

public class Main {
	public static Tower towers[];
	public static Monster monsters;
	public static void main(String[] args) {
		monsters = new Monster(1);
		JFrame frame = new JFrame("TD Madness");
		frame.add(new Board());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200,800);
		frame.setVisible(true);
		monsters.walkMonster();
	}
	void buyTower(int type){
		towers[Tower.towersCount] = new Tower(type);
		Tower.towersCount++;
	}
	public static void sleep(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
		}
	}

}
