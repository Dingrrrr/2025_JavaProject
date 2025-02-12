package ch12;

import java.awt.Color;
import java.util.Random;

public class MColor {
	
	static Random rd = new Random();
	
	//static : 객체 생성없이 클래스명으로 사용가능 
	public static Color rColor(){
		int r, g, b;
		r = rd.nextInt(256);//0~255
		g = rd.nextInt(256);//0~255
		b = rd.nextInt(256);//0~255
		return new Color(r,g,b);
	}
	
	//배경색 전경색
	public static Color[] rColor2(){
		int r, g, b;
		r = rd.nextInt(256);//0~255
		g = rd.nextInt(256);//0~255
		b = rd.nextInt(256);//0~255
		Color c[] = new Color[2];
		c[0] = new Color(r,g,b);
		c[1] = new Color(255-r,255-g,255-b);
		return c;
	}
}





