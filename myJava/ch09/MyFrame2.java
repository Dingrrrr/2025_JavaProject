package ch09;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame2 extends MFrame implements ActionListener{

	Button btn1, btn2;
	
	public MyFrame2() {
		Panel p = new Panel();
		p.add(btn1 = new Button("Btn1"));
		p.add(btn2 = new Button("Btn2"));
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		add(p, BorderLayout.SOUTH);
		validate();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==btn1)
			setBackground(MColor.rColor());
		else if(obj==btn2)
			System.exit(0);
	}
	
	public static void main(String[] args) {
		new MyFrame2();
	}

}







