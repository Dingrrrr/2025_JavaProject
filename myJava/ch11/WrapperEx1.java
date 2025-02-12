package ch11;

import java.util.Vector;

public class WrapperEx1 {
	public static void main(String[] args) {
		//byte, short, int(약어), long
		//float, double
		//char(약어), boolean
		//Auto Boxing, Auto UnBoxing
		int a = 10;
		//Integer i = Integer.valueOf(a);
		Integer i = a; //Auto Boxing
		int b = i; //Auto UnBoxing
		Vector v = new Vector(); //Vector:객체를 저장시켜주는 클래스
		v.add(new String("aaa")); //0
		v.add(new Object()); //1
		v.add(a); //Auto Boxing <- Integer 타입으로 저장
		int c = (Integer)v.get(2); //Auto UnBoxing <- int로 자동 변환
		
		Integer i1 = new Integer(a);
		Integer i2 = new Integer("22");
		Integer i3 = Integer.valueOf(a); //valueOf <- static 메소드
		Integer i4 = Integer.valueOf("23");
		int d = i4.intValue(); //Auto Boxing 사용하지 않으면 이렇게 사용
		int e = Integer.parseInt("23"); //숫자 형태의 문자를 정수로 리턴
		System.out.println(Integer.toBinaryString(e)); //2진수 
		System.out.println(Integer.toOctalString(e)); //8진수 
		System.out.println(Integer.toHexString(e)); //10진수
		
		System.out.println(Integer.MAX_VALUE);
	}
}
