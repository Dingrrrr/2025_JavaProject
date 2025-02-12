package ch09;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame3 extends MFrame{
	
	Button btn1, btn2, btn3, btn4;
	//btn1 : Frame 배경색 랜덤하게 변경
	//btn2 : 버튼 배경색 랜덤하게 각각 적용
	//btn3 : 버튼 전경색(글자색) 랜덤하게 각각 적용
	//btn4 : 종료
	public MyFrame3() {
		setTitle("MyFrame4");
		Panel p = new Panel();
		p.add(btn1 = new Button("Btn1"));
		p.add(btn2 = new Button("Btn2"));
		p.add(btn3 = new Button("Btn3"));
		p.add(btn4 = new Button("Exit"));
		//익명클래스 이용해서 기능을 구현하시오.
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setBackground(MColor.rColor());
			}
		});
		
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn1.setBackground(MColor.rColor());
				btn2.setBackground(MColor.rColor());
				btn3.setBackground(MColor.rColor());
				btn4.setBackground(MColor.rColor());
			}
		});
		
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn1.setForeground(MColor.rColor());
				btn2.setForeground(MColor.rColor());
				btn3.setForeground(MColor.rColor());
				btn4.setForeground(MColor.rColor());
			}
		});
		
		btn4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		add(p, BorderLayout.SOUTH);
		validate();
	}
	
	
	public static void main(String[] args) {
		new MyFrame3();
	}
}