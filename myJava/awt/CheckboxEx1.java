package awt;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CheckboxEx1 extends MFrame implements ItemListener {

	Checkbox cb[] = new Checkbox[3];
	Checkbox rb[] = new Checkbox[3];
	CheckboxGroup grp;
	String label1[] = {"선택1", "선택2" ,"선택3"};
	String label2[] = {"라디오1", "라디오2" ,"라디오3"};
	
	public CheckboxEx1() {
		super(420, 120);
		setTitle("Checkbox 예제");
		for (int i = 0; i < cb.length; i++) {
			cb[i] = new Checkbox(label1[i], false);
			cb[i].addItemListener(this);
			add(cb[i]); //Frame에 장착
		}
		grp = new CheckboxGroup();
		for (int i = 0; i < rb.length; i++) {
			rb[i] = new Checkbox(label2[i], grp, true);
			rb[i].addItemListener(this);
			add(rb[i]);
		}
		validate();
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		//이벤트를 일으킨 Checkbox를 리턴
		Checkbox cb = (Checkbox)e.getSource();
		String str = cb.getLabel() + " : " + cb.getState();
		setTitle(str);
	}

	public static void main(String[] args) {
		new CheckboxEx1();
	}

}
