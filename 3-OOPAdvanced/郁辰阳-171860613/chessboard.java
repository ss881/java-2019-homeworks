import java.util.Random;

public class chessboard {
	private Creature board[][];
	private Random myRandom;
	private static String[] HuLuWaColor= {"ºì","³È","»Æ","ÂÌ","Çà","À¶","×Ï"};
	private int EvilChoice;
	
	public chessboard(int n) {
		myRandom=new Random();
		board=new Creature[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				board[i][j]=new Creature();  
			}
		}
		EvilChoice=0;
	}
	
	public void fallback() {
		for(int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				board[i][j]=new Creature();
			}
		}
	}
	
	public void start() {
		//set up the place for Grandpa and SheJing
		Grandpa HuLuGrandpa = new Grandpa(11,1);
		board[10][0]=HuLuGrandpa;
		board[10][14]=new SheJing(11,15);
		//set up the formation for HuLuWa
		int[] sequence=HuLuGrandpa.SortHuLuWa();
		for(int i=0;i<=6;i++) {
			String name;
			switch(sequence[i]) {
			case 1:name="´óÍÞ"; break;
			case 2:name="¶þÍÞ"; break;
			case 3:name="ÈýÍÞ"; break;
			case 4:name="ËÄÍÞ"; break;
			case 5:name="ÎåÍÞ"; break;
			case 6:name="ÁùÍÞ"; break;
			case 7:name="ÆßÍÞ"; break;
			default:name="Error"; break;
			}
			board[i][0]=new HuLuWa(1,i+1,name,sequence[i],HuLuWaColor[i]);
		}
		
		//sort HuLuWa
		for(int cnt=0;cnt<7;cnt++) {
			for(int i=0;i<6;i++) {
				HuLuWa temp1=(HuLuWa)board[i][0]; HuLuWa temp2=(HuLuWa)board[i+1][0];
				int result = temp1.compareTo(temp2);
				if(result>0) {
					Creature temp=board[i][0];
					board[i][0]=board[i+1][0];
					board[i+1][0]=temp;
				}
			}
		}
		
		//set up the formation for the evils
		chooseEvilStrategy();
		//output the whole chessboard
		printBoard();
	}
	
	public void printBoard() {
		for(int i=0;i<15;i++) {
			//System.out.println("******************************");
			for(int j=0;j<15;j++) {
				if(board[i][j].outputName()!="null") {
					String temp=board[i][j].outputName();
					System.out.print(temp.subSequence(0, 1));
					//System.out.print(board[i][j].outputName());
				}
				else
					System.out.print("    ");
			}
			System.out.print('\n');
		}
		//System.out.println("finish outputing");
	}
	
	private void chooseEvilStrategy() {
		//Random myRandom=new Random();
		if(EvilChoice==0)
			EvilChoice=myRandom.nextInt(7)+1;
		else
			EvilChoice=(EvilChoice+5)%7+1;
		//int choice=6;
		switch(EvilChoice) {
		case 1: //º×Òí
			for(int i=0;i<4;i++) {
				board[i][14-i]=new Evil(i+1,15-i,"à¶†ª");
			}
			board[0][8]=new Evil(1,9,"Ð«×Ó¾«");
			board[1][9]=new Evil(2,10,"à¶†ª");
			board[2][10]=new Evil(3,11,"à¶†ª");
			break;
		case 2: //ÑãÐÐ
			for(int i=0;i<4;i++) {
				board[i][14-i]=new Evil(i+1,15-i,"à¶†ª");
			}
			board[4][10]=new Evil(11,5,"Ð«×Ó¾«");
			break;
		case 3: //ºâéî
			for(int i=0;i<3;i++) {
				board[i*2][14]=new Evil(i*2+1,15,"à¶†ª");
				if(i==0)
					board[1][13]=new Evil(2,14,"Ð«×Ó¾«");
					else
						board[2*i+1][13]=new Evil(2*i+2,14,"à¶†ª");
			}
			break;
		case 4: //ÓãÁÛ
			for(int i=0;i<4;i++) {
				board[3-i][14-i]=new Evil(4,15,"à¶†ª");
			}
			board[2][11]=new Evil(3,12,"à¶†ª");  board[2][9]=new Evil(3,10,"à¶†ª");
			board[3][12]=new Evil(4,13,"à¶†ª");  board[3][10]=new Evil(4,11,"à¶†ª");
			board[3][8]=new Evil(4,9,"Ð«×Ó¾«");
			board[4][9]=new Evil(5,10,"à¶†ª");
			break;
		case 5: //·½ÃÅ
			board[0][12]=new Evil(1,13,"à¶†ª");
			board[1][11]=new Evil(2,12,"à¶†ª");
			board[1][13]=new Evil(2,14,"à¶†ª");
			board[2][10]=new Evil(3,11,"Ð«×Ó¾«");
			board[2][14]=new Evil(3,15,"à¶†ª");
			board[3][11]=new Evil(4,12,"à¶†ª");
			board[3][13]=new Evil(4,14,"à¶†ª");
			board[4][12]=new Evil(5,13,"à¶†ª");
			break;
		case 6: //ÙÈÔÂ
			for(int i=0;i<4;i++) {
				board[i][14-i]=new Evil(i+1,15-i,"à¶†ª");
				board[i+1][12-i]=new Evil(i+2,14-i,"à¶†ª");
				board[8-i][14-i]=new Evil(9-i,15-i,"à¶†ª");
				board[7-i][12-i]=new Evil(8-i,14-i,"à¶†ª");
			}
			board[4][9]=new Evil(5,11,"Ð«×Ó¾«");
			//board[4][9]
			board[3][9]=new Evil(4,11,"à¶†ª"); board[5][9]=new Evil(6,11,"à¶†ª");
			board[4][10]=new Evil(5,12,"à¶†ª"); board[4][11]=new Evil(5,13,"à¶†ª");
			break;
		case 7: //·æÊ¸
			board[0][11]=new Evil(1,13,"Ð«×Ó¾«");
			for(int i=1;i<=5;i++) {
				board[2*i][11]=new Evil(2*i+1,12,"à¶†ª");
				if(i<=3) {
					board[2*i-1][11-i]=new Evil(2*i,12-i,"à¶†ª");
					board[2*i-1][11+i]=new Evil(2*i,12+i,"à¶†ª");
				}
			}
			break;
		default: break;
	    }
	}
	
	public static void main(String[] args) {
		chessboard war=new chessboard(15);
		
		for(int i=1;i<=6;i++) {
			System.out.println("µÚ"+ i +"´ÎÕóÐÍ"+'\n');
			war.start();
			war.fallback();
		}
	
		return;
	}
}

