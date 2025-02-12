package ch06;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class YFrame extends Frame {

	public YFrame() {
		setTitle("제목");
		setSize(300, 300);
		setBackground(Color.BLUE);
		setResizable(false);
		setVisible(true);
		//종료 기능
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		validate();
	}//--디폴트 생성자
	
	public YFrame(int w, int h) {
		setTitle("제목");
		setSize(w, h);
		setBackground(Color.BLUE);
		setResizable(false);
		setVisible(true);
		//종료 기능
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		validate();
	}//--디폴트 생성자

	public YFrame(int w, int h, Color c) {
		setTitle("제목");
		setSize(w, h);
		setBackground(c);
		setResizable(false);
		setVisible(true);
		//종료 기능
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		validate();
	}//--디폴트 생성자
	
	public static void main(String[] args) {
		YFrame f1 = new YFrame();
		YFrame f2 = new YFrame(500, 100);
		YFrame f3 = new YFrame(500, 500, Color.ORANGE);
	}
	
}