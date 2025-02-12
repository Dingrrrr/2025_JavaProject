package ch07;

import java.util.Vector;

public class CastingEx2 {
	public static void main(String[] args) {
		
		//객체를 담는 자료구조 기능의 클래스
		Vector vec = new Vector();
		String str = new String("하하");
		vec.add(str);
		//원래 저장했던 타입으로 캐스팅을 해야함.
		String ss = (String)vec.get(0);
		
	}
}
