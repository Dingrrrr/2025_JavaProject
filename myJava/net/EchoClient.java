package net;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EchoClient extends JFrame
implements ActionListener{
	
	JButton btn1, btn2;
	JTextField tf1, tf2;
	TextArea ta;
	JPanel p1, p2;
	BufferedReader in;
	PrintWriter out;
	static final int PORT = 8000;
	
	public EchoClient() {
		setSize(350, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("EchoClient");
		p1 = new JPanel();
		p1.add(new JLabel("HOST ",JLabel.CENTER));
		p1.add(tf1 = new JTextField("127.0.0.1",15));
		p1.add(btn1 = new JButton("Connect"));
		
		p2 = new JPanel();
		p2.add(new JLabel("CHAT ",JLabel.CENTER));
		p2.add(tf2 = new JTextField("",15));
		p2.add(btn2 = new JButton("SEND"));	
		
		tf1.addActionListener(this);
		tf2.addActionListener(this);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		
		add(p1,BorderLayout.NORTH);
		add(ta=new TextArea());
		add(p2,BorderLayout.SOUTH);
		setVisible(true);
		validate();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==tf1||obj==btn1) {
			connect(tf1.getText().trim());
			tf1.setEditable(false);
			btn1.setEnabled(false);
			tf2.requestFocus();
		}else if(obj==tf2||obj==btn2) {
			try {
				//서버에 입력한 문자열 보냄
				out.println(tf2.getText());
				//서버에서 메시지 받음
				ta.append(in.readLine() + "\n");
				tf2.setText("");
				tf2.requestFocus();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	//서버 연결하고 I/O 객체를 만드는 기능
	public void connect(String host) {
		try {
			Socket sock = new Socket(host, PORT);
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintWriter(sock.getOutputStream(), true);
			//서버로부터 최초로 받는 메세지
			ta.append(in.readLine() + "\n");
			tf2.requestFocus();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new EchoClient();
	}
}





