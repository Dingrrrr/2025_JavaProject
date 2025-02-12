package event;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEventEx2 extends MFrame {

	Button move;
	
	public KeyEventEx2() {
		super(400, 500, new Color(100, 200, 100));
		setLayout(null);
		move = new Button("move");
		move.setBackground(MColor.rColor());
		move.setBounds(200, 250, 50, 30);
		move.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				String txt = KeyEvent.getKeyText(code);
				//System.out.println("code : " + code);
				//System.out.println("txt : " + txt);
				int x = move.getX(); //현재 버튼의 x좌표 값
				int y = move.getY(); //현재 버튼의 y좌표 값
				if(txt.equals("Up")) {
					y-=10;
				}else if(txt.equals("Down")) {
					y+=10;
				}else if(txt.equals("Left")) {
					x-=10;
				}else if(txt.equals("Right")) {
					x+=10;
				}
				//Frame 밖으로 나가지 못하게 로직 추가 : getWidth, getHeight
				if(x>0&&x<getWidth()-50&&y>20&&y<getHeight()-30) {
					move.setLocation(x, y);
				}
			}
		});
		add(move);
		move.requestFocus();
		validate();
	}
	
	public static void main(String[] args) {
		new KeyEventEx2();
	}

}
