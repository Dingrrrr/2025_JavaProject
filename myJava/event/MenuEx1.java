package event;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.PopupMenu;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuEx1 extends MFrame {
	
	PopupMenu pm;
	MenuBar mb;
	Menu file, edit;
	TextArea ta;
	
	public MenuEx1() {
		super(300,300);
		setTitle("MyEdit 1.0");
		mb = new MenuBar();
		file = new Menu("파일");
		edit = new Menu("편집");
		//////////////////////
		file.add("새파일");
		file.add("열기");
		file.addSeparator();
		file.add("저장");
		file.add("새이름으로 저장");
		file.addSeparator();
		file.add("인쇄");
		file.add("종료");
		
		edit.add("취소");
		edit.add("복사");
		edit.add("잘라내기");
		edit.add("붙여넣기");
		//////////////////////
		mb.add(file);
		mb.add(edit);
		setMenuBar(mb);
		add(ta = new TextArea());
		 popipMenu();
		validate();
	}

	public void popipMenu() {
		pm = new PopupMenu();
		pm.add("실행취소");
		pm.add("되돌리기");
		pm.addSeparator();
		pm.add("복사");
		pm.add("잘라내기");
		pm.add("붙여넣기");
		pm.addSeparator();
		pm.add("모두선택");
		pm.add("속성");
		pm.add("종료");
		pm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if(cmd.equals("종료")) {
					System.exit(0);
				}else if(cmd.equals("복사")) {
					ta.append("MyEdit1.0");
				}
			}
		});
		add(pm);
		ta.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == 3/*오른쪽 마우스*/) {
					pm.show(e.getComponent(),e.getX(),e.getY());
				}
				super.mousePressed(e);
			}
		});
	}
	
	public static void main(String[] args) {
		new MenuEx1();
	}
}