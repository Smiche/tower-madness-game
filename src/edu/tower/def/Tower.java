package edu.tower.def;

public class Tower {
	double damage = 100;
	double range = 10;
	int speed = 1;
	int level = 1;
	int price = 10;
	public static int towersCount = 0;
	
	/**
	 * Constructor for the tower objects which have 3 types.
	 * 1 close range 2 speed 3 long range
	 * @param type
	 */
	public Tower(int type){
		if(type == 1){
			price = 5;
			damage = 160;
			range = 5;
			speed = 2;
		}
		if(type ==2){
			price = 7;
			damage = 80;
			speed = 3;
			range = 8;
		}
		if(type ==3){
			price = 10;
			range = 15;
			damage = 100;
		}
	}
	void upgrade(){
		damage = damage + (damage*0.2);
		range = range + (range*(2/10));
		speed++;
		level++;
		Player.reduceMoney(price);
		price = price + (3*level);
	}
}
