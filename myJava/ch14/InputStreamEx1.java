package ch14;

import java.io.InputStream;

public class InputStreamEx1 {
	
	public static void main(String[] args) {
		InputStream is = System.in; //키보드
		//대부분 I/O의 메소드는 예외처리
		try {
			while(true) {
				int a = is.read(); //내부적인 스레드 : 입력대기 상태
				if(a==-1/*ctrl + z*/) break;
				System.out.print((char)a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
