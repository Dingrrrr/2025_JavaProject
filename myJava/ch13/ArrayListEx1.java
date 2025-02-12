package ch13;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListEx1 {
	public static void main(String[] args) {
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("짜장면");
		list.add("짬뽕");
		list.add("우동");
		list.add(2,"탕수육");
		list.add(3,"만두");
		for (String str : list) {
			System.out.println(str);
		}
		System.out.println("--------------");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		//순차적으로 가져오고 모든 Collection 객체에 동적바인딩의 역할로 사용 가능
		Iterator<String>e =  list.iterator();
		while(e.hasNext()) {
			System.out.println(e.next());
		}
	}
}
