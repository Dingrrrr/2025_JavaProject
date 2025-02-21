package TeamProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.*;

public class AlarmNewDialog extends JFrame {
	private JPanel p;
	private BufferedImage image;
	private JButton chkButton;
	private JLabel closeLabel;
	TPMgr mgr;

	public AlarmNewDialog() {
		setTitle("프레임 설정");
		setSize(358, 192);
		setUndecorated(true);
		// setLocationRelativeTo(parent);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mgr = new TPMgr();

		try {
			image = ImageIO.read(new File("TeamProject/pet_add_frame.png")); // 투명 PNG 불러오기
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 🔹 공통 마우스 클릭 이벤트 리스너
		MouseAdapter commonMouseListener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object source = e.getSource(); // 클릭된 컴포넌트 확인
				if (source == chkButton) {
					System.out.println("확인 버튼 클릭됨");
					dispose();
				} else if (source == closeLabel) {
					System.out.println("닫힘 버튼 클릭됨");
					dispose();
				}

			}
		};
		
		JLabel deleteLabel = new JLabel("새로운 알림이 있습니다");
		deleteLabel.setBounds(120, 50, 200 ,40);
		deleteLabel.setForeground(Color.black);
		add(deleteLabel);

		// 확인 버튼
		chkButton = new RoundedButton("확인");
		chkButton.setBounds(130, 120, 100, 40);
		chkButton.setBackground(Color.gray);
		chkButton.setForeground(Color.white);
		chkButton.setFocusable(false); // 포커스 끄기
		chkButton.addMouseListener(commonMouseListener);
		add(chkButton);

		// 🔹 닫기 버튼 이미지 추가
		closeLabel = createScaledImageLabel("TeamProject/delete_button.png", 28, 28);
		closeLabel.setBounds(320, 7, 28, 28);
		closeLabel.addMouseListener(commonMouseListener);
		add(closeLabel); // 🔹 패널에 추가

		// JPanel 추가
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (image != null) {
					// 이미지 크기 조정 후 그리기
					Image scaledImage = image.getScaledInstance(358, 192, Image.SCALE_SMOOTH);
					g.drawImage(scaledImage, 0, 0, this);
				}
			}
		};

		panel.setLayout(null);
		panel.setOpaque(false); // 🔹 배경을 투명하게 설정
		add(panel);

		setVisible(true);

	}

	
	/**
	 * 이미지 크기를 조정하여 JLabel을 생성하는 메서드
	 */
	private JLabel createScaledImageLabel(String imagePath, int width, int height) {
		ImageIcon icon = new ImageIcon(imagePath);
		Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new JLabel(new ImageIcon(scaledImage));
	}
	public static void main(String[] args) {
		new AlarmNewDialog();
	}
}
