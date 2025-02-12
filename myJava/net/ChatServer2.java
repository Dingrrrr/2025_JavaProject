package net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatServer2 {

	public static final int PORT = 8002;
	ServerSocket server;
	// 접속한 Client 객체를 저장하는 벡터
	Vector<Client2> vc;

	public ChatServer2() {
		try {
			vc = new Vector<Client2>();
			server = new ServerSocket(PORT);
		} catch (Exception e) {
			System.err.println("Error in Server");
			System.exit(1); // 비정상적인 종료
		}
		System.out.println("**************************************");
		System.out.println("***ChatServer v2.0 Start**************");
		System.out.println("**************************************");
		try {
			while (true) {
				Socket sock = server.accept();
				Client2 ct = new Client2(sock);
				ct.start();
				vc.addElement(ct);
			}
		} catch (Exception e) {
			System.err.println("Error in Server");
		}
	}

	public void sendAllMessage(String msg) {
		for (Client2 ct : vc) {
			ct.sendMessage(msg);
		}
	}

	// 접속이 끊어지면 Vector에서 제거 기능
	public void removeClient(Client2 ct) {
		vc.remove(ct);
	}

	// ChatList Token 기능 : aaa; bbb; ccc; 홍길동;
	// Vector에 안에 있는 Client2 에 있는 id를 리턴 받음.
	public String getIdList() {
		String list = "";
		for (Client2 ct : vc) {
			list += ct.id + ChatProtocol2.MODE2; // ";"로 하게 될 경우 오류의 위험이 있다.
		}
		return list;
	}

	// 매개변수 id로 Client2찾기
	public Client2 findClient(String id) {
		Client2 fct = null;
		for (Client2 ct : vc) {
			if (ct.id.equals(id)) {
				fct = ct;
				break;
			}
		}
		return fct;
	}

	class Client2 extends Thread {

		Socket sock;
		BufferedReader in;
		PrintWriter out;
		String id;

		public Client2(Socket sock) {
			try {
				this.sock = sock;
				in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				out = new PrintWriter(sock.getOutputStream(), true);
				System.out.println(sock + "Connected......");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				sendMessage("사용할 아이디를 입력하세요.");
				while (true) {
					String line = in.readLine();
					if (line == null)
						break;
					else
						routine(line);
				}
			} catch (Exception e) {
				removeClient(this);
				System.out.println(sock + "끊어짐...");
				// ChatList 새로고침
				String str = ChatProtocol2.CHATLIST;
				str += ChatProtocol2.MODE1 + getIdList();
				sendAllMessage(str); // 접속된 모든 사용자에게 전송
			}
		}

		// Client에 전달된 메세지 분석 기능
		public void routine(String line) {
			// CHATALL:오늘은 즐거운 목요일입니다
			int idx = line.indexOf(ChatProtocol2.MODE1);
			String cmd = line.substring(0, idx); // CHATALL
			String data = line.substring(idx + 1); // 오늘은...
			if (cmd.equals(ChatProtocol2.ID)) { // ID:aaa
				id = data;
				// 새로운 접속자가 추가되면서 Chat List 전송
				sendAllMessage(ChatProtocol2.CHATLIST + ChatProtocol2.MODE1 + getIdList()); // CHATLIST:aaa;bbb;ccc;홍길동;
				// 새로운 접속자 welcome 메시지 전송
				sendAllMessage(ChatProtocol2.CHATALL + ChatProtocol2.MODE1 + "[" + id + "]님이 입장하였습니다");

			} else if (cmd.equals(ChatProtocol2.CHATALL)) {
				sendAllMessage(ChatProtocol2.CHATALL + ChatProtocol2.MODE1 + "[" + id + "]" + data); // CHATALL:[aaa]
																										// 밥먹자

			} else if (cmd.equals(ChatProtocol2.CHAT)) {
				// CHAT:bbb;오늘 뭐해? <- aaa가 bbb에게 귓속말 전달
				idx = data.indexOf(ChatProtocol2.MODE2);
				cmd = data.substring(0, idx); // bbb
				data = data.substring(idx + 1); // 오늘 뭐해?
				// 받을 사람 Client를 찾음
				Client2 ct = findClient(cmd/* bbb */);
				if (ct != null) {
					// bbb에게 보냄
					ct.sendMessage(ChatProtocol2.CHAT 
							+ ChatProtocol2.MODE1 + "[" + id/* aaa */ + "(S)]" + data);
					/// aaa에게 보냄
					sendAllMessage(ChatProtocol2.CHAT 
							+ ChatProtocol2.MODE1 + "[" + id/* aaa */ + "(S)]" + data);
				} else {
					// bbb가 현재 접속자가 아닌 경우 -> aaa에게 메시지를 보냄
					sendMessage(ChatProtocol2.CHAT + ChatProtocol2.MODE1 + "[" + cmd + "님이 현재 접속자가 아닙니다.");
				}

			} else if (cmd.equals(ChatProtocol2.MESSAGE)) {
				// MESSAGE:bbb;언제 마쳐?
				idx = data.indexOf(ChatProtocol2.MODE2);
				cmd = data.substring(0, idx); // bbb
				data = data.substring(idx + 1); // 언제 마쳐?
				Client2 ct = findClient(cmd/*bbb*/);
				if(ct!=null) {
					// bbb에게 : MESSAGE:aaa;언제 마쳐?
					ct.sendMessage(ChatProtocol2.MESSAGE 
							+ ChatProtocol2.MODE1 + id + ChatProtocol2.MODE2+ data);
				} else {
					sendMessage(ChatProtocol2.CHAT + ChatProtocol2.MODE1 + "[" + cmd + "님이 현재 접속자가 아닙니다.");
				}
				
			}
		}

		public void sendMessage(String msg) {
			out.println(msg);
		}
	}

	public static void main(String[] args) {
		new ChatServer2();
	}
}