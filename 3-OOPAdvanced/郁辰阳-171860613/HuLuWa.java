import java.util.Random;

public class HuLuWa extends Creature implements Comparable<HuLuWa>{
	int num;
	String color;
	
	public HuLuWa(int row,int col,String name,int inputnum, String inputcolor){
		super(row,col,name);
		num=inputnum; color=inputcolor;
	}
	
	
	public int compareTo(HuLuWa input) {
		if(this.num>input.num) 
			return 1;
		else if(this.num==input.num)
			return 0;
		else
			return -1;
	}
}

class Grandpa extends Creature{
	public Grandpa(int row, int col) {
		super(row, col, "үү");
	}
	
	int[] SortHuLuWa() {
		int []sequence=new int[7];
		Random myrandom=new Random();
		for(int i=0;i<7;i++) {
			int seed=myrandom.nextInt(7)+1;
			
			boolean check=false;
			while(!check) {
				check=true;
				for(int j=0;j<i;j++) {
					if(sequence[j]==seed){
						check=false;
						seed=myrandom.nextInt(7)+1;
						break;
					}
				}
			}
			
			sequence[i]=seed;
		}
		return sequence;
	}
}
