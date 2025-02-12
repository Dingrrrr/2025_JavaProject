package ch12;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class RunnableFrameEx3 extends MFrame implements Runnable {

	Random r = new Random();
	int x, y;
	Color c;
	
	public RunnableFrameEx3(int wx, int wy) {
		super(200, 200); //창의 크기
		setLocation(wx, wy); //창이 실행되는 위치값
		c = MColor.rColor();
	}
	
	@Override
	public void run() {
		try {
			for (int i = 0; i < 30; i++) {
				x = r.nextInt(200); //0 ~ 299까지의 난수를 발생
				y = r.nextInt(200); //0 ~ 299까지의 난수를 발생
				Thread.sleep(500); //0.5초
				repaint(); //update -> paint 호출하는 구조
			}
		} catch (Exception e) {}
	}
	
	@Override 
	public void update(Graphics g) {
		g.clearRect(x, y, 10, 10); 
		paint(g);
	}
	
	@Override 
	public void paint(Graphics g) {
		g.setColor(c); 
		g.fillOval(x, y, 10, 10); 
	}

	public static void main(String[] args) {
		RunnableFrameEx3 ra[] = new RunnableFrameEx3[9];
		for (int i = 0; i < ra.length; i++) {
			int wx = 200 + (i/3)*200;
			int wh = 200 + (i%3)*200;
			ra[i] = new RunnableFrameEx3(wx, wh);
			Thread t = new Thread(ra[i]);
			t.start();
		}
	}
}
