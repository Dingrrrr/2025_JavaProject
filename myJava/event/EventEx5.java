package event;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventEx5 extends MFrame implements ActionListener {

	Button btn1, btn2, btn3; 
	
	public EventEx5() {
		Panel p = new Panel();
		p.add(btn1 = new Button("버튼1"));
		p.add(btn2 = new Button("버튼2"));
		p.add(btn3 = new Button("버튼3"));
		
		add(p, BorderLayout.SOUTH);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		
	}
	
	@Override // btn3를 추가를 하여 기능은 btn1, btn2, btn3의 배경을 랜덤을 세팅
	public void actionPerformed(ActionEvent e) {
		//ActionEvent를 호출시킨 이벤트 소스를 obj리턴
		Object obj = e.getSource();
		if(obj==btn1) {
			setBackground(MColor.rColor());
		}else if(obj==btn2) {
			System.exit(0);
		}else if(obj==btn3) {
			btn1.setBackground(MColor.rColor());
			btn2.setBackground(MColor.rColor());
			btn3.setBackground(MColor.rColor());
		}
		
	}
	
	public static void main(String[] args) {
		new EventEx5();
	}
}
