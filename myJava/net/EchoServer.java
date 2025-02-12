package net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	
	//포트 번호 선언
	public static final int Port = 8000;
	
	public EchoServer() {
		try {
			int cnt = 0; //접속한 client 개수
			ServerSocket server = new ServerSocket(Port); //ServerSocket 명령어 덕분에 연결이 쉬워짐
			System.out.println("EchoServer Start............");
			//Client가 언제든지 접속할 수 있는 상황을 만듬.
			while(true) {
				Socket sock = server.accept(); //Client가 접속할때까지 대기
				EchoThread ct = new EchoThread(sock);
				ct.start();
				cnt++;
				System.out.println("Client " + cnt + "Socket");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//클라이언트를 각자 대응하기 위한 Thread 상속 클래스 선언
	class EchoThread extends Thread {
		
		Socket sock; //socket은 input, output Stream을 가지고 있음
		BufferedReader in;
		PrintWriter out;
		
		public EchoThread(Socket sock) {
			try {
				this.sock = sock;
				in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				out = new PrintWriter(sock.getOutputStream(), true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			try {
				//Client가 접속 후에 최초로 받는 메세지
				out.println("Hello Enter BYB to exit"); //out은 outputstream
				while(true) {
					String line = in.readLine(); //Client부터 data 들어오기 전까지 대기상태
					if(line==null) break;
					else {
						out.println("Echo : " + line); //Client 넘겨온 문자열에 Echo 추가해서 반사
						if(line.equalsIgnoreCase("BYB")) {
							break;
						}
					}
				}
				in.close();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new EchoServer();
	}
}
