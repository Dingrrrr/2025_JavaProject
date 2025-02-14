package TeamProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.border.EmptyBorder;

public class VoteResultDialog extends JFrame {
	private BufferedImage image;
	private JLabel modifyLabel, closeLabel, grayFrameLabel;

	public VoteResultDialog() {
		setTitle("프레임 설정");
		setSize(350, 401);
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
						if (source == closeLabel) {
							System.out.println("닫기 버튼 클릭됨");
							dispose(); // 창 닫기
						} else  {

						}

					}
				};
				
		// 🔹 회색프레임
		grayFrameLabel = createScaledImageLabel("TeamProject/photo_frame.png", 280, 280);
		grayFrameLabel.setBounds(17, 45, 318, 318);
		grayFrameLabel.addMouseListener(commonMouseListener);
		add(grayFrameLabel);
		
		
		// JPanel 추가
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (image != null) {
					// 이미지 크기 조정 후 그리기
					Image scaledImage = image.getScaledInstance(350, 401, Image.SCALE_SMOOTH);
					g.drawImage(scaledImage, 0, 0, this);
				}
			}
		};

		panel.setLayout(null);
		panel.setOpaque(false); // 🔹 배경을 투명하게 설정
		add(panel);

		// 🔹 닫기 버튼 이미지 추가
		closeLabel = createScaledImageLabel("TeamProject/delete_button.png", 28, 28);
		closeLabel.setBounds(315, 7, 28, 28);
		closeLabel.addMouseListener(commonMouseListener);
		panel.add(closeLabel); // 🔹 패널에 추가
		
		//🔹 수정 버튼 이미지 추가
		modifyLabel = createScaledImageLabel("Teamproject/modify_icon.png", 28, 28);
		modifyLabel.setBounds(275, 7, 28, 28);
		modifyLabel.addMouseListener(commonMouseListener);
		panel.add(modifyLabel);

		setVisible(true);
	}

	private JLabel createScaledImageLabel(String imagePath, int width, int height) {
		ImageIcon icon = new ImageIcon(imagePath);
		Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new JLabel(new ImageIcon(scaledImage));
	}

	public static void main(String[] args) {
		new VoteResultDialog();
	}
}
