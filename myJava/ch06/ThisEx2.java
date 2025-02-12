package ch06;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ThisFrame extends MFrame implements ActionListener {
	
	Button btn;
	
	public ThisFrame() {
		btn = new Button("my Button");
		add(btn, BorderLayout.SOUTH);
		//ActionListener타입의 객체가 매개변수로 필요
		//implements ActionListener 했기 때문에 같은 타입
		btn.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		setBackground(MColor.rColor());
	}
	
}

public class ThisEx2 {
	public static void main(String[] args) {
		new ThisFrame();
	}
}
