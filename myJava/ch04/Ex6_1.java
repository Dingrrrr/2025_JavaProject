package ch04;

import java.util.Scanner;

public class Ex6_1 {

	public static void main(String[] args) {
		/*점수와 학년을 입력을 받아서 60이상이면 합격인데, 
		 * 4학년은 70점 이상 합격이다.*/
		Scanner sc = new Scanner(System.in);
		int score, grade;
		//중첩 if문 사용하여 구현하라.
		System.out.print("점수를 입력하세요(0~100) : ");
		score = sc.nextInt();
		System.out.print("학년를 입력하세요(1~4) : ");
		grade = sc.nextInt();
		if(score>=60) {
			if(grade==4&&score<70) {
				System.out.println("불합격");
			}else {
				System.out.println("합격");
			}
		}else {
			System.out.println("불합격");
		}
	}
}
