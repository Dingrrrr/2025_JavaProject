package ch11;

public class StringEx2 {
	public static void main(String[] args) {
		
		String str = "Java Programming";
		//str += "!!!"; //새로운 "Java Programming!!!" String 저장소 생성
		System.out.println(str);
		int len = str.length();
		System.out.println(len);
		System.out.println(str.toUpperCase());
		System.out.println(str.toLowerCase());
		//지정한 위치값으로 일부만 리턴
		System.out.println(str.substring(5));
		System.out.println(str.substring(5, 10));
		//Java Programming
		//8번째 문자
		char c1 = str.charAt(8);
		System.out.println(c1);
		//지정한 문자 및 문자열의 위치값은?
		int idx1 = str.indexOf('a'); //처음 검색
		System.out.println(idx1);
		int idx2 = str.lastIndexOf('a'); //뒤에서 검색
		System.out.println(idx2);
		int idx3 = str.lastIndexOf('z');
		System.out.println(idx3); //존재하지않는 문자는 -1 리턴.
		//a를 'q'로 변환 출력
		for (int i = 0; i < args.length; i++) {
			if(str.charAt(i)=='a') 
				System.out.print('q');
			else
				System.out.println(str.charAt(i));
		}//--for
		System.out.println();
		String str2 = str.replace('a', 'z');
		System.out.println(str2);
		//문자열 반대로 출력
		for (int i = len-1; i >= 0; i--) {
			System.out.print(str.charAt(i));
		}
		System.out.println();
		StringBuffer sb = new StringBuffer(str);
		System.out.println(sb.reverse()); //역순으로 출력
		String str3 =  "Java&JSP&Flutter&Iot&Spring";
		String str4[] = str3.split("&");
		for (int i = 0; i < str4.length; i++) {
			System.out.println(str4[i]);
		}
		String str5 = "   JSPStudy   ";
		//앞 뒤 공백 제거
		System.out.println("***"+str5.trim()+"***");
		//정수 -> 문자열
		int idx4 = 45;
		String str6 = String.valueOf(idx4);
		System.out.println(str6);
		String str7 = idx4+"";
		System.out.println(idx4+"75");
	}
}
