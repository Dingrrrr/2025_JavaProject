package awt;

import java.awt.Label;

import awt.MFrame;

public class LabelEx1 extends MFrame {
	
	Label label[];
	int pos[] = {Label.LEFT, Label.CENTER, Label.RIGHT, Label.LEFT};
	
	public LabelEx1() {
		//3개 이상이면 무조건 배열로 선언함.
		//아직 Label 객체가 만들어진 것은 아님.(null값이 들어있는 것)
		label = new Label[4]; //Label 객체가 만들어진 것 아님
		String str = "오늘은 행복한 화요일";
		for (int i = 0; i < label.length; i++) {
			label[i] = new Label(str, pos[i]);
			label[i].setBackground(MColor.rColor());
			//컴포넌트를 생성하고 배경색 세팅하고 컨테이너에 부착
			add(label[i]);
		}
		validate(); //새로고침
	}
	
	public static void main(String[] args) {
		new LabelEx1();
	}
}
