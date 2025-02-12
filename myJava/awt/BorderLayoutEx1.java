package awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.util.Iterator;
import java.util.Random;

public class BorderLayoutEx1 extends MFrame2 {

	String str[] = {"첫번째","두번째","세번째","네번째","다섯번째"};
	Button btn[] = new Button[5];
	
	public BorderLayoutEx1() {
		for (int i = 0; i < btn.length; i++) {
			btn[i] = new Button(str[i]);
			Color c[] = MColor.rColor2();
			btn[i].setBackground(c[0]);
			btn[i].setForeground(c[1]);
		}
		add(btn[0], "North");
		add(btn[1], BorderLayout.EAST);
		add(BorderLayout.SOUTH, btn[2]);
		add("West", btn[3]);
		add(btn[4]); //지정하지 않으면 기본값은 Center
		
		validate();
	}
	
	public static void main(String[] args) {
		//new BorderLayoutEx1();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			BorderLayoutEx1 bl = new BorderLayoutEx1();
			int x = r.nextInt(2000);
			int y = r.nextInt(1000);
			int len = r.nextInt(200) + 100; // 100~299
			bl.setBounds(x, y, len, len);
			bl.validate();
		}
	}
}
