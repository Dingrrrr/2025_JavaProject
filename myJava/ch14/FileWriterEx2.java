package ch14;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.Writer;

public class FileWriterEx2 extends MFrame 
implements ActionListener {

	TextArea ta;
	TextField tf;
	Button save;
	
	public FileWriterEx2() {
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
			String text = tf.getText().trim();
			if (!text.isEmpty()) {
				ta.append(text + "\n");
				tf.setText("");
			}
		}else if(obj==save) {
			saveToFile();
		}
	}
	
	private void saveToFile() {
		String content = ta.getText();
		if (content.isEmpty()) {
			ta.append("저장할 내용이 없습니다.\n");
			return;
		}
		try {
			String fileName = System.currentTimeMillis() + ".txt";
			FileWriter fw = new FileWriter("ch14/" + fileName);
			fw.write(content);
			fw.close();
			startCoundown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void startCoundown() {
		Thread conutdownThread = new Thread(() -> {
			try {
				for (int i = 5; i > 0; i--) {
					ta.append("저장하였습니다 - " + i + "초 후에 사라집니다." + "\n");
					Thread.sleep(1000);
				}
				ta.append("저장 완료");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		conutdownThread.start();
	}
	

	public static void main(String[] args) {
		new FileWriterEx2();	
	}
}







