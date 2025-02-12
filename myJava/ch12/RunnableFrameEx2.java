package ch12;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class RunnableFrameEx2 extends MFrame /* implements Runnable */ {
	
	Color c;
	Random r;
	int x, y;
	
	public RunnableFrameEx2(Color c) {
		super(300, 300);
		this.c = c;
		r = new Random();
	}
	
	/* @Override */
	public void run() {
		try {
			for (int i = 0; i < 20; i++) {
				x = r.nextInt(300); //0 ~ 299까지의 난수를 발생
				y = r.nextInt(300); //0 ~ 299까지의 난수를 발생
				Thread.sleep(500); //0.5초
				repaint(); //update -> paint 호출하는 구조
			}
		} catch (Exception e) {}
	}
	
	public void start() {run();}
	
	@Override //지정한 좌표만 새롭게 그린다.
	public void update(Graphics g) {
		g.clearRect(x, y, 10, 10); //x, y 좌표에서 가로, 세로 위치만 다시 그린다.
		paint(g);
	}
	
	@Override //실제 그리는 기능의 메소드
	public void paint(Graphics g) {
		g.setColor(c); //색상을 세팅하고
		g.fillOval(x, y, 10, 10); //안이 채워진 원을 그린다.
	}
	
	public static void main(String[] args) {
		RunnableFrameEx1 f1 = new RunnableFrameEx1(Color.BLUE);
		RunnableFrameEx1 f2 = new RunnableFrameEx1(Color.RED);
		f1.start();
		f2.start();
	}
}
