package ch13;

import java.util.Enumeration;
import java.util.Hashtable;

public class HashtableEx1 {
	public static void main(String[] args) {
		Hashtable<String, String> ht = 
				new Hashtable<String, String>();
		ht.put("사과", "Apple");
		ht.put("포도", "Grapes");
		ht.put("딸기", "Strawberry");
		ht.put("딸기", "Erdbeeren"); //덮어쓰기
		ht.put("a", "1111");
		System.out.println(ht.size());
		System.out.println("---------------");
		//Key 값으로 value 접근하는 방식의 자료구조
		Enumeration<String> e = ht.keys(); //모든 키 값을 리턴
		while(e.hasMoreElements()) {
			String key = e.nextElement();
			String value = ht.get(key);
			System.out.println(key + " : " + value);
		}
	}
}
