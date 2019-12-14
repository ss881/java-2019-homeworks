package com.company;

public interface Bubble_Sort {
    public static void bubbleSort(Huluwa []brothers,int []arr) {
        for (int i = 0; i < 7; ++i) {
            boolean flag = false;
            for (int j = 0; j < 7 - i - 1; ++j) {
                if (arr[j] > arr[j + 1]) {
                    int front_order=arr[j];
                    int front_col=brothers[front_order].transfer_col();

                    int back_order=arr[j+1];
                    int back_col=brothers[back_order].transfer_col();

                    brothers[front_order].change_place_order(j+1,back_col);
                    brothers[back_order].change_place_order(j,front_col);
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }


}
