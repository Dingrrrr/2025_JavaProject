package net;

public class ChatProtocol2 {
	
	//(Client -> Server) ID : aaa
	public static final String ID = "ID";
	
	//(Client -> Server) CHAT : 받는 아이디; 메세지 ex)CHAT:bbb;밥먹자
	//(Server -> Client) CHAT : 보낸 아이디; 메세지 ex)CHAT:aaa;밥먹자
	public static final String CHAT = "CHAT";
	
	//(Client -> Server) CHATALL:메세지
	//(Server -> Client) CHATALL:[보낸 아이디] 메세지
	public static final String CHATALL = "CHATALL";
	
	//(Client -> Server)MESSAGE:받는 아이디;메세지 ex) MESSAGE:bbb;밥먹자
	//(Client -> Server)MESSAGE:보낸 아이디;메세지 ex) MESSAGE:aaa;밥먹자
	public static final String MESSAGE = "MESSAGE";
	
	//(Server -> Client) CHATLIST : aaa; bbb; ccc; 홍길동;(;으로 구분)
	public static final String CHATLIST = "CHATLIST";
	
	
	public static final String MODE1 = ":";
	public static final String MODE2 = ";";
}
