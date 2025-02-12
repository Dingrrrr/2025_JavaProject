package ch11;

public class SystemEx1 {
	public static void main(String[] args) {
		System.out.println("출력");
		//1970년 1월 1일 00시부터 현재까지 1초를 밀리초(1초 - 1000sm)로 계산
		long time = System.currentTimeMillis();
		System.out.println("time : " + time);
		System.exit(0); //프로그램 종료, 0은 정상적인 종료
	}
}
