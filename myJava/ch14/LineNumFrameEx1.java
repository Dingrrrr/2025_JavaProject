package ch14;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FileDialog;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;

public class LineNumFrameEx1 extends MFrame 
implements ActionListener{
	
	Button open, save;
	TextArea ta;
	FileDialog openDialog, saveDialog;
	String sourceDir;
	String sourceFile;
	
	public LineNumFrameEx1() {
		super(400,500);
		setTitle("Line Number Add");
		add(ta = new TextArea());
		Panel p = new Panel();
		p.add(open = new Button("OPEN"));
		p.add(save = new Button("SAVE"));
		openDialog = new FileDialog(this,"파일열기",FileDialog.LOAD);
		saveDialog = new FileDialog(this,"파일저장",FileDialog.SAVE);
		ta.setEditable(false);
		open.addActionListener(this);
		save.addActionListener(this);
		add(p,BorderLayout.SOUTH);
		validate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==open) {
			if(openDialog==null) {
				openDialog = new FileDialog(this, "파일열기", FileDialog.LOAD);
			}
			openDialog.setVisible(true);
			String dir, file;
			dir = openDialog.getDirectory();
			file = openDialog.getFile();
			readeFile(dir+file);
		}else if(obj==save) {
			if(saveDialog==null) {
				saveDialog = new FileDialog(this, "파일저장", FileDialog.SAVE);
			}
			saveDialog.setVisible(true);
			String dir, file;
			dir = saveDialog.getDirectory();
			file = saveDialog.getFile();
			writeFile(dir+file);
			DialogBox dialog = new DialogBox(this, "저장하였습니다.", "알림");
		}
	}
	
	public void readeFile(String file){
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			String str;
			int i = 1;
			ta.setText("");
			while((str=br.readLine())!=null) {
				ta.append(i + " : " + str + "\n");
				i++;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void readeFile2(String file){
		try {
			LineNumberReader lr = new LineNumberReader(new BufferedReader(new FileReader(file)));
			String str = "";
			while(true) {
				str = lr.readLine();
				if(str==null) break;
				ta.append(lr.getLineNumber() + ":" + str + "\n");
			}
			lr.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void writeFile(String file){
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(ta.getText());
			ta.setText("");
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		new LineNumFrameEx1();
	}
}