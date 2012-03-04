package com.game.tdef;

import javax.swing.JFrame;

public class Main {
	public static Tower towers[];
	public static Monster[] monsters;
	public static void main(String[] args) {
	//	monsters = new Monster(1);
		monsters = new Monster[20];
		for(int i = 0;i<20;i++){
			monsters[i] = new Monster(i);
		}
		JFrame frame = new JFrame("TD Madness");
		frame.add(new Board());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200,800);
		frame.setVisible(true);
		for(int i = 0;;i++){
		int skip = i - (i/20)*20;
		System.out.println(skip);
		monsters[skip].walkMonster();
		}
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
