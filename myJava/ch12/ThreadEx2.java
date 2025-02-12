package ch12;

class NoThread2 extends Thread {
	
String name;
	
	public NoThread2(String name) {
		this.name = name;
	}
	
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(200); //현재 스레드를 0.2초 동안 정지(일하지말고 졸아라)
				System.out.println(name + " : " + i);
			} catch (Exception e) {}
		}
	}//--run
	public void start() {
		run();
	}
}

public class ThreadEx2 {
	public static void main(String[] args) {
		NoThread2 n1 = new NoThread2("First");
		NoThread2 n2 = new NoThread2("Second");
		n1.start();
		n2.start();
	}
}
