package com.game.tdef;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Monster {
	double health = 100;
	int speed = 1;
	public static int wave = 0;
	public static int monstersCount = 0;
	double x,y;
	static Image monster;
	/**
	 * Constructor for Monster.
	 * 2 Types 
	 * 1Fast 2Healthy
	 * @param race
	 */
	public Monster(int race){
		java.net.URL myurl = this.getClass().getResource("/Images/monster.png");
		Toolkit tk = Toolkit.getDefaultToolkit();
		monster = tk.getImage(myurl);
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
			if(Main.monsters[i] != null){
				return false;
			}
		}
		return true;	
	}
	public void killedMonster(int monId){
		if(Main.monsters[monId].health <= 0){
			Main.monsters[monId] = null;
			Player.money = Player.money + wave + 1;
		}
	}
	public void walkMonster(){
			if(x < 200){
				x+=0.5;
				y = 300;
				Main.sleep(20);
			
		}
	}
	public void paint(Graphics2D gf){
		gf.drawImage(getImage(),(int)getX(),(int)getY(),null);
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
