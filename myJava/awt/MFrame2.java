package awt;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MFrame2 extends Frame {
	MFrame2() {
		this(300, 300, new Color(220, 220, 220));
	}
	MFrame2(int w, int h) {
		this(w, h, new Color(220, 220, 220));
	}
	MFrame2(Color c) {
		this(300, 300, new Color(220, 220, 220));
	}
	MFrame2(int w, int h, Color c) {
	//Frame의 기본 레이아웃 : BorderLayout 세팅
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
	}
	
	public static void main(String[] args) {
		MFrame2 m1 = new MFrame2();
		MFrame2 m2 = new MFrame2(150, 100);
		MFrame2 m3 = new MFrame2(Color.CYAN);
		MFrame2 m4 = new MFrame2(400, 400, Color.MAGENTA);
	}
}
