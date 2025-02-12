package ch03;

import java.util.Iterator;

public class Ex2 {
	public static void main(String[] args) {
		char c = 'a';
		char c1 = c++;
		System.out.println(c + " : " + c1);
		char c2 = 'A';
		for (int i = 0; i < 26; i++) {
			System.out.print((c2++)+"\t");
		}
		System.out.println();
		char c3 = 'a';
		for (int i = 0; i < 26; i++) {
			System.out.print((c3++)+"\t");
		}
	}
}
