package Tests;

import java.util.Arrays;
import java.util.Random;

public class measurements2 {

	public static int[] rand(int n) {
		Random random = new Random();
		int[] res = new int[n];
		for (int i=0; i<n; i++) {
			int val = random.nextInt(n);
			res[i] = val;
			}
		return res;
	}
	
	public static void main(String[] args) {
		//for (int i=1; i<11; i++) {
			int i = 1;
			int[] randArr = new int[100*i];
			randArr = rand(10000*i);
			System.out.println(Arrays.toString(randArr));
		}
	}

//}
