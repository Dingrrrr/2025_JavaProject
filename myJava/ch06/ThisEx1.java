package ch06;

class This1 {
	
	This1(This2 t2) {
		
	}
}

class This2 {
	This2() {
		This1 t1 = new This1(this/*생성된 This2 객체 자신*/);
	}
}

public class ThisEx1 {

	public static void main(String[] args) {
		
	}

}
