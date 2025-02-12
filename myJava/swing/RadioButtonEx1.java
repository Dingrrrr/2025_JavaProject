package swing;

import java.awt.BorderLayout;
import java.awt.Button;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

public class RadioButtonEx1 extends JFrame {
	JRadioButton jb[] = new JRadioButton[3];
	String size[] = {"Small Size","Medium Size","Large Size"};
	JLabel text;
	JPanel topP, sizeP, resultP;
	
	public RadioButtonEx1() {
		setSize(400, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		topP = new JPanel();
		JLabel label = new JLabel("어떤 커피를 주문하시겠습니까?");
		topP.add(label);
		
		ButtonGroup gr = new ButtonGroup();
		sizeP = new JPanel();
		for (int i = 0; i < jb.length; i++) {
			gr.add(jb[i] = new JRadioButton(size[i]));
			sizeP.add(jb[i]);
		}
		Border border = BorderFactory.createTitledBorder("SIZE");
		sizeP.setBorder(border);
		add(topP, BorderLayout.PAGE_START);
		add(sizeP, BorderLayout.CENTER);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new RadioButtonEx1();
	}
}
