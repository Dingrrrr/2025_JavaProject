package ch06_1;

class Access3 {
	
	public int a = 0; //어디에서나 접근 가능.
	protected int b = 0; //상속 또는 같은 패캐지에서는 사용 가능.
	private int c = 0; //자신의 클래스 안에서만 사용 가능.
			
}

public class AccessEx3 {
	
	public int a = 0; //어디에서나 접근 가능.
	protected int b = 0; //상속 또는 같은 패캐지에서는 사용 가능.
	private int c = 0; //자신의 클래스 안에서만 사용 가능.
	
	public static void main(String[] args) {
		Access3 ac = new Access3();
		ac.a = 10;
		ac.b = 20; //동일한 package이기 때문에 public 처럼 접근 가능
		//ac.c = 30;
	}
}
