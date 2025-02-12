package ch14;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class ReaderEx1 {

	public static void main(String[] args) {
		try {
			InputStream is = System.in;
			//1byte 단위로 처리되는 data를 문자 단위로 처리
			Reader reader = new InputStreamReader(is);
			while(true) {
				int a = reader.read();
				if(a==-1) break;
				System.out.print((char)a);
			}//--while
			reader.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End");
	}
}
