package ch11;

public class StringBufferEx1 {
	public static void main(String[] args) {
		String str = "aaa";
		str += "bbb";
		System.out.println(str);
		StringBuffer sb = new StringBuffer();
		sb.append("Java");
		System.out.println(sb);
		sb.append(" Programming");
		System.out.println(sb);
		//Java를 Android로 대체
		sb.replace(0, 4, "Android");
		System.out.println(sb);
		sb.deleteCharAt(0);
		//0번쨰 문자를 삭제
		System.out.println(sb);
		//문자를 반전
		sb.reverse();
		System.out.println(sb);
	}
}
