package event;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MyAction implements ActionListener{
	
	EventEx2 f;
	
	public MyAction(EventEx2 f) {
		this.f = f;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//버튼을 클릭하면 Frame 배경색과 버튼의 배경색이 랜덤 변경
		f.setBackground(MColor.rColor());
		f.btn.setBackground(MColor.rColor());
	}
}

public class EventEx2 extends MFrame {

	Button btn;
	
	public EventEx2() {
		add (btn = new Button("버튼2"), 
				BorderLayout.SOUTH);
		//이벤트 리스너 객체
		MyAction ma = new MyAction(this);
		btn.addActionListener(ma);
	}
	
	public static void main(String[] args) {
		new EventEx2();
	}
}
