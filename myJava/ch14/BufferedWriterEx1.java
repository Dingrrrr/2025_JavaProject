package ch14;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class BufferedWriterEx1 {
	public static void main(String[] args) {
		String str = "오늘은 즐거운 화요일입니다.";
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		try {
			bw.write(str);
			bw.newLine();
			bw.write(str);
			bw.newLine();
			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
