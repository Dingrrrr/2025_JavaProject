package awt;

import java.awt.Choice;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ChoiceEx3_1 extends MFrame 
implements ItemListener{
	
	Choice name, star;
	String ms[] = {"현 빈","원 빈","이민호","김수현","김우빈","이종석"};
	String fs[] = {"고아라","이연희","이하늬","문채원","수 지","김연아"};
	
	public ChoiceEx3_1() {
		setTitle("Choice 예제3");
		name = new Choice();
		name.add("남자연예인");
		name.add("여자연예인");
		star = new Choice();
		for (int i = 0; i < ms.length; i++) {
			star.add(ms[i]);
		}
		inputItem(star, ms);
		//이벤트 연결완료
		name.addItemListener(this);
		add(name);
		add(star);
	}
	
	//Choice에 item을 추가하는 기능
	public void inputItem(Choice ch, String item[]) {
		//기존에 아이템 모두 삭제
		ch.removeAll();
	}
	
	@Override //name Choice item 변경시 호출되는 메소드
	public void itemStateChanged(ItemEvent e) {
		if(name.getSelectedItem().equals("남자연예인")) {
			inputItem(star, ms);
		}else if(name.getSelectedItem().equals("여자연예인")) {
			inputItem(star, fs);
		}
	}
	
	public static void main(String[] args) {
		new ChoiceEx3();
	}
}





