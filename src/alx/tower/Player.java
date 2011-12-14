package alx.tower;

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
}
