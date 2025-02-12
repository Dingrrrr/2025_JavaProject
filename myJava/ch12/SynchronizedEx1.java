package ch12;

//Synchronized : 멀티쓰레드 환경에서 데이터의 일관성의 보장해주는 동기화 기능

class Synchronized1 implements Runnable {
	
	public synchronized void a(String who) {
		try {
			Thread.sleep(200);
			System.out.println(who + " : b() 호출");
			b(who);
		} catch (Exception e) {}
	}
	
	public synchronized void b(String who) {
		try {
			Thread.sleep(200);
			System.out.println(who + " : a() 호출");
			a(who);
		} catch (Exception e) {}
	}
	
	@Override
	public void run() {
		b(Thread.currentThread().getName()); 
	}
	
}

public class SynchronizedEx1 {
	public static void main(String[] args) {
		Synchronized1 sz = new Synchronized1();
		Thread t1 = new Thread(sz, "첫 번째");
		Thread t2 = new Thread(sz, "두 번째");
		t1.start();
		t2.start();
	}
}
