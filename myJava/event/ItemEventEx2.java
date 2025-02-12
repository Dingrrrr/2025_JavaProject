package event;

import java.awt.Checkbox;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ItemEventEx2 extends MFrame {
	
	Label label;
	Checkbox cb1, cb2, cb3;
	
	public ItemEventEx2() {
		super(300, 200);
		setLayout(new FlowLayout());
		add(cb1=new Checkbox("수박"));
		add(cb2=new Checkbox("바나나"));
		add(cb3=new Checkbox("멜론"));
		add(label = new Label("현재 상태 :                    "));
		cb1.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				String str = cb1.getLabel() + " : " + cb1.getState();
				label.setText(str);
				setTitle(str);
			}
		});
		cb2.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				String str = cb2.getLabel() + " : " + cb2.getState();
				label.setText(str);
				setTitle(str);
			}
		});
		cb3.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				String str = cb3.getLabel() + " : " + cb3.getState();
				label.setText(str);
				setTitle(str);
			}
		});
		
		
		
		validate();
	}


	public static void main(String[] args) {
		new ItemEventEx2();
	}
}




