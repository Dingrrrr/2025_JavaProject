package ch14;

import java.io.File;

public class FileEx2 {
	public static void main(String[] args) {
		String dir = "C:/Windows";
		File fdir = new File(dir);
		if(fdir.isDirectory()) {
			System.out.printf("검색 디렉토리 %s\n", dir);
			System.out.println("-------------------------");
			String list[] = fdir.list(); //파일에 대한 값들을 배열로 보여줌.
			for (int i = 0; i < list.length; i++) {
				File f = new File(dir+File.separator+list[i]);
				if(f.isDirectory()) 
					System.out.println("["+f.getName()+"]");
				else
					System.out.println(f.getName());
			}
		}else {
			System.out.printf("지정한 %s는 디렉토리가 아닙니다.", dir);
		}
	}
}
