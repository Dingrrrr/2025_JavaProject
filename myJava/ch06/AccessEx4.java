package ch06;

import ch06_1.AccessEx3;

public class AccessEx4 {
	public static void main(String[] args) {
		AccessEx3 ac = new AccessEx3();
		ac.a = 10;
		//ac.b = 20; //같은 package 아니기 때문에 private 접근 불가능
		//ac.c = 30;
	}
}
