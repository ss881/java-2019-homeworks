/*
*Guo Tiancai 2019-9-10
*/

public class sort {
    public static void main(String[] args) {
        int num[] = new int[]{21, 2, 3, 62, 23, 33, 95, 10, 12, 45, 10, 12, 34, 55};
        QuickSort(num, 0, num.length - 1);
        for(int i = 0; i < num.length; i++){
            System.out.println(num[i]);
        }
    }

    public static void QuickSort(int[] num, int l, int r){
        if (l < r){
            int m = partition(num, l, r);
            QuickSort(num, l, m - 1);
            QuickSort(num, m + 1, r);
        }
    }

    public static int partition(int[] num, int l, int r) {
        int pivot = num[r];
        int i = l -1;
        for(int j = l; j < r; j++){
            if(num[j] <= pivot){
                i++;
                int tmp = num[j];
                num[j] = num[i];
                num[i] = tmp;
            }
        }
        int tmp = num[i + 1];
        num[i + 1] = pivot;
        num[r] = tmp;
        return i + 1;
    }
}