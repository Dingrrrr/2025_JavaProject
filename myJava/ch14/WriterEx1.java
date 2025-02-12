package ch14;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class WriterEx1 {
	
	public static void main(String[] args) {
		try {
			int a = 'A';
			char b = 'b';
			char c = '정';
			OutputStream os = System.out;//콘솔창에 꼽혀 있는 빨대
			Writer writer = new OutputStreamWriter(os);
			writer.write(a);
			writer.write(b);
			writer.write(c);
			writer.flush(); //스트림에 남아있는 data를 비운다. (순차적인 데이터를 강제로 밀어준다.)
			writer.close(); //사용하지 않은 스트림은 반드시 닫는다. (불필요한 리소스가 생기지 않는다.)
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
