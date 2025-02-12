package ch05;

import java.util.Iterator;

public class Ex2 {

	public static void main(String[] args) {
		//다차원 배열 : n * for(중첩)
		int arr[][] = new int[2][3];
		for (int i = 0; i < arr/*1차 배열의 크기*/.length; i++) {
			for (int j = 0; j < arr[i]/*2차 배열의 크기*/.length; j++) {
				arr[i][j] = i + j;
				System.out.println("arr["+i+"]["+j+"]" + arr[i][j]);
			}//for2
		}//for1
		System.out.println("문제 1번-------------------------------------------");
		//문제1. arr2의 모든 요소의 합을 구하시오.
		int arr2[][] = {{1,2},
					   {3,4,5},
					   {6},
					   {7,8},
					   {9,10}};
		int sum = 0;
		for (int i = 0; i < arr2.length; i++) {
			for (int j = 0; j < arr2[i].length; j++) {
				sum+=arr2[i][j];
				}
			}
			System.out.println("합 : " + sum);
		
		System.out.println("문제 2번-------------------------------------------");
		//문제2. arr3의 평균을 구하시오.
				double arr3[][] = {{1.0},
								   {2.3,3.4},
								   {4.5,6.2,4.3},
								   {9.0}};
				int sum1 = 0;
				int cnt = 0;
				double avg = 0.0;
				for (int i = 0; i < arr3.length; i++) {
					for (int j = 0; j < arr3[i].length; j++) {
						sum1 += arr3[i][j];
						cnt++;
					}
				}
				System.out.println("평균 : " + sum1/cnt);
				
			System.out.println("문제 3번------------------------------------");
		//문제3. arr4의 평균과 합을 각각 구하시오.
		int arr4[][][] = { { {1, 2, 3}, {4, 5}, {6, 7, 8, 9}},
						 { {10, 11}, {12, 13, 14}, {15}, {22}},
						 { {16, 17, 18, 19, 20},{22, 23, 24}}
						 };
				int sum2 = 0;
				int cnt1 = 0;
				for (int i = 0; i < arr4.length; i++) {
					for (int j = 0; j < arr4[i].length; j++) {
						for (int k = 0; k < arr4[i][j].length; k++) {
							sum2+=arr4[i][j][k];
							cnt1++;
						}
					}
				}
				System.out.println("합 : " + sum2);
				System.out.println("평균 : " + sum2/cnt1);
	}
}
