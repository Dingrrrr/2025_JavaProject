package graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import event.MFrame;

public class ImageViewEx1 extends graphics.MFrame {

	Image img;
	
	public ImageViewEx1() {
		super(400, 400);
		img = Toolkit.getDefaultToolkit().getImage("graphics/aaa.jpg");	
	}
	
	@Override
	//ImageObserver : 이미지 로딩이 다 될때까지 기다리는 기능의 인터페이스
	public void paint(Graphics g) {
		super.paint(g);
		//System.out.println(11111);
		g.drawImage(img, 0, 0, this);
	}
	
	public static void main(String[] args) {
		new ImageViewEx1();
	}

}
