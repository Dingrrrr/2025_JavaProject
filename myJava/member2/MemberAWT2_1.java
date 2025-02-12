package member2;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MemberAWT2_1 extends MFrame 
implements ActionListener {

	JButton b1, b2, b3, b4, b5;
	List list;

	JLabel label;
	JTextField tf1, tf2, tf3, tf4;
	JPanel p1, p2, p3;
	JButton insBtn, upBtn, zipBtn, dupBtn;
	Vector<MemberBean> vlist;
	MemberMgr mgr;
	int num;
	ZipcodeFrame2_1 zf;
	DialogBox err;

	public MemberAWT2_1() {
		super(320, 450);
		setTitle("회원관리 v2.0");
		mgr = new MemberMgr();
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		label = new JLabel("");
		viewList();
		add(label, BorderLayout.NORTH);
		b1 = new JButton("수정");
		b2 = new JButton("삭제");
		b3 = new JButton("입력");
		b4 = new JButton("리스트");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		p1 = new JPanel();
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		add(p1, BorderLayout.SOUTH);
		validate();
	}
	
	public void viewList() {
		label.setOpaque(true);
		label.setText("NO   NAME       PHONE                   TEAM");
		label.setBackground(Color.CYAN);
		vlist = mgr.listMember();
		list = new List(vlist.size(), false);
		for (int i = 0; i < vlist.size(); i++) {
			MemberBean bean = vlist.get(i);
			String str = (i+1) + "     ";
			str+=bean.getName().trim()+"      ";
			str+=bean.getPhone().trim()+"     ";
			str+=bean.getTeam().trim();
			list.add(str);
		}
		//실행후 list 첫번째 아이템에 커서 세팅
		int len = list.getItemCount();
		if(len>0) 
			list.select(0);
		add(list, BorderLayout.CENTER);
	}
	
	public void insertForm() {
		label.setText("회원입력폼");
		label.setBackground(Color.GRAY);
		p3.setLayout(new GridLayout(7, 1));

		JPanel p4 = new JPanel();
		p4.add(new JLabel("NAME  :"));
		tf1 = new JTextField(20);
		p4.add(tf1);
		p3.add(p4);

		JPanel p5 = new JPanel();
		p5.add(new JLabel("PHONE  :"));
		tf2 = new JTextField(15);
		dupBtn = new JButton("중복");
		dupBtn.addActionListener(this);
		p5.add(tf2);
		p5.add(dupBtn);
		p3.add(p5);

		JPanel p6 = new JPanel();
		p6.add(new JLabel("TEAM  :"));
		tf3 = new JTextField(20);
		p6.add(tf3);
		p3.add(p6);
		//////////////////////////////
		JPanel p10 = new JPanel();
		Choice ch = new Choice();
		ch.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int i = ch.getSelectedIndex();
				if(i==0)/*팀을 선택하세요*/ {
					tf3.setText("");
				}else {
					tf3.setText(ch.getItem(i));
				}
			}
		});
		Vector<String> vlist = mgr.getTeamList();
		ch.add("팀을 선택하세요");
		for (int i = 0; i < vlist.size(); i++) {
			ch.add(vlist.get(i));
		}
		p10.add(ch);
		p3.add(p10);
		//////////////////////////////
		JPanel p7 = new JPanel();
		zipBtn = new JButton("우편번호");
		p7.add(zipBtn);
		p3.add(p7);
		
		JPanel p8 = new JPanel();
		p8.add(new JLabel("주소  :"));
		tf4 = new JTextField(20);
		zipBtn.addActionListener(this);
		p8.add(tf4);
		p3.add(p8);

        JPanel p9 = new JPanel();
		insBtn = new JButton("저장");
		insBtn.addActionListener(this);
		p9.add(insBtn);
		p3.add(p9);

		add(p3, BorderLayout.CENTER);
	}
	
	public void updateForm(MemberBean bean) {
		label.setText("회원수정폼");
		label.setBackground(Color.YELLOW);
		p3.setLayout(new GridLayout(7, 1));
		num = bean.getNum();
		
		JPanel p4 = new JPanel();
		p4.add(new JLabel("NAME  :"));
		tf1 = new JTextField(bean.getName(), 20);
		p4.add(tf1);
		p3.add(p4);

		JPanel p5 = new JPanel();
		p5.add(new JLabel("PHONE  :"));
		tf2 = new JTextField(bean.getPhone(), 20);
		p5.add(tf2);
		p3.add(p5);

		JPanel p6 = new JPanel();
		p6.add(new JLabel("TEAM  :"));
		tf3 = new JTextField(bean.getTeam(), 20);
		p6.add(tf3);
		p3.add(p6);
		//////////////////////////////
		JPanel p10 = new JPanel();
		Choice ch = new Choice();
		ch.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int i = ch.getSelectedIndex();
				if(i==0)/*팀을 선택하세요*/ {
					tf3.setText("");
				}else {
					tf3.setText(ch.getItem(i));
				}
			}
		});
		Vector<String> vlist = mgr.getTeamList();
		ch.add("팀을 선택하세요");
		for (int i = 0; i < vlist.size(); i++) {
			ch.add(vlist.get(i));
		}
		p10.add(ch);
		p3.add(p10);
		//////////////////////////////
		
		JPanel p7 = new JPanel();
		zipBtn = new JButton("우편번호");
		p7.add(zipBtn);
		p3.add(p7);
		
		JPanel p8 = new JPanel();
		p8.add(new JLabel("주소  :"));
		tf4 = new JTextField(bean.getAddress(), 20);
		zipBtn.addActionListener(this);
		p8.add(tf4);
		p3.add(p8);

		JPanel p9 = new JPanel();
		upBtn = new JButton("수정저장");
		upBtn.addActionListener(this);
		p9.add(upBtn);
		p3.add(p9);

		add(p3, BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==b1/*수정 버튼*/) {
			int n = list.getSelectedIndex();
			MemberBean bean = vlist.get(n);
			num = bean.getNum();
			//list에 아이템 제거 및 기존 화면 제거
			list.removeAll();
			remove(list);
			updateForm(bean);
		}else if(obj==b2/*삭제 버튼*/) {
			int n = list.getSelectedIndex();
			MemberBean bean = vlist.get(n);
			if(mgr.deleteMember(bean.getNum())) {
				p3.removeAll();
				remove(p3);
				list.removeAll();
				remove(list);
				vlist.removeAllElements();
				viewList();
			}
		}else if(obj==b3/*입력폼 버튼*/) {
			p3.removeAll();
			list.removeAll();
			remove(list);
			insertForm();
		}else if(obj==b4/*리스트 버튼*/) {
			p3.removeAll();
			remove(p3);
			remove(list);
			vlist.removeAllElements();
			viewList();
		}else if(obj==zipBtn/*우편번호 검색창*/) {
			//처음 실행할때 객체를 생성하지만, 두 번째는 재사용
			if(zf==null) {
				zf = new ZipcodeFrame2_1(this);
				//ZipcodeFrame의 창의 위치를 MemberAWT 옆 배치
				zf.setLocation(getX() + getWidth(), getY());
			}else {
				zf.setLocation(getX() + getWidth(), getY());
				zf.setVisible(true);
			}	
		}else if(obj==insBtn/*입력 저장 버튼*/) {
			MemberBean bean = new MemberBean();
			bean.setName(tf1.getText());
			bean.setPhone(tf2.getText());
			bean.setTeam(tf3.getText());
			bean.setAddress(tf4.getText());
			if(mgr.insertMember(bean)) {
				p3.removeAll();
				list.removeAll();
				remove(list);
				insertForm();
			}
		}else if(obj==upBtn/*수정 저장 버튼*/) {
			MemberBean bean = new MemberBean();
			bean.setNum(num);
			bean.setName(tf1.getText());
			bean.setPhone(tf2.getText());
			bean.setTeam(tf3.getText());
			bean.setAddress(tf4.getText());
			if(mgr.updateMember(bean)) {
				p3.removeAll();
				remove(p3);
				vlist.removeAllElements();
				viewList();
			}
		}else if(obj==dupBtn/*중복 확인 버튼*/) {
			String phone = tf2.getText().trim();
			if(phone.isEmpty()) {
				new DialogBox(this, "전화번호를 입력하세요.", "오류");
			}else {
				Boolean flag = mgr.duplicationPhone(phone);
				if (flag) {
					new DialogBox(this, "중복된 전화번호 입니다.", "알림");
				}else {
					new DialogBox(this, "사용가능한 전화번호 입니다.", "확인");
				}
			}
		}
		
		validate();
	}//--actionPerformed

	public static void main(String[] args) {
		new MemberAWT2_1();
	}
}
