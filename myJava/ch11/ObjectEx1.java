package ch11;

//import가 필요없는 경우 : 같은 패키지, java.lang 클래스 등
import java.lang.*;

class Point1 {}

public class ObjectEx1 {
	public static void main(String[] args) {
		Point1 p1 = new Point1();
		Point1 p2 = new Point1();
		//hashCode : 객체를 구분하는 값(메모리 주소값을 참조)
		System.out.println("클래스 이름 : " + p1.getClass());
		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());
		//p1==p2, p1.equals(p2)는 Stirng에서 추가설명
		System.out.println("객체의 기본 정보 : " + p1.toString());
		System.out.println("객체의 기본 정보 : " + p2.toString());
		System.out.println(p1==p2); //객체의 hashCode 값 비교(주소값 비교)
		System.out.println(p1.equals(p2)); //객체가 가지고 있는 값 비교
		p1 = p2;
		System.out.println(p1==p2);
		System.out.println(p1.equals(p2));
	}
}
