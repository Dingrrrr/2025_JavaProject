package awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Label;
import java.awt.Panel;

public class DesignEx1 extends MFrame2 {
	
	Label label;
	Checkbox cb1, cb2, cb3;
	CheckboxGroup cbg;
	Button btn1, btn2;
	
	public DesignEx1() {
		super(250, 150);
		setTitle("디자인 예제1");
		Panel p1, p2, p3;
		p1 = new Panel();
		p1.setBackground(Color.GREEN);
		label = new Label("과일 중에 선택");
		p1.add(label);
		
		p2 = new Panel();
		cbg = new CheckboxGroup();
		cb1 = new Checkbox("사과", cbg, false);
		cb2 = new Checkbox("딸기", cbg, true);
		cb3 = new Checkbox("앵두", cbg, false);
		p2.add(cb1);
		p2.add(cb2);
		p2.add(cb3);
		
		p3 = new Panel();
		btn1 = new Button("Start");
		btn2 = new Button("End");
		p3.add(btn1);
		p3.add(btn2);
		
		
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.CENTER);
		add(p3, BorderLayout.SOUTH);
		validate();
	}	

	public static void main(String[] args) {
		new DesignEx1();
	}

}

