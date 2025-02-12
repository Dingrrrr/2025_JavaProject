package event;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyEventEx1 extends MFrame 
implements ActionListener, KeyListener {
	
	TextField tf;
	TextArea ta;
	Button btn;
	
	public KeyEventEx1() {
		super(300,500);
		add(tf = new TextField(),BorderLayout.SOUTH);
		add(ta = new TextArea());
		ta.setEditable(false);
		btn = new Button("REMOVE");
		btn.addActionListener(this);
		tf.addKeyListener(this);
		add(btn,BorderLayout.NORTH);
		validate();
	}
	
	@Override //문자 입력 시 호출
	public void keyTyped(KeyEvent e) {
		ta.append("##keyTyped : " + tf.getText() +"\n");
	}

	@Override //키 누를 시 호출
	public void keyPressed(KeyEvent e) {
		ta.append("##keyPressed : " + tf.getText() +"\n");
	}

	@Override // 키를 땔때 호출
	public void keyReleased(KeyEvent e) {
		ta.append("##keyReleased : " + tf.getText() +"\n");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ta.setText("");
		tf.setText("");
		tf.requestFocus();
	}
	
	public static void main(String[] args) {
		new KeyEventEx1();
	}

	
}



