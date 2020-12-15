package Tests;

import java.util.Arrays;
import java.util.Random;

public class Measurments {

	public static long insertionSort(int arr[]) 
    { 
		long count = 0;
        int n = arr.length; 
        for (int i = 1; i < n; i++) { 
            int key = arr[i]; 
            int j = i - 1; 
  
            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            while (j >= 0 && arr[j] > key) { 
                arr[j + 1] = arr[j]; 
                count++;
                j = j - 1; 
            } 
            arr[j + 1] = key; 
        }
        return count;
    }
	
	public static int[] down(int n) {
		int[] res = new int[n];
		for (int i = 0 ; i<n; i++)
			res[i] = n-i;
		return res;
	}
	
	public static int[] rand(int[] arr) {
		Random random = new Random();
//		int[] res = new int[n];
		for (int i=0; i<arr.length; i++) {
			int val = random.nextInt(arr.length);
			arr[i] = val;
			}
		return arr;
	}
	
	static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
	
public static void main(String[] args)
{
	//for (int i=1; i<11; i++) {
		int i = 1;
		int[] randArr = new int[10*i];
		randArr = rand(randArr);
		System.out.println("before sorting: ");
		printArray(randArr);
		System.out.println("rand " + 10000*i + " count: " + insertionSort(randArr));
		System.out.println("after sorting: ");
		printArray(randArr);

		int[] downArr = new int[10000*i];
		downArr = down(10000*i);
		System.out.println("before sorting: " + Arrays.toString(downArr));
		System.out.println("down " + 10000*i + " count: " + insertionSort(downArr));
		System.out.println("after sorting: " + Arrays.toString(downArr));
	//}
}
}
