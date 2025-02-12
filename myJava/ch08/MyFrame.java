package ch08;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class MyFrame extends MFrame implements ActionListener {

	Button btn;
	
	public MyFrame() {
		btn = new Button("버튼");
		add(btn, BorderLayout.SOUTH);
		//MyFrame은 Frame 및 ActionListener 타입.
		//addActionListener 메소드의 매개변수 타입은 ActionListener 이다.
		//그래서 현재 객체의 타입에 매개변수 들어갈 수 있음. <- this
		//버튼을 클릭하면 이벤트 핸들러 호출 <- actionPerformed
		btn.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		setBackground(MColor.rColor());
		Color c[] = MColor.rColor2();
		btn.setBackground(c[0]); //버튼의 배경색
		btn.setForeground(c[1]); //버튼의 전경색(글자)
	}
	
	public static void main(String[] args) {
		new MyFrame();
	}
}
