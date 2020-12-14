package Tests;

import java.util.Arrays;
import java.util.Random;

public class Measurments {

	public void insertionSort(int arr[]) 
    { 
        int n = arr.length; 
        for (int i = 1; i < n; ++i) { 
            int key = arr[i]; 
            int j = i - 1; 
  
            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            while (j >= 0 && arr[j] > key) { 
                arr[j + 1] = arr[j]; 
                j = j - 1; 
            } 
            arr[j + 1] = key; 
        } 
    }
	
	public static int[] rand(int n) {
		Random random = new Random();
		int[] res = new int[n];
		for (int i=0; i<n; i++) {
			int val = random.nextInt(n);
			res[i] = val;
			}
		return res;
	}
	

public static void main(String[] args)
{
	for (int i=1; i<11; i++) {
		int[] randArr = new int[100*i];
		randArr = rand(10000*i);
		System.out.println(Arrays.toString(randArr));
	}
}
}
