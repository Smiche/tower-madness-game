package alx.tower;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Monster {
	double health = 100;
	int speed = 1;
	public static int wave = 0;
	public static int monstersCount = 0;
	int x,y;
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
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public Image getImage(){
		return monster;
	}
}
