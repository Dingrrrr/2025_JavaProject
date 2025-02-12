package ch12;

class Thread1 extends Thread {
	
	String name;
	
	public Thread1(String name) {
		this.name = name;
	}
	
	//run 메소드는 문법적으로 강제성은 아니지만 멀티 스레드의 기능을 위해서는 Override
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

public class ThreadEx1 {
	public static void main(String[] args) {
		Thread1 t1 = new Thread1("첫 번째");
		Thread1 t2 = new Thread1("두 번째");
		//t1.run(); //직접적인 호출을 하면 멀티 스레드 실행이 안됨.
		t1.start(); //JVM에서 쓰레드 스케줄러가 있는데 여기에 등록 start이고
		t2.start(); //run메소드 호출은 내부적으로 알아서 실행
	}
}
