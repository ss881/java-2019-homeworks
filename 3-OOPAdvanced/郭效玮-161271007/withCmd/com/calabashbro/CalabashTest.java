package com.calabashbro;


public class CalabashTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO 自动生成的方法存根
		Battleground battleground=new Battleground(15);
		
		Calabash calabash1=new Calabash("大娃");
		Calabash calabash2=new Calabash("二娃");
		Calabash calabash3=new Calabash("三娃");
		Calabash calabash4=new Calabash("四娃");
		Calabash calabash5=new Calabash("五娃");
		Calabash calabash6=new Calabash("六娃");
		Calabash calabash7=new Calabash("七娃");
		Calabash grandfa=new Calabash("爷爷");
		
		Monster scorpion=new Monster("蝎子");
		Monster snake=new Monster("蛇精");
		Monster smallPotato=new Monster("喽啰");
		
		
		Army calabashArmy=new Army(calabash1, Formation.fmt4);
		Army grandfaArmy=new Army(grandfa);
		Army snakeArmy=new Army(snake);
		Army scorpionArmy=new Army(scorpion, Formation.fmt1);
		
		
		calabashArmy.setSoiders(new Unit[]{calabash2,calabash3,calabash4,calabash5,calabash6,calabash7});
		scorpionArmy.setSoiders(new Unit[]{smallPotato});
		
		battleground.addArmy(grandfaArmy, 1, 1);
		battleground.addArmy(calabashArmy, 3, 3);
		battleground.addArmy(snakeArmy, 1, 13);
		
		for (int i = 1; i < 9; i++) {
			scorpionArmy.setFormation(Formation.getFormation(i));
			battleground.addArmy(scorpionArmy, 7, 10);
			System.out.println(battleground);
			Thread.sleep(1000);
			battleground.delArmy(scorpionArmy, 7, 10);
		}
	}

}
