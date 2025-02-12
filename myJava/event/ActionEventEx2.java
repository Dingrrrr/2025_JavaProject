package event;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionEventEx2 extends MFrame
implements ActionListener{

	List list;
	Button b[] = new Button[4];
	String lab[] = {"추가","지우기","전체지우기","종료"};
	TextField tf;
	
	public ActionEventEx2() {
		super(300, 200);
		//////////////////////////////////////////////
		Panel p = new Panel();
		p.setLayout(new GridLayout(4,1));
		for (int i = 0; i < b.length; i++) {
			p.add(b[i]=new Button(lab[i]));
			b[i].addActionListener(this);
		}
		//////////////////////////////////////////////
		add(list=new  List());
		add(p, BorderLayout.EAST);
		add(tf=new TextField(), BorderLayout.SOUTH);
		tf.addActionListener(this);
		validate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals(lab[0])/*추가*/||e.getSource()==tf) {
			String str = tf.getText().trim();
			if(str.length()==0) {
				setTitle("문자열을 입력하세요.");
			}else {
				list.add(str);
			}
			tf.setText("");
			tf.requestFocus();
			
		}else if(cmd.equals(lab[1])/*지우기*/) {
			
		}else if(cmd.equals(lab[2])/*전체 지우기*/) {
			list.removeAll();
		}else if(cmd.equals(lab[3])/*종료*/) {
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		new ActionEventEx2();
	}

}

