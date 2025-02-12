package awt;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Label;

public class GridLayoutEx1 extends MFrame2 {

	public GridLayoutEx1() {
		setLayout(new GridLayout(3, 2));
		add(new Button("1"));
		add(new Button("2"));
		add(new Label("Hong"));
		add(new Button("3"));
		add(new Button("4"));
		add(new Label("Gil"));	
	}
	
	public static void main(String[] args) {
		new GridLayoutEx1();
	}

}
