package ch06;

class Method1 {
	
	//method : 개개체의 반복적인 기능. 최대한 세분화
	int/*리턴타입*/ abs/*메소드 명*/ (int num/*매개변수*/)/*선언부*/ {
		if(num<0)
			num =- num;
		return num;
	}/*구현부*/
	
	void prn(int a, int b) {
		System.out.println(a + " + " + b + " = " + (a+b));
	}
}

public class MethodEx1 {
	public static void main(String[] args) {
		Method1 mt = new Method1();
		int a = mt.abs(-10); //리턴타입이 있는 메소드를 반드시 받을 필요는 없다.
		System.out.println(a);
		mt.prn(20, 30);
	}

}
