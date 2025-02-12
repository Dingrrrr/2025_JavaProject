package ch11;

import java.util.Calendar;

public class CalendarEx1 {
	public static void main(String[] args) {
		//Calendar : 추상클래스이고 메소드로 그 기능을 만듬
		Calendar cal = Calendar.getInstance();
		System.out.print("오늘의 날씨 : ");
		System.out.print(cal.get(Calendar.YEAR)+"년 ");
		System.out.print(cal.get(Calendar.MONTH)+1+"월 ");
		System.out.print(cal.get(Calendar.DATE)+"일 ");
		System.out.println();
		int ampm = cal.get(Calendar.AM_PM);
		if(ampm == Calendar.AM)
		System.out.println("현재 시간은 AM ");
		else
			System.out.println("현재 시간은 PM ");
		System.out.println(cal.get(Calendar.HOUR) + "시 ");
		System.out.println(cal.get(Calendar.MINUTE) + "분 ");
		System.out.println(cal.get(Calendar.SECOND) + "초 ");
	}
}
