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

public class UserDeleteDialog extends JFrame {
	private JPanel p;
	private BufferedImage image;
	private JButton yesButton, noButton;
	private JLabel closeLabel;

	public UserDeleteDialog() {
		setTitle("프레임 설정");
		setSize(358, 192);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
				if (source == yesButton) {
					System.out.println("회원 탈퇴 버튼 클릭됨");
				} else if (source == noButton) {
					System.out.println("회원 탈퇴 취소 클릭됨");
					dispose();
				} else if (source == closeLabel) {
					System.out.println("취소 버튼 클릭됨");
					dispose();
				}

			}
		};
		
		JLabel deleteLabel = new JLabel("정말 탈퇴하시겠습니까?");
		deleteLabel.setBounds(120, 50, 200 ,40);
		deleteLabel.setForeground(Color.black);
		add(deleteLabel);
		
		// 회원 탈퇴 버튼
		yesButton = new RoundedButton("예");
		yesButton.setBounds(210, 120, 100, 40);
		yesButton.setBackground(Color.gray);
		yesButton.setForeground(Color.white);
		yesButton.setFocusable(false); // 포커스 끄기
		yesButton.addMouseListener(commonMouseListener);
		add(yesButton);

		// 회원 탈퇴 취소 버튼
		noButton = new RoundedButton("아니오");
		noButton.setBounds(50, 120, 100, 40);
		noButton.setBackground(Color.gray);
		noButton.setForeground(Color.white);
		noButton.setFocusable(false); // 포커스 끄기
		noButton.addMouseListener(commonMouseListener);
		add(noButton);

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
		new UserDeleteDialog();
	}
}
