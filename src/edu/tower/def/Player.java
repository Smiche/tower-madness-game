package edu.tower.def;

public class Player {
	static int money = 100;
	int wave = 0;
	
	public Player(){
		
	}
	public static void reduceMoney(int cost){
		if(money > cost){
			money = money - cost;
		}
	}
	public static boolean playerReady(){
		return true;
		
	}
}
