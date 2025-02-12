package ch13;

import java.util.Vector;

class Box1 { //다양한 타입의 data를 저장
	private Object data;
	public void set(Object data) {this.data = data;}
	public Object get() {return data;}
}

class Box2<K> { //지정한 타입만 data를 저장
	private K data;
	public void set(K data) {this.data = data;}
	public K get() {return data;}
}

class Box3<K,V> { //Hashtable(바구니)에서 선언된 터압
	private K key;
	private V value;
}

public class GenericEx1 {
	public static void main(String[] args) {
		Box1 b1 = new Box1();
		b1.set(Integer.valueOf(22));
		Integer i1 = (Integer)b1.get();
		///////////////////////////////
		Box2<String> b2 = new Box2<String>();
		//b2.set(Integer.valueOf(23));
		b2.set("뉴진스");
		String str = b2.get();
		
		Vector<String> vec = new Vector<String>();
		String str1 = "하하";
		vec.add(str1);
		vec.add(str1); //동일한 객체가 들어와도 사이즈는 2
		System.out.println(vec.size());
	}
}
