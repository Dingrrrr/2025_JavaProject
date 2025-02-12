package event;

import java.awt.Color;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionEventEx1 extends MFrame implements MouseMotionListener {

	Label lDrag, lMove;
	
	public MouseMotionEventEx1() {
		super(500, 390 ,new Color(100,200,100));
		setResizable(true);
		setLayout(null);
		lDrag = new Label("Drag", Label.CENTER);
		lMove = new Label("Move", Label.CENTER);
		lDrag.setBounds(100, 100, 50, 30);
		lMove.setBounds(100, 150, 50, 30);
		lDrag.setBackground(MColor.rColor());
		lMove.setBackground(MColor.rColor());
		add(lDrag);
		add(lMove);
		addMouseMotionListener(this);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		Point p =e.getPoint();
		lMove.setLocation(e.getPoint());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		lDrag.setLocation(e.getPoint());
	}
	
	public static void main(String[] args) {
		new MouseMotionEventEx1();
	}
}
