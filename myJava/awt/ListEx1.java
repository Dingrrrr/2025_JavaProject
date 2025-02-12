package awt;
import java.awt.Label;
import java.awt.List;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ListEx1 extends MFrame implements ItemListener{
	
	List list1, list2;
	Label label1, label2;
	
	String team1[] ={"롯데","삼성","최강 한화","KIA","NC"};
	String team2[] ={"LG","두산","넥센","SK","KT", "이글스"};
	
	public ListEx1() {
		list1 = new List(3, false); //단일 선택
		list2 = new List(4, true); //복수 선택
		for (int i = 0; i < team1.length; i++) {
			list1.add(team1[i]);
			list2.add(team2[i]);
		}
		add(list1);
		add(list2);
		add(label1 = new Label("team1 :              "));
		add(label2 = new Label("team2 :              "));
		//컴포넌트와 이벤트 연결
		list1.addItemListener(this);
		list2.addItemListener(this);
		validate();
	}
	
	@Override //list1, list2의 item이 변경 시 호출되는 메소드
	public void itemStateChanged(ItemEvent e) {
		//System.out.println("테스트");
		String str1 = list1.getSelectedItem();
		label1.setText("team1 : " + str1);
		String str2[] = list2.getSelectedItems();
		String str3 = "";
		for (int i = 0; i < str2.length; i++) {
			str3+=str2[i] + " ";
		}
		label2.setText("team2 : " + str3);
	}
	
	public static void main(String[] args) {
		new ListEx1();
	}
}
