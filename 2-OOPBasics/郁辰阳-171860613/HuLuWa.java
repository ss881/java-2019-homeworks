
public class HuLuWa implements Comparable<HuLuWa>{
	//������Ҫ����
	private int num; //����
	private int place; //����λ��
	private String color; //��ɫ
	
	//���캯��
	HuLuWa(int inputnum, int inputplace,String inputcolor){ 
		num=inputnum; place=inputplace;
		color=inputcolor;
	}
	HuLuWa(){
		num=0; place=0;
		color = " ";
	}
	
	int getNum() {
		return num;
	}
	
	//��λ��
	public void swap(int dst) {
		String seniority = " ";
		switch(num) {
		case 1:seniority="�ϴ�"; break;
		case 2:seniority="�϶�"; break;
		case 3:seniority="����"; break;
		case 4:seniority="����"; break;
		case 5:seniority="����"; break;
		case 6:seniority="����"; break;
		case 7:seniority="����"; break;
		default:seniority="error"; break;
		}
		
		System.out.println(seniority+": "+ place +"->"+dst);
		place=dst;
	}
	
	
	@Override
	public int compareTo(HuLuWa b) {
		return num-b.getNum();
	}
	
	public void outputColor() {
		System.out.print(color+" ");
		return;
	}
	
	public void outputSen() {
		String seniority = " ";
		switch(num) {
		case 1:seniority="�ϴ�"; break;
		case 2:seniority="�϶�"; break;
		case 3:seniority="����"; break;
		case 4:seniority="����"; break;
		case 5:seniority="����"; break;
		case 6:seniority="����"; break;
		case 7:seniority="����"; break;
		default:seniority="error"; break;
		}
		System.out.print(seniority+" ");
	}
}



