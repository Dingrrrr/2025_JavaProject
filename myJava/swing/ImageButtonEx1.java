package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//AWT: OS에 있는 이미지를 사용 -> OS마다 모양이 상이함.
//swing : 어도비에서 제공된 이미지 사용.
public class ImageButtonEx1 extends JFrame{
	
	JPanel p;
	JLabel label;
	JButton btn;
	ImageIcon icon, dog;
	
	public ImageButtonEx1() {
		setSize(300,250);
		//x를 클릭하면 종료되는 기능 추가
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 공식과 같다.
		p = new JPanel();
		label = new JLabel("이미지를 보려면 아래 버튼을 누르세요.");
		btn = new JButton("이미지 레이블");
		icon = new ImageIcon("swing/icon.gif");
		btn.setIcon(icon);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dog = new ImageIcon("swing/dog.gif");
				label.setIcon(dog);
				label.setText(null);
			}
		});
		
		p.add(label);
		p.add(btn);
		add(p);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ImageButtonEx1();
	}
}