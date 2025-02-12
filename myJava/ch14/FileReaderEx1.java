package ch14;

import java.io.FileReader;

public class FileReaderEx1 {
	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("ch14/aaa.txt");
			int a = 0;
			while((a=fr.read())!=-1) { //-1까지 찾는다는 뜻
				System.out.print((char)a);
			}
			fr.close();
			System.out.println("\nEnd");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
