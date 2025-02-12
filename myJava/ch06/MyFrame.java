package ch06;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MyFrame extends Frame {

	public MyFrame() {
		setSize(300, 300);
		setVisible(true);
		setBackground(randomColor());
		Button btn = new Button("버튼");
		add(btn, "South");
		btn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				setBackground(randomColor());
			}
		});
	}
	
	public Color randomColor(){
		Random rd = new Random();  
		int r = rd.nextInt(256);//0~255 랜덤정수
		int g = rd.nextInt(256);//0~255 랜덤정수
		int b = rd.nextInt(256);//0~255 랜덤정수
		return new Color(r,g,b);
	}
	
	public static void main(String[] args) {
		new MyFrame();
	}
}
