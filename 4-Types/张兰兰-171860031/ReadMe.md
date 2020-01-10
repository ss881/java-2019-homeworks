#作业四 171860031 张兰兰
---
##泛型机制和反射机制
###实现
	class Block<T extends Character>{
    	private T character;
		......
	}
###举例使用
	public class Processor implements SwapEormation{
    	private Block<Character> [][]block;
		public void init_grandpa(){
        block[0][0].init_(new Grandpa(),0,0,Color_of_Calabash.NO);
    	}
	}
此处的基类为Character，派生类用了Grandpa类，她们的符号都是T

---
	
##反射机制的体现
	public class Character {
    	private State_of_Character state;
   		...
    	public void init_(int col,int row,Color_of_Calabash coc){
       	 	...
    	}  
   	 	public void show(){
    	    System.out.print(" ");
    	}
	}
	public class Grandpa extends Character {
    	private Identity identity;
    	@Override
    	public void init_(int col,int row,Color_of_Calabash coc){
        	identity=Identity.GRANDPA;
    	}
    	@Override
    	public void show(){
        	System.out.print("G");
    	}
	}
通过反射知道人物的类型究竟是什么（比如是葫芦娃），从而设置他们的身份，进行位置的调换，展示他们的图像。

---