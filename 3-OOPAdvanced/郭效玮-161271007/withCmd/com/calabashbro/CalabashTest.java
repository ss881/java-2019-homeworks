package com.calabashbro;


public class CalabashTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO �Զ����ɵķ������
		Battleground battleground=new Battleground(15);
		
		Calabash calabash1=new Calabash("����");
		Calabash calabash2=new Calabash("����");
		Calabash calabash3=new Calabash("����");
		Calabash calabash4=new Calabash("����");
		Calabash calabash5=new Calabash("����");
		Calabash calabash6=new Calabash("����");
		Calabash calabash7=new Calabash("����");
		Calabash grandfa=new Calabash("үү");
		
		Monster scorpion=new Monster("Ы��");
		Monster snake=new Monster("�߾�");
		Monster smallPotato=new Monster("ආ�");
		
		
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
