package ch07;

class Singleton {
	
	private static Singleton intance = null;
	
	//외부에서 객체를 생성 할 수 없도록 막아버림.
	private Singleton() {
		//DB연결 객체 10개 만듬.
	}
	
	public static Singleton getInstance() {
		if(intance==null)
			intance = new Singleton();
		return intance;
	}
	
}

public class SingletonEx {
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			//Singleton st = new Singleton();
			Singleton st = Singleton.getInstance();
			System.out.println(st);
		}
	}
}
