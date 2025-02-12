package ch04;

import java.util.Arrays;
import java.util.Random;
import java.util.TreeSet;

public class LuckyLotto2 {

	public static void main(String[] args) {
		Object arr[] = getLotto();
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i] + "\n");
		}
	}
	//1~45사이의 난수를 오름차순으로 리턴
	public static Object[] getLotto() {
		//TreeSet(자료구조)는 중복과 정렬이 자동으로 만들어 지는 클래스
		TreeSet<Integer> ts = new TreeSet<Integer>();
		for (int i = 0; ts.size() < 6; i++) {
			int lotto = (int)(Math.random()*45)+1;
			ts.add(lotto);
		}
		Object obj[] = ts.toArray();
		return obj;
	}
}
