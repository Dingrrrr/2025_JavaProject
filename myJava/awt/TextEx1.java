package awt;

import java.awt.Color;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextEx1 extends MFrame implements ActionListener {
	
	TextField tf1, tf2;
	TextArea ta;
	
	public TextEx1() {
		super(250, 300);
		add(new Label("성명"));
		add(tf1 = new TextField("홍길동", 20));
		add(new Label("암호"));
		add(tf2 = new TextField("", 20));
		tf2.addActionListener(this); //이벤트 연결
		tf2.setEchoChar('#');
		add(ta = new TextArea(10, 30));
		ta.append("MyArea 1.0\n");
		ta.setEditable(false); //Enabled는 접근 가능(커서도 가능)
		//ta.setEnabled(false); //Enabled는 접근 불능(커서 조차 불가능)
		Color c[] = MColor.rColor2();
		ta.setBackground(c[0]);
		ta.setForeground(c[1]);
		tf2.requestFocus(); //실행과 동시에 마우스 커서를 암호로 이동
		
		validate();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String str = tf1.getText() + " / " + tf2.getText() +"\n";
		ta.append(str);
		tf1.setText("");
		tf2.setText("");
		tf1.requestFocus();
	}
	
	public static void main(String[] args) {
		new TextEx1();
	}
}
