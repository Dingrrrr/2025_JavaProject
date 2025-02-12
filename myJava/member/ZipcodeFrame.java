package member;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.List;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ZipcodeFrame extends MFrame
implements ActionListener{

	JLabel label;
	JButton searchBtn, selectBtn;
	List list;
	TextField tf;
	JPanel p1,p2;
	ZipcodeMgr mgr;
	DialogBox err;
	MemberAWT awt;
	
	public ZipcodeFrame(MemberAWT awt) {
		super(300, 500);
		setTitle("ZipcodeFrame");
		this.awt = awt;
		mgr = new ZipcodeMgr();
		p1=new JPanel();
		p1.setBackground(Color.LIGHT_GRAY);
		p1.add(label = new JLabel ("도로명 : ",label.CENTER));
		p1.add(tf = new TextField("광평로",15));
		p1.add(searchBtn = new JButton("검색"));
		tf.addActionListener(this);
		searchBtn.addActionListener(this);
		//////////////////////////////////////
		list = new List();
		list.addActionListener(this);
		//////////////////////////////////////
		p2=new JPanel();
		p2.add(selectBtn = new JButton("선택"));
		selectBtn.addActionListener(this);
		p2.setBackground(Color.LIGHT_GRAY);
		add(p1,BorderLayout.NORTH);
		add(list,BorderLayout.CENTER);
		add(p2,BorderLayout.SOUTH);
		tf.requestFocus();
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==tf||obj==searchBtn) {
			if(list.getItemCount()!=0) //기존에 검색 결과가 있는 경우
				list.removeAll();
			Vector<ZipcodeBean> vlist = mgr.searchZipcode(tf.getText().trim());
			if(vlist.isEmpty()) {
				//DialogBox가 처음에는 객체를 생성하지만 두 번째는 재사용
				if(err==null) {
					err = new DialogBox(this, "검색 결과가 없습니다.", "알림");
				}else {
					err.setLocationRelativeTo(this);
					err.setVisible(true);
				}
			}else {
				//결과가 있는 경우
				for (int i = 0; i < vlist.size(); i++) {
					ZipcodeBean bean = vlist.get(i);
					String adds = bean.getZipcode() + "";
					adds += bean.getArea1().trim() + "";
					adds += bean.getArea2().trim() + "";
					adds += bean.getArea3().trim() + "";
					list.add(adds);
				}
			}
		}else if(obj==list||obj==selectBtn) {
			/* 선택한 주소를 더블클릭 또는 '선택' 버튼을 클릭하면
			 * 자신의 창은 사라지고 MemberAWT 창에 주소값 전달. 
			 * 검색된 주소는 모두 제거 */
			String add = list.getSelectedItem();
			awt.tf4.setText(add);
			list.removeAll();
			dispose();
		}
	}
	
	public static void main(String[] args) {
		//new ZipcodeFrame();
	}
}
