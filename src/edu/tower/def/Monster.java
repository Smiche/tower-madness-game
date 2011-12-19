package edu.tower.def;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Monster {
	double health = 100;
	int speed = 1;
	public static int wave = 0;
	public static int monstersCount = 0;
	double x,y;
	Image monster;
	/**
	 * Constructor for Monster.
	 * 2 Types 
	 * 1Fast 2Healthy
	 * @param race
	 */
	public Monster(int race){
		ImageIcon i = new ImageIcon("C:/monster.jpg");
		monster = i.getImage();
		if(race == 1){
			health = 120+((health/10)*(wave));
		}
		if(race == 2){
			health = 80+((health/10)*(wave-1));
			speed =2;
		}
		monstersCount++;	
	}
	public boolean waveFinished(){
		for(int i = 0;i<20;i++){
			if(Main.monsters != null){
				return false;
			}
		}
		return true;	
	}
	public void killedMonster(int monId){
		if(Main.monsters.health <= 0){
			Main.monsters = null;
			Player.money = Player.money + wave + 1;
		}
	}
	public void walkMonster(){
		for(int i = 0;;i++){
			if(x < 200){
				x+=0.5;
				y = 300;
				Main.sleep(20);
			}
			
		}
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public Image getImage(){
		return monster;
	}
}
