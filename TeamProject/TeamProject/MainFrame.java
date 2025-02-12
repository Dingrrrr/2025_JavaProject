package TeamProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class MainFrame extends JFrame {
	private BufferedImage image;

	public MainFrame() {
		setTitle("프레임 설정");
		setSize(402, 874);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			image = ImageIO.read(new File("TeamProject/phone_frame.png")); // 투명 PNG 불러오기
		} catch (Exception e) {
			e.printStackTrace();
		}

		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (image != null) {
					// 원하는 크기로 조정 (예: 300x600)
					Image scaledImage = image.getScaledInstance(402, 874, Image.SCALE_SMOOTH);
					g.drawImage(scaledImage, 0, 0, this);
				}
			}
		};

		panel.setOpaque(false);
		panel.setLayout(null);
		add(panel);

		// 닫기 버튼 추가
		JButton closeButton = new JButton("X");
		closeButton.setBounds(370, 10, 20, 20); // 오른쪽 상단에 위치
		closeButton.setBackground(Color.RED); // 버튼 배경 색 (선택 사항)
		closeButton.setForeground(Color.WHITE); // 버튼 텍스트 색 (선택 사항)
		closeButton.setBorder(BorderFactory.createEmptyBorder());
		closeButton.setFocusPainted(false);
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // 애플리케이션 종료
			}
		});
		
		panel.add(closeButton);

		setVisible(true);
	}

	public static void main(String[] args) {
		new MainFrame();
	}
}
