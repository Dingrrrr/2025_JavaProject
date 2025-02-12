package ch09;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends MFrame{

	Button btn1, btn2;
	
	public MyFrame() {
		Panel p = new Panel();
		p.add(btn1 = new Button("변경"));
		p.add(btn2 = new Button("종료"));
		//익명클래스로 배경색 변경과 종료 기능을 각각 구현
		//ActionListener 타입의 객체가 메개변수로
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setBackground(MColor.rColor());
			}
		});
		
		btn2.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		add(p, BorderLayout.SOUTH);
		validate();
	}
	
	public static void main(String[] args) {
		new MyFrame();
	}
	
}
