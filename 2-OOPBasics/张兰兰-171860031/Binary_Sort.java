package com.company;

public class Binary_Sort {
    public Binary_Sort(){

    }
    public void binarysort(Huluwa[]brothers, int arr[])
    {
        int i,j,temp;
        int low ,high ,mid;
        for(i=1;i<arr.length;i++)
        {
            temp = arr[i];
            low = 0;
            high = i-1;
            while(low<=high)
            {
                mid = (low+high)/2;
                if(arr[mid]>temp)
                    high =mid -1;
                else
                    low = mid + 1;
            }
            int temp_row=arr[high+1];
            int temp_col=brothers[temp_row].transfer_col();
            int temp_front_row=arr[i];
            int temp_front_col=brothers[temp_front_row].transfer_col();
            for(j =i-1;j>high;j--) {
                int front_row=arr[j];
                int front_col=brothers[front_row].transfer_col();

                int back_col=temp_front_col;
                brothers[front_row].change_place(j+1,back_col);
                arr[j + 1] = arr[j];
                temp_front_col=front_col;
            }
           // int front_row=temp;
            //int front_col=brothers[front_row].transfer_col();
            brothers[temp_front_row].change_place(high+1,temp_col);
            arr[high+1] = temp;
        }
    }
}
