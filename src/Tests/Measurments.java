package Tests;

import java.util.Arrays;
import java.util.Random;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Measurments {

	public static long insertionSort(long arr[]) 
    { 
		long count = 0;
        int n = arr.length; 
        for (int i = 1; i < n; i++) { 
            long key = arr[i]; 
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
	
	public static long[] down(int n) {
		long[] res = new long[n];
		for (int i = 0 ; i<n; i++)
			res[i] = n-i-1;
		return res;
	}
	
	public static long[] rand(int n) {
		Random random = new Random();
		long[] res = new long[n];
		for (int i=0; i<n; i++) {
			int val = random.nextInt(n);
			res[i] = val;
			}
		return res;
	}
	
	static void printArray(long arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
	
	static long[] sortedArray(int n) {
		long[] res = new long[n];
		//Arrays.sort(arr);
		for (int i=0; i<n; i++) 
			res[i]=i;
		return res;
	}
	
	static void part1Array() {
		for (int i=1; i<11; i++) {
			long[] randArr = rand(10000*i);
			long[] randArrSorted = Arrays.copyOf(randArr, 10000*i);
			System.out.println("rand " + 10000*i + " count: " + insertionSort(randArr));
			Arrays.sort(randArrSorted);
			System.out.println(Arrays.equals(randArrSorted, randArr));
			long[] downArr = down(10000*i);
			System.out.println("down " + 10000*i + " count: " + insertionSort(downArr));
			System.out.println(Arrays.equals(downArr, sortedArray(10000*i)));
		}
	}
	
	
	static void part1AVL() {
		
	}
	
public static void main(String[] args)
{
	part1Array();
	part1AVL();
}
}
