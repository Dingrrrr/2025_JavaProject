package event;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventEx4 extends MFrame {

	Button btn1, btn2; 
	
	public EventEx4() {
		Panel p = new Panel();
		p.add(btn1 = new Button("버튼1"));
		p.add(btn2 = new Button("버튼2"));
		add(p, BorderLayout.SOUTH);
		//이벤트 핸들러 연결 <- 이벤트 리스너 없기 때문에 직접 선언 그리고 생성
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
	}
	
	public static void main(String[] args) {
		new EventEx4();
	}
}
