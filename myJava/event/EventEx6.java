package event;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EventEx6 extends MFrame {

	Checkbox red, green, cyan;
	CheckboxGroup grp;
	
	public EventEx6() {
		Panel p = new Panel();
		grp = new CheckboxGroup();
		p.add(red = new Checkbox("Red", false, grp));
		p.add(green = new Checkbox("Green", false, grp));
		p.add(cyan = new Checkbox("Cyan", true, grp));
		setBackground(Color.CYAN);
		add(p, BorderLayout.SOUTH);
		//익명클래스 이벤트 구현
		red.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				setBackground(Color.RED);
			}
		});
		
		green.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				setBackground(Color.GREEN);
			}
		});
		
		cyan.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				setBackground(Color.GREEN);
			}
		});
		
		validate();
	}
	
	
	public static void main(String[] args) {
		new EventEx6();
	}
}
