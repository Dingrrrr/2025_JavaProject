package ch11;

public class StringEx1 {
	public static void main(String[] args) {
		int a = 10, b = 10;
		//기본형의 ==는 값을 비교
		System.out.println(a==b);
		System.out.println("--------------------");
		String s1 = new String("Java");
		String s2 = new String("Java");
		//String은 new 연산자 없이 유일하게 객체를 생성하는 클래스
		//new 연산자 없이 민들어진 객체를 String 저장소에서 문자열이 만들어진다.
		//입력된 값은 먼저 저장소에서 있는지 없는지 먼저 검색, 있으면 그것을 사용하고 없으면 새롭게 만든다.
		String s3 = "Java";
		String s4 = "Java";
		System.out.println(s1==s2); //false
		System.out.println(s3==s4); //true
		System.out.println(s1==s3); //false
		System.out.println("---------------------");
		System.out.println(s1.equals(s2)); //true
		System.out.println(s1.equals(s3)); //true
		System.out.println(s3.equals(s4)); //true
		s3 = s3+"자바";
		System.out.println(s3);
		String s5 = "APPLE";
		String s6 = "apple";
		//대소문자 무시하고 비교
		System.out.println(s5.equalsIgnoreCase(s6));
	}
}