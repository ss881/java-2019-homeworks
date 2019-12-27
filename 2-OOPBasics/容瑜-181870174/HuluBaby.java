import java.util.Random;
public class HuluBaby {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AhuluBaby Baby1 = new AhuluBaby(1,"��ɫ","�ϴ�",0,1,1);
		AhuluBaby Baby2 = new AhuluBaby(2,"��ɫ","�϶�",0,4,1);
		AhuluBaby Baby3 = new AhuluBaby(3,"��ɫ","����",0,5,1);
		AhuluBaby Baby4 = new AhuluBaby(4,"��ɫ","����",0,0,1);
		AhuluBaby Baby5 = new AhuluBaby(5,"��ɫ","����",0,3,1);
		AhuluBaby Baby6 = new AhuluBaby(6,"��ɫ","����",0,6,1);
		AhuluBaby Baby7 = new AhuluBaby(7,"��ɫ","����",0,2,1);
		
		AhuluBaby[][] BabyList = new AhuluBaby[7][7];
		BabyList[0][0] = Baby4;
		BabyList[0][1] = Baby1;
		BabyList[0][2] = Baby7;
		BabyList[0][3] = Baby5;
		BabyList[0][4] = Baby2;
		BabyList[0][5] = Baby3;
		BabyList[0][6] = Baby6;
		
		int i=0;
		int j=0;
		System.out.println("��ʼ��˳��Ϊ��");
		for(i=0;i<7;i++)
		{
			System.out.println(BabyList[0][i].rank);
		}
		for(i=0;i<7-1;i++)
		{
			for(j=0;j<7-1-i;j++)
			{
				if(BabyList[0][j].num>BabyList[0][j+1].num)
				{
					System.out.println(BabyList[0][j].rank+":"+" "+(j+1)+"->"+(j+2));
					System.out.println(BabyList[0][j+1].rank+":"+" "+(j+2)+"->"+(j+1));
					AhuluBaby tem;
					tem = BabyList[0][j];
					BabyList[0][j] = BabyList[0][j+1];
					BabyList[0][j+1] = tem;
					BabyList[0][j].Move(0, j);
					BabyList[0][j+1].Move(0, j+1);
				}
			}
		}
		System.out.println("������ϣ���ʼ������");
		for(i=0;i<7;i++)
		{
			System.out.println(BabyList[0][i].rank);
		}
		
		int n = 6;
		int a = 0;
		while(n>1)
		{
			Random rand1 = new Random();
			a = rand1.nextInt(n);
			AhuluBaby temp;
			temp = BabyList[0][n];
			BabyList[0][n] = BabyList[0][a];
			BabyList[0][a] = temp;
			BabyList[0][n].Move(0, n);
			BabyList[0][a].Move(0, a);
			n--;
		}
		System.out.println("����˳��������£�");
		for(i=0;i<7;i++)
		{
			System.out.println(BabyList[0][i].rank);
		}
		BinarySort(BabyList);
		System.out.println("����ɫ������ϣ���ʼ������");
		for(i=0;i<7;i++)
		{
			System.out.println(BabyList[0][i].color);
		}
		
	}
	
	public static void BinarySort(AhuluBaby[][] arr) {
	    for (int i = 1; i < arr[0].length; i++) {
	    	AhuluBaby temp = arr[0][i];
	        int low = 0, high = i - 1;
	        int mid = -1;
	        while (low <= high) {            
	            mid = low + (high - low) / 2;            
	            if (arr[0][mid].num > temp.num) {               
	                high = mid - 1;            
	            } 
	            else { // Ԫ����ͬʱ��Ҳ�����ں����λ��                
	                low = mid + 1;            
	            }        
	        }        
	        for(int j = i - 1; j >= low; j--) { 
	        	System.out.println(arr[0][j].rank+":"+" "+(j+1)+"->"+(j+2));
	            arr[0][j + 1] = arr[0][j]; 
	            arr[0][j+1].Move(0, j+1);
	        }
	        if(i!=low)
	        {
	        	System.out.println(temp.rank+":"+" "+(i+1)+"->"+(low+1));
	        }
	        arr[0][low] = temp; 
	        arr[0][low].Move(0, low);
	    }
	}
	
	
}


class AhuluBaby{
	int num;//ÿ����«�޶��е�index
	String color;//��«�޵���ɫ
	String rank;//��«�޵�����
	int x;//��«�޵�ƽ��������
	int y;//��«�޵�ƽ��������
	int running_speed;//��«�޵��ƶ��ٶ�,�ں��������Ͼ�����������ٶ��ƶ�
	
	AhuluBaby(){
		num = 1;
		color = "Red";
		rank = "�ϴ�";
		x = num;
		y = 0;
		running_speed = 1;
	}
	
	AhuluBaby(int renum,String recol,String rerank,int my_x,int my_y,int speed){
		num = renum;
		color = recol;
		rank = rerank;
		x = my_x;
		y = my_y;
		running_speed = speed;
	}
	
	void Move(int dest_x,int dest_y) {
		while(x!=dest_x)
		{
			if(dest_x > x)
			{
				x = x + running_speed;
			}
			else
			{
				x = x - running_speed;
			}
		}
		while(y!=dest_y)
		{
			if(dest_y > y)
			{
				y = y + running_speed;
			}
			else
			{
				y = y - running_speed;
			}
		}
	}//��«�޳�Ŀ��λ���ƶ�
	
}

/*
class Gmap{
	AhuluBaby man_1;
	AhuluBaby man_2;
	AhuluBaby man_3;
	AhuluBaby man_4;
	AhuluBaby man_5;
	AhuluBaby man_6;
	AhuluBaby man_7;
	
	Gmap(AhuluBaby man1,AhuluBaby man2,AhuluBaby man3,AhuluBaby man4,AhuluBaby man5,AhuluBaby man6,AhuluBaby man7){
		man_1 = man1;
		man_2 = man2;
		man_3 = man3;
		man_4 = man4;
		man_5 = man5;
		man_6 = man6;
		man_7 = man7;
	}
}//���Ǻ�«�����������
*/

