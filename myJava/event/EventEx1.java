package event;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* 2. 버튼을 클릭하면 ActionEvent 객체가 내부적으로 생성
 * 이벤트 소스랑 ActionEvent 객체를 연결하기 위한
 * 리스너를 구현. XxxListner*/
public class EventEx1 extends MFrame
implements ActionListener/*이벤트 리스너*/ {
	
	Button btn;
	
	public EventEx1() {
		//1. 이벤트 소스 생성 : 클릭하면 ActionEvent 발생
		add (btn = new Button("버튼"), BorderLayout.SOUTH);
		//4. 이벤트 소스랑 이벤트 핸들러 연결(addXxxListner)
		btn.addActionListener(this);
	}
	
	//3. 이벤트 리스너의 메소드(이벤트 헨들러)
	@Override
	public void actionPerformed(ActionEvent e) {
		setBackground(MColor.rColor());
	}
	
	public static void main(String[] args) {
		new EventEx1();
	}
	
}
