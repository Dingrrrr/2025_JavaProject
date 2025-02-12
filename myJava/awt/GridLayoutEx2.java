package awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Panel;

public class GridLayoutEx2 extends MFrame2 {
	
	String s[] = {"add","remove","removeall","exit"};
	Button []btn = new Button[4];
	
	
	public GridLayoutEx2() {
		Panel p = new Panel();
		p.setLayout(new GridLayout(4,1));
		for (int i = 0; i < btn.length; i++) {
			p.add(btn[i] = new Button(s[i]));
		}
		add(p, BorderLayout.EAST);
		//add(p, BorderLayout.SOUTH);
		validate();
	}
	
	public static void main(String[] args) {
		new GridLayoutEx2();
	}
}