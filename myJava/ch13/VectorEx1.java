package ch13;

import java.util.Vector;

//Vector는 Collection의 대표적인 클래스
public class VectorEx1 {
	public static void main(String[] args) {
		Vector vec = new Vector(); //10개의 저장시킬 수 있는 칸 생성
		System.out.println(vec.size());
		System.out.println(vec.capacity());
		System.out.println("-------------------");
		boolean flag = vec.add("하하");
		System.out.println(flag);
		vec.addElement(new String("호호"));
		vec.addElement(22); //AutoBoxing
		vec.addElement(Integer.valueOf(23));
		System.out.println(vec.size());
		//배열과 Vector 밑에 항상 for문 존재
		for (int i = 0; i < vec.size(); i++) {
			vec.elementAt(i);
			System.out.println(vec.get(i));
		}
		System.out.println("-------------------");
		//foreach 문은 i값을 사용할 수 없음.
		for (Object obj : vec) {
			System.out.println(obj);
		}
	}
}
