package ch12;

class Runnable1 implements Runnable {
	
String name;
	
	public Runnable1(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(200); //현재 스레드를 0.2초 동안 정지(일하지말고 졸아라)
				System.out.println(name + " : " + i);
			} catch (Exception e) {}
		}
	}//--run
}

public class RunnableEx1 {
	public static void main(String[] args) {
		Runnable1 r1 = new Runnable1("하나");
		Runnable1 r2 = new Runnable1("둘");
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
	}
}
