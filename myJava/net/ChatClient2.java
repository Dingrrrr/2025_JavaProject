package net;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient2 extends JFrame implements ActionListener, Runnable {

	JButton bt1, bt2, bt3, bt4;
	JTextField tf1, tf2, tf3;
	TextArea area;
	List list;
	Socket sock;
	BufferedReader in;
	PrintWriter out;
	String listTitle = "*******대화자명단*******";
	boolean flag = false;
	String swear[] = { "개새끼", "병신", "씨발", "엿먹어", "미친놈", "미친년" };

	public ChatClient2() {
		setSize(450, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("MyChat v2.0");
		JPanel p1 = new JPanel();
		p1.add(new Label("Host", Label.RIGHT));
		p1.add(tf1 = new JTextField("127.0.0.1", 10));
		p1.add(new Label("Port", Label.RIGHT));
		p1.add(tf2 = new JTextField("8002", 5));
		bt1 = new JButton("connect");
		bt1.addActionListener(this);
		p1.add(bt1);
		add(BorderLayout.NORTH, p1);
		// //////////////////////////////////////////////////////////////////////////////////////////
		area = new TextArea("MyChat v2.0");
		area.setBackground(Color.DARK_GRAY);
		area.setForeground(Color.PINK);
		area.setEditable(false);
		add(BorderLayout.CENTER, area);
		// /////////////////////////////////////////////////////////////////////////////////////////
		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		list = new List();
		list.add(listTitle);
		// list.add("bbb");
		p2.add(BorderLayout.CENTER, list);
		JPanel p3 = new JPanel();
		p3.setLayout(new GridLayout(1, 2));
		bt2 = new JButton("Save");
		bt2.addActionListener(this);
		bt3 = new JButton("Message");
		bt3.addActionListener(this);
		p3.add(bt2);
		p3.add(bt3);
		p2.add(BorderLayout.SOUTH, p3);
		add(BorderLayout.EAST, p2);
		// ///////////////////////////////////////////////////////////////////////////////////////////
		JPanel p4 = new JPanel();
		tf3 = new JTextField("", 30);
		tf3.addActionListener(this);
		bt4 = new JButton("send");
		bt4.addActionListener(this);
		p4.add(tf3);
		p4.add(bt4);
		add(BorderLayout.SOUTH, p4);
		setVisible(true);
		validate();
	}

	public void run() {
		try {
			String host = tf1.getText().trim();
			int port = Integer.parseInt(tf2.getText().trim());
			connect(host, port);
			area.append(in.readLine());
			while (true) {
				String line = in.readLine();
				if (line == null)
					break;
				else
					routine(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// --run

	public void routine(String line) {
		int idx = line.indexOf(ChatProtocol2.MODE1);
		String cmd = line.substring(0, idx); // CHATALL
		String data = line.substring(idx + 1); // 오늘은...
		
		if(cmd.equals(ChatProtocol2.CHATLIST)) {
			// data : aaa;bbb;ccc;홍길동;
			list.removeAll();
			list.add(listTitle); //***대화자 명단*** <- 첫번째 item으로 추가
			String items[] = data.split(ChatProtocol2.MODE2);
			for (String item : items) {
				list.add(item);
			}
		}else if(cmd.equals(ChatProtocol2.CHAT)||cmd.equals(ChatProtocol2.CHATALL)) {
			area.append(data+"\n");
		}else if(cmd.equals(ChatProtocol2.MESSAGE)) {
			// data : bbb;오늘은 여친 생일.
			idx = data.indexOf(ChatProtocol2.MODE2);
			cmd = data.substring(0, idx);
			data = data.substring(idx+1);
			new Message("FROM:", cmd/*bbb*/, data /*오늘은 여친 생일.*/);
		}
	}// --routine

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == bt1) {
			new Thread(this).start(); // run 메소드 실행
			bt1.setEnabled(false);
			tf1.setEnabled(false);
			tf2.setEnabled(false);
			area.setText("");

		} else if (obj == bt2) {
			saveFile(area.getText());

		} else if (obj == bt3) {
			int idx = list.getSelectedIndex();
			if (idx == 0 || idx == -1) {
				new DialogBox(this, "아이디를 선택하세요", "알림");
			} else {
				new Message("TO:");
			}
		} else if (obj == bt4 || obj == tf3) {
			String str = tf3.getText();
			if (str.trim().length() == 0) {
				tf3.setText("");
				tf3.requestFocus();
				return;
			}
			if (filterMgr(str)) {
				new DialogBox(this, "금지어입니다", "경고");
				tf3.setText("");
				tf3.requestFocus();
				return;
			}
			// 아이디 입력, 채팅
			if (!flag) { // 아이디 입력
				sendMessage(ChatProtocol2.ID + ChatProtocol2.MODE1 + str);
				setTitle(getTitle() + "-" + str + "님 반갑습니다");
				area.setText("");
				flag = true;
			} else { // 귓속말 채팅, 전체 채팅
				int idx = list.getSelectedIndex();
				if (idx == 0 || idx == -1) { // 전체 채팅
					sendMessage(ChatProtocol2.CHATALL + ChatProtocol2.MODE1 + str);
				} else { // 귓속말채팅
					String id = list.getSelectedItem();
					// CHAT:bbb;오늘 저녁에 뭐해?
					sendMessage(ChatProtocol2.CHATALL + ChatProtocol2.MODE1 + id + ChatProtocol2.MODE2 + str);
				}
			}
			tf3.setText("");
			tf3.requestFocus();
		}
	}// --actionPerformed

	// 채팅 내용을 저장하고 (파일명은 오늘날짜+시간) 알림으로 표시
	public void saveFile(String msg) {
		try {
			// 현재 날짜와 시간으로 파일명 생성
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String fileName = "Chat_" + timestamp + ".txt";
			BufferedWriter writer = new BufferedWriter(new FileWriter("net/" + fileName));
			writer.write(area.getText());
			writer.flush();
			writer.close();
			area.setText("");
			new DialogBox(this, "대화내용을 저장하였습니다", "알림");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void connect(String host, int port) {
		try {
			sock = new Socket(host, port);
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintWriter(sock.getOutputStream(), true/* auto flush */);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// --connect

	public void sendMessage(String msg) {
		out.println(msg);
	}

	// return : true <- 욕설
	public boolean filterMgr(String msg) {
		for (String s : swear) {
			if (msg.contains(s)) {
				return true;
			}
		}
		return false;
	}

	class Message extends Frame implements ActionListener {

		Button send, close;
		TextField name;
		TextArea ta;
		String mode;// to/from
		String id;

		public Message(String mode) {
			setTitle("쪽지보내기");
			this.mode = mode;
			id = list.getSelectedItem();
			layset("");
			validate();
		}

		public Message(String mode, String id, String msg) {
			setTitle("쪽지읽기");
			this.mode = mode;
			this.id = id;
			layset(msg);
			validate();
		}

		public void layset(String msg) {
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					dispose();
				}
			});
			Panel p1 = new Panel();
			p1.add(new Label(mode, Label.CENTER));
			name = new TextField(id, 20);
			p1.add(name);
			add(BorderLayout.NORTH, p1);

			ta = new TextArea("");
			add(BorderLayout.CENTER, ta);
			ta.setText(msg);
			Panel p2 = new Panel();
			if (mode.equals("TO:")) {
				p2.add(send = new Button("send"));
				send.addActionListener(this);
			}
			p2.add(close = new Button("close"));
			close.addActionListener(this);
			add(BorderLayout.SOUTH, p2);

			setBounds(200, 200, 250, 250);
			setVisible(true);
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == send) {
				sendMessage(ChatProtocol2.MESSAGE + ChatProtocol2.MODE1 + id + ChatProtocol2.MODE2 + ta.getText());
			}
			setVisible(false);
			dispose();
		}
	}

	public static void main(String[] args) {
		new ChatClient2();
	}
}
