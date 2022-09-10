package com.gfg.java;

import java.util.Scanner;

public class BinaryToDecimalConversion {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int number = sc.nextInt();
		sc.close();
		BintoDec(number);
	}

	public static void BintoDec(int number) {
		int decimal = 0;  
	    int n = 0;  
	    while(true){  
	      if(number == 0){  
	        break;  
	      } else {  
	          int temp = number%10;  
	          decimal += temp*Math.pow(2, n);  
	          number = number/10;  
	          n++;  
	       }  
	    }
	    System.out.println(decimal);
	}
}
