import java.util.Arrays;
import java.util.Scanner;

class IntArray{
	int[] array;
	int arrayLength;
	public IntArray() {
		// TODO �Զ����ɵĹ��캯�����
	}
	public IntArray(String str) {
		// TODO �Զ����ɵĹ��캯�����
		String[] stringArray=str.split(" ");
		array=new int[stringArray.length];
		this.arrayLength=stringArray.length;
		for (int i = 0; i < arrayLength; i++) {
			array[i]=new Integer(stringArray[i]).intValue();
		}
	}
	@Override
	public String toString() {
		// TODO �Զ����ɵķ������
		String str="";
		for (int i : array) {
			str+=i;
			str+=" ";
		}
		return str;
	}
	public void sort() {
		Arrays.sort(array);
	}
}
public class SortTest {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		System.out.println("input integers splited by blank");
		Scanner scanner=new Scanner(System.in);
		IntArray intArray=new IntArray(scanner.nextLine());
		intArray.sort();
		System.out.println(intArray);
	}

}
