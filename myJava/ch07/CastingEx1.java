package ch07;

public class CastingEx1 {
public static void main(String[] args) {
	
	int a = 123456789;
	long l = a; //묵시적 형변환
	short s = (short)a; //형시적 형변환
	System.out.println(s);
	
	}
}
