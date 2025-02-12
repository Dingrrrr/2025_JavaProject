package awt;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ChoiceEx2 extends MFrame implements ItemListener {
	
	Choice air, city;
	String sair[] = {"대한항공","아시아나","에어부산","진에어"};
	String scity[] = {"서 울","대 전","대 구","부 산","제주도"};
	
	public ChoiceEx2() {
		super(300, 300, new Color(100,200,100));
		setTitle("Choice 예제2");
		air = new Choice();
		for (int i = 0; i < sair.length; i++) {
			air.add(sair[i]);
		}
		city = new Choice();
		for (int i = 0; i < scity.length; i++) {
			city.add(scity[i]);
		}
		add(air);
		add(city);
		city.addItemListener(this);
		validate();
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		repaint(); //내부적으로 paint호출
	}
	@Override //생성자 호출 후에 자동적으로 호출되는 메소드
	public void paint(Graphics g/*붓*/) { //도화지(frame), 붓(), 화가(개발자)
		super.paint(g); //기본 그리기 작업 수행
		if(air==null||city==null) // air, city 객체가 생성되기 전에는 그리면 안됨.
			return;
		g.setColor(Color.BLUE);
		String str = "항공사 선택 : " + air.getSelectedItem();
		g.drawString(str, 30, 120/*x, y 좌표*/);
		g.setColor(Color.RED);
		str = "도시 선택 : " + city.getSelectedItem();
		g.drawString(str, 30, 150/*x, y 좌표*/);
	}
	public static void main(String[] args) {
		new ChoiceEx2();
	}
}
