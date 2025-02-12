package ch14;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

public class FileWriterEx2_1 extends MFrame 
implements ActionListener {

	TextArea ta;
	TextField tf;
	Button save;
	
	public FileWriterEx2_1() {
		super(300, 400);
		setTitle("FileWriterEx1");
		add(ta=new TextArea());
		Panel p = new Panel();
		p.add(tf = new TextField(25));
		p.add(save = new Button("SAVE"));
		ta.setEditable(false);
		tf.addActionListener(this);
		save.addActionListener(this);
		add(p,BorderLayout.SOUTH);
		validate();
	}
	
	@Override //for문 Thread.sleep(1000) -> 1초
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==tf) {
			ta.append(tf.getText()+"\n");
			tf.setText("");
			tf.requestFocus();
		}else if(obj==save) {
			saveFile(ta.getText());
			ta.setText("");
			countDown();
		}
	}
	
	//문자열 저장 기능
	public void saveFile(String txt) {
		try {
			FileWriter fw = new FileWriter("ch14/" + System.currentTimeMillis() + ".txt");
			fw.write(txt);
			fw.flush();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//카운트 다운 기능
	public void countDown() {
		try {
			for (int i = 5; i > 0; i--) {
				ta.setText("저장 하였습니다. - " + i + "초 후에 사라집니다.");
				Thread.sleep(1000); //현재의 스레드를 잠시 멈추어라(버퍼링)
			}
			ta.setText(""); //ta를 초기화
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new FileWriterEx2_1();	
	}
}







