package member2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;


class DialogBox extends JDialog implements ActionListener {
    
	JButton b1;
    Frame f;
    
    DialogBox(Frame f,String name,String title) {
        super(f, true);
        this.f = f;
        setTitle(title);
        // 메시지 출력
        add(new JLabel(name, JLabel.CENTER));
        JPanel pan = new JPanel();
        pan.setLayout(new FlowLayout());
        add(pan, BorderLayout.SOUTH);

        b1 = new JButton("확인");
        pan.add(b1);
        b1.addActionListener(this);
        	
        setSize(250, 150);
        setLocationRelativeTo(f);
        setVisible(true);
    }

    // 다이얼로그 위의 버튼을 클릭했을 때
    public void actionPerformed(ActionEvent evt) {
        dispose();
    }
}
